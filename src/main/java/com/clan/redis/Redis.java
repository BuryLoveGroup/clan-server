package com.clan.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by robot on 2017/11/6.
 */
public class Redis {

    private static Properties properties = new Properties();

     //Redis服务器IP
    private static String ADDR;

    //Redis的端口号
    private static int PORT;

    //访问密码
    private static String AUTH;

    private static int MAX_ACTIVE;


    private static int MAX_IDLE;


    private static int MAX_WAIT;

    private static int TIMEOUT;


    private static boolean TEST_ON_BORROW;

    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            //当前类
            InputStream inputStream = new BufferedInputStream(new FileInputStream("/application.properties"));
            properties.load(inputStream);
            //获取redis属性
            AUTH = properties.getProperty("redis.password");
            ADDR = properties.getProperty("reids.host");
            PORT = Integer.valueOf(properties.getProperty("redis.port"));
            MAX_ACTIVE = Integer.valueOf("redis.pool.max-idle");
            MAX_IDLE = Integer.valueOf("redis.pool.max-active");
            MAX_WAIT = Integer.valueOf("redis.pool.max-wait");
            TEST_ON_BORROW = Boolean.valueOf(properties.getProperty("redis.borrow"));
            //获取连接池
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(MAX_IDLE);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
}
