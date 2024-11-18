package com.enba.integrate.preventretry.service;

import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class DefaultRedisCacheTemplate implements CacheTemplate {

  private RedisTemplate<String, Object> redisTemplate;

  public DefaultRedisCacheTemplate(RedisTemplate<String, Object> redisTemplate) {
    super();
    this.redisTemplate = redisTemplate;
  }

  /**
   * 使用RedisTemplate设置键值对，并设置过期时间
   *
   * @param key 键，用于标识存储在Redis中的数据
   * @param value 值，要存储在Redis中的数据
   * @param timeout 过期时间，表示数据在Redis中存活的时间
   * @param unit 时间单位，对timeout的度量单位进行说明，例如秒、分钟等
   *     <p>通过调用此方法，可以在Redis中设置一个键值对，并指定其过期时间 这对于需要临时存储并在特定时间后自动删除的数据非常有用
   */
  @Override
  public void set(String key, Object value, long timeout, TimeUnit unit) {
    ValueOperations<String, Object> operations = redisTemplate.opsForValue();
    operations.set(key, value, timeout, unit);
  }

  /**
   * 根据指定的键从Redis中获取值
   *
   * @param key 要获取的键，不为null
   * @return 键对应的值，如果键不存在则返回null
   */
  @Override
  public Object get(String key) {
    ValueOperations<String, Object> operations = redisTemplate.opsForValue();
    return operations.get(key);
  }

  /**
   * 删除指定的键
   *
   * @param key 要删除的键
   */
  @Override
  public void delete(String key) {
    redisTemplate.delete(key);
  }

  /**
   * 设置缓存过期时间 通过设置特定的过期时间，可以释放不再需要的数据占用的内存空间，防止数据永不过期导致内存溢出
   *
   * @param key 缓存的键，用于标识特定的缓存数据
   * @param timeout 过期时间的值，表示缓存数据的有效时间长度
   * @param unit 过期时间的单位，用于指定过期时间值的度量标准
   * @return 返回缓存数据是否成功设置过期时间的布尔值，true表示成功，false表示失败
   */
  @Override
  public Boolean expire(String key, long timeout, TimeUnit unit) {
    return redisTemplate.expire(key, timeout, unit);
  }
}
