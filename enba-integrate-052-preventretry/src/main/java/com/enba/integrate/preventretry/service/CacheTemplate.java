package com.enba.integrate.preventretry.service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.util.Assert;

public interface CacheTemplate {

  void set(String key, Object value, long timeout, TimeUnit unit);

  /**
   * 将指定键和值关联到缓存中，并设置项的过期时间
   *
   * @param key 缓存键，必须唯一标识缓存项
   * @param value 缓存值，可以是任意类型的数据
   * @param timeout 项的过期时间，表示从当前时间起算的生存时间
   *     <p>注意：此方法假定存在两个同名但参数不同的set方法， 一个接受长整型毫秒值和时间单位参数，另一个接受键、值和过期时间参数
   *     <p>原因：本方法通过重载以适应不同时间单位的timeout，确保灵活性
   */
  default void set(String key, Object value, Duration timeout) {
    // 确保timeout参数不为null，对非法参数进行前置检查
    Assert.notNull(timeout, "Timeout must not be null!");

    // 根据timeout的毫秒值是否存在，来决定使用毫秒还是秒作为时间单位
    if (TimeoutUtils.hasMillis(timeout)) {
      // 当timeout中包含毫秒值时，调用重载的set方法，并使用毫秒作为时间单位
      set(key, value, timeout.toMillis(), TimeUnit.MILLISECONDS);
    } else {
      // 当timeout中不包含毫秒值时，调用重载的set方法，并使用秒作为时间单位
      set(key, value, timeout.getSeconds(), TimeUnit.SECONDS);
    }
  }

  Object get(String key);

  /**
   * 根据键名获取值，如果缓存中没有该键，则使用供应商提供的值设置缓存，并返回该值
   *
   * @param key 缓存键名
   * @param timeout 设置缓存的超时时间
   * @param unit 超时时间的单位
   * @param supplier 用于获取缓存不存在时要设置的值的供应商函数
   * @return 返回缓存中存在的值或通过供应商函数设置并返回的值
   */
  default Object getAndSet(String key, long timeout, TimeUnit unit, Supplier<Object> supplier) {
    // 尝试从缓存中获取值
    Object object = get(key);
    if (object != null) {
      // 如果缓存中存在该键名对应的值，则直接返回该值
      return object;
    }
    // 使用供应商函数获取值
    Object obj = supplier.get();
    if (obj != null) {
      // 如果供应商函数提供了值，则将该值设置到缓存中
      set(key, obj, timeout, unit);
    }
    // 返回通过供应商函数设置并返回的值
    return obj;
  }

  /**
   * 使用指定超时时间设置缓存，并返回旧值 如果键已存在于缓存中，则使用指定的超时时间更新它并返回旧值 如果键不存在于缓存中，则根据提供的 Supplier 生成新值，并使用指定的超时时间设置它
   *
   * @param key 缓存键，用于标识缓存项
   * @param timeout 指定的超时时间，可以是毫秒或秒
   * @param supplier 用于生成新值的 Supplier 函数接口
   * @return 该键的旧值，如果不存在则返回 null
   *     <p>注意：此方法假定存在名为 `getAndSet` 的重载方法，可以接受长整型和时间单位参数 此外，`TimeoutUtils.hasMillis` 方法用于检查
   *     Duration 是否具有毫秒值
   */
  default Object getAndSet(String key, Duration timeout, Supplier<Object> supplier) {
    Assert.notNull(timeout, "Timeout must not be null!");
    if (TimeoutUtils.hasMillis(timeout)) {
      return getAndSet(key, timeout.toMillis(), TimeUnit.MILLISECONDS, supplier);
    }
    return getAndSet(key, timeout.getSeconds(), TimeUnit.SECONDS, supplier);
  }

  void delete(String key);

  Boolean expire(String key, long timeout, TimeUnit unit);

  /**
   * 设置缓存过期时间
   *
   * <p>此方法用于为给定的缓存键设置过期时间，过期时间由Duration对象指定， 可以是毫秒或秒为单位的持续时间。方法首先检查timeout是否为null，
   * 如果为null，则抛出IllegalArgumentException异常。然后，根据timeout的值是 否包含毫秒来决定调用哪个重载版本的expire方法。如果timeout中有毫秒，
   * 则调用接受毫秒单位的expire方法，并传入毫秒值和TimeUnit.MILLISECONDS； 否则，调用接受秒单位的expire方法，并传入秒值和TimeUnit.SECONDS。
   *
   * @param key 缓存的键，不能为null
   * @param timeout 持续时间，指定了缓存的过期时间，不能为null
   * @return 如果过期时间设置成功，返回true；否则返回false
   * @throws IllegalArgumentException 如果timeout为null
   */
  default Boolean expire(String key, Duration timeout) {

    Assert.notNull(timeout, "Timeout must not be null");

    return TimeoutUtils.hasMillis(timeout)
        ? expire(key, timeout.toMillis(), TimeUnit.MILLISECONDS)
        : expire(key, timeout.getSeconds(), TimeUnit.SECONDS);
  }
}
