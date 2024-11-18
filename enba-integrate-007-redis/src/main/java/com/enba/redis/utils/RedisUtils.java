package com.enba.redis.utils;

import com.alibaba.fastjson.JSON;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class RedisUtils {

  private static RedisTemplate<String, Object> REDIS_TEMPLATE;

  @Resource
  private RedisTemplate<String, Object> rt;

  @PostConstruct
  public void init() {
    REDIS_TEMPLATE = rt;
  }

  /**
   * 获取
   *
   * @param cacheKey 缓存key
   * @param c        返回值类型
   * @return c类型的对象
   */
  public static <T> T get(String cacheKey, Class<T> c) {
    Object o = REDIS_TEMPLATE.opsForValue().get(cacheKey);
    return JSON.parseObject(JSON.toJSONString(o), c);
  }

  /**
   * 缓存是否存在
   *
   * @param cacheKey 缓存key
   * @return true 存在
   */
  public static boolean hasKey(String cacheKey) {
    return Boolean.TRUE.equals(REDIS_TEMPLATE.hasKey(cacheKey));
  }

  /**
   * 添加到set集合
   *
   * @param cacheKey 缓存key
   * @param value    值 一个或多个
   */
  public static void addForSet(String cacheKey, Object... value) {
    REDIS_TEMPLATE.opsForSet().add(cacheKey, value);
  }

  /**
   * 获取set集合
   *
   * @param cacheKey 缓存
   * @param c        返回值类型
   * @return c类型的set集合
   */
  public static <T> Set<T> getForSet(String cacheKey, Class<T> c) {
    Set<Object> set = REDIS_TEMPLATE.boundSetOps(cacheKey).members();
    if (Objects.isNull(set)) {
      return null;
    }
    return set.stream().map(o -> JSON.parseObject(JSON.toJSONString(o), c))
        .collect(Collectors.toSet());
  }

  /**
   * 设置过期时间
   *
   * @param cacheKey 缓存 key
   * @param timeout  过期时间（秒）
   */
  public static void expire(String cacheKey, long timeout) {
    REDIS_TEMPLATE.expire(cacheKey, timeout, TimeUnit.MINUTES);
  }

  /**
   * 删除指定缓存
   *
   * @param cacheKey 缓存key
   */
  public static Boolean delete(String cacheKey) {
    return REDIS_TEMPLATE.delete(cacheKey);
  }

  /**
   * 指定元素删除
   *
   * @param cacheKey 缓存
   * @param objKey   集合元素
   */
  public static void remove(String cacheKey, String objKey) {
    REDIS_TEMPLATE.boundSetOps(cacheKey).remove(objKey);
  }


  /**
   * 存储
   *
   * @param cacheKey 缓存key
   * @param value    值
   * @param timeout  超时时间 秒
   */
  public static void save(String cacheKey, Object value, long timeout) {
    REDIS_TEMPLATE.opsForValue().set(cacheKey, value, timeout, TimeUnit.SECONDS);
  }

  public static void save(String cacheKey, Object value) {
    REDIS_TEMPLATE.opsForValue().set(cacheKey, value);
  }
}
