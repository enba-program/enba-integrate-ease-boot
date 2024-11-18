package com.enba.integrate.authorize.aspect;

import com.alibaba.fastjson.JSON;
import com.enba.integrate.authorize.annotation.AuthStrategy;
import com.enba.integrate.authorize.annotation.PreAuthorize;
import java.util.Map;
import java.util.TreeMap;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE+1) // 设置优先级最高，优先执行鉴权切面逻辑
@Aspect
public class AuthAspect implements ApplicationContextAware {

  private ApplicationContext apiSystemContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.apiSystemContext = applicationContext;
  }

  /**
   * 切面方法，处理PreAuthorize注解的环绕通知逻辑 该方法在目标方法执行前后执行，以实现权限校验功能
   *
   * @param joinPoint 连接点对象，用于获取方法签名、参数等信息，并执行目标方法
   * @param preAuthorize 参数对象，用于匹配PreAuthorize注解
   * @return 目标方法的执行结果
   * @throws Throwable 如果方法执行过程中抛出异常，则向上抛出
   */
  @Around("@annotation(preAuthorize) || @within(preAuthorize)")
  public Object doAroundAdvice(ProceedingJoinPoint joinPoint, PreAuthorize preAuthorize)
      throws Throwable {
    // 获取连接点的签名
    Signature signature = joinPoint.getSignature();
    // 将签名转换为方法签名
    MethodSignature methodSignature = (MethodSignature) signature;

    // 获取方法上的PreAuthorize注解
    PreAuthorize preAuthorizeOnMethod =
        AnnotationUtils.getAnnotation(methodSignature.getMethod(), PreAuthorize.class);

    // 获取类上的PreAuthorize注解
    PreAuthorize preAuthorizeOnClass = getPreApiSystemAuthorizeOnClass(methodSignature);

    // 权限校验策略对象
    AuthStrategy<?> authStrategy = null;

    // 根据就近原则，优先检查方法上的注解配置的权限校验策略
    if (null != preAuthorizeOnMethod) {
      // 如果方法上注解配置不需要校验权限，则直接执行目标方法
      if (!preAuthorizeOnMethod.checkAuth()) {
        return joinPoint.proceed();
      } else {
        // 根据注解配置获取权限校验策略实例
        if (AuthStrategy.class != preAuthorizeOnMethod.checkAuthClass()) {
          authStrategy = apiSystemContext.getBean(preAuthorizeOnMethod.checkAuthClass());
        } else if (AuthStrategy.class != preAuthorizeOnClass.checkAuthClass()) {
          authStrategy = apiSystemContext.getBean(preAuthorizeOnClass.checkAuthClass());
        }
      }
    } else {
      // 如果类上注解配置不需要校验权限，则直接执行目标方法
      if (!preAuthorizeOnClass.checkAuth()) {
        return joinPoint.proceed();
      } else if (AuthStrategy.class != preAuthorizeOnClass.checkAuthClass()) {
        // 根据类上注解配置获取权限校验策略实例
        authStrategy = apiSystemContext.getBean(preAuthorizeOnClass.checkAuthClass());
      }
    }

    // 获取目标方法的所有参数值
    Object[] args = joinPoint.getArgs();
    // 获取目标方法的所有参数名称
    String[] paramNames = methodSignature.getParameterNames();
    // 根据参数值和参数名称生成参数Map
    Map<String, Object> allParamMap = generateAllParamMap(args, paramNames);

    // 如果未正确配置权限校验策略，则抛出运行时异常
    if (null == authStrategy) {
      throw new RuntimeException("@PreAuthorize 权限类配置错误. args: " + JSON.toJSONString(allParamMap));
    }

    // 调用权限校验策略的access方法进行权限校验
    boolean access = authStrategy.access(allParamMap);
    // 如果没有通过权限校验，则抛出相应的异常
    if (!access) {
      throw authStrategy.accessDeniedException(allParamMap);
    }

    // 权限校验通过，执行目标方法并返回结果
    return joinPoint.proceed();
  }

  /**
   * 从类中获取PreApiSystemAuthorize注解 该方法通过MethodSignature获取方法的声明类型，并在此类型上查找PreApiSystemAuthorize注解
   *
   * @param methodSignature 方法签名，用于获取声明类型
   * @return 返回找到的PreApiSystemAuthorize注解，如果没有找到则返回null
   */
  private PreAuthorize getPreApiSystemAuthorizeOnClass(MethodSignature methodSignature) {
    // 获取方法的声明类型
    Class<?> declaringType = methodSignature.getDeclaringType();
    // 在声明类型上查找PreApiSystemAuthorize注解
    return AnnotationUtils.findAnnotation(declaringType, PreAuthorize.class);
  }

  /**
   * 将参数数组和参数名数组生成一个包含所有参数的映射
   *
   * @param args 参数数组，包含方法调用时传递的所有参数值
   * @param paramNames 参数名数组，包含方法参数的名称
   * @return 返回一个按照参数名称排序的Map，其中key为参数名，value为参数值
   */
  private Map<String, Object> generateAllParamMap(Object[] args, String[] paramNames) {
    // 创建一个排序的Map来存储参数名和参数值
    Map<String, Object> paramMap = new TreeMap<>();

    // 检查参数名数组是否存在且不为空，以决定是否需要生成参数映射
    if (paramNames != null && paramNames.length > 0) {
      // 遍历参数名数组，将参数名和对应的参数值添加到映射中
      for (int i = 0; i < paramNames.length; i++) {
        paramMap.put(paramNames[i], args[i]);
      }
    }

    // 返回生成的参数映射
    return paramMap;
  }
}
