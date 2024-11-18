package com.enba.integrate.log.aop;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.enba.integrate.log.dao.AccessFuncLog.Log;
import com.enba.integrate.log.service.LogService;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Component
@Aspect
public class LogAspect implements ApplicationContextAware {

  private ApplicationContext applicationContext;

  @Pointcut("@annotation(com.enba.integrate.log.annotation.Log)")
  public void execLog() {
    // 切入点表达式，用于指定带有com.enba.integrate.log.annotation.Log注解的方法为切入点
    // 该注解标记的方法或方法内的任何方法都将被AOP框架识别并作为执行日志记录的切入点
  }

  /**
   * 环绕通知方法，用于拦截服务层方法并记录方法执行的日志信息
   *
   * @param pjp ProceedingJoinPoint对象，包含ProceedingJoinPoint的信息和操作
   * @return 方法执行的结果对象
   * @throws Throwable 方法执行过程中抛出的异常
   */
  @Around(value = "execLog()")
  public Object handlerMethod(ProceedingJoinPoint pjp) throws Throwable {
    // 获取连接点的签名
    Signature signature = pjp.getSignature();
    // 将签名转换为MethodSignature
    MethodSignature methodSignature = (MethodSignature) signature;
    // 获取目标方法
    Method targetMethod = methodSignature.getMethod();
    // 方法执行结果初始化为null
    Object result = null;
    // 记录方法开始执行的时间
    long startTime = System.currentTimeMillis();

    // 检查目标方法上是否有Log注解
    if (targetMethod.isAnnotationPresent(com.enba.integrate.log.annotation.Log.class)) {
      // 初始化访问信息
      Log accessInfo = initAccessInfo(signature, pjp.getArgs());
      // 用于存储异常对象
      Throwable ee = null;
      try {
        // 执行连接点方法
        result = pjp.proceed();
        // 设置访问信息中的成功标志
        accessInfo.setSuccess(true);
      } catch (Throwable e) {
        // 记录异常信息
        accessInfo.setException(e.getMessage());
        // 设置成功标志为false
        accessInfo.setSuccess(false);
        // 存储异常对象
        ee = e;
      }

      // 设置访问信息中的执行时间 单位：毫秒
      accessInfo.setSpendTime(System.currentTimeMillis() - startTime);

      // 将方法执行结果转换为字符串并存储在访问信息中
      accessInfo.setResponseData(JSON.toJSONString(result));

      // 先保存日志信息
      saveLog(accessInfo);

      // 在抛出异常
      if (ee != null) {
        throw ee;
      }
    }

    // 返回方法执行结果
    return result;
  }

  /**
   * 初始化访问日志信息 该方法用于提取并封装请求日志相关信息，如模块名、功能名、请求方法、请求参数等 主要在日志记录功能中使用，帮助记录每个请求的详细信息
   *
   * @param signature 当前方法的签名，用于获取方法名和其他信息
   * @param args 方法参数数组，包含请求传入的所有参数
   * @return Log对象，封装了请求日志的所有必要信息
   */
  private Log initAccessInfo(Signature signature, Object[] args) {
    // 获取方法签名
    MethodSignature methodSignature = (MethodSignature) signature;
    // 获取目标方法
    Method targetMethod = methodSignature.getMethod();
    // 获取目标方法上的Log注解信息
    com.enba.integrate.log.annotation.Log logMsg =
        targetMethod.getAnnotation(com.enba.integrate.log.annotation.Log.class);

    // 初始化Log对象
    Log retObj = new Log();
    // 获取HttpServletRequest对象
    HttpServletRequest httpServletRequest =
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    // 设置模块名、功能名、请求方法、请求类名、请求参数、请求URI和用户代理信息
    retObj
        .setModule(logMsg.module())
        .setFunc(logMsg.func())
        .setMethod(httpServletRequest.getMethod())
        .setClassName(signature.getDeclaringTypeName())
        .setParams(JSON.toJSONString(httpServletRequest.getParameterMap()))
        .setRequestUri(httpServletRequest.getRequestURI())
        .setUserAgent(httpServletRequest.getHeader("user-agent"))
        .setRemoteAddr(ServletUtil.getClientIP(httpServletRequest));

    // 初始化参数字符串
    String params = "";
    // 如果有参数，根据请求方法区分处理
    if (args.length > 0) {
      if (HttpMethod.GET.name().equalsIgnoreCase(retObj.getMethod())) {
        // 对于GET请求，直接获取查询字符串作为参数
        params = httpServletRequest.getQueryString();
      } else {
        // 对于非GET请求，将参数数组转换为JSON字符串
        params = JSONUtil.toJsonStr(args);
      }
    }
    // 设置参数
    retObj.setParams(params);
    // 返回封装好的Log对象
    return retObj;
  }

  private void saveLog(Log log) {
    LogService logService = applicationContext.getBean(LogService.class);
    if (ObjectUtil.isNull(logService)) {
      return;
    }

    logService.saveLog(log);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
