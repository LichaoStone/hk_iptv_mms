package com.haikan.iptv.config.redis;

import com.haikan.iptv.config.Exception.RedisConnectException;

@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws RedisConnectException;
}
