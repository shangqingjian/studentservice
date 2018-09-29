package com.huawei.StudentService.sysinit;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisManager {
	
	private static final Logger logger = Logger.getLogger(RedisManager.class);
	
	private static String ADDR = null;
	private static int PORT = 0;
	private static String AUTH = null;
	private static int MAX_ACTIVE = 0;
	private static int MAX_IDLE = 0;
	private static int MAX_WAIT = 0;
	private static int TIMEOUT = 0;
	private static boolean TEST_ON_BORROW = false;
	
    private static JedisPool jedisPool = null;

	static {
    	ADDR = (String) ConfigCenter.getCfgByKeyWithDefault("REDIS_ADDR", "");
    	PORT = (int) ConfigCenter.getCfgByKeyWithDefault("REDIS_PORT", 0);
    	AUTH = (String) ConfigCenter.getCfgByKeyWithDefault("REDIS_AUTH", "");
    	MAX_ACTIVE = (int) ConfigCenter.getCfgByKeyWithDefault("REDIS_MAX_ACTIVE", 0);
    	MAX_IDLE = (int) ConfigCenter.getCfgByKeyWithDefault("REDIS_MAX_IDLE", 0);
    	MAX_WAIT = (int) ConfigCenter.getCfgByKeyWithDefault("REDIS_MAX_WAIT", 0);
    	TIMEOUT = (int) ConfigCenter.getCfgByKeyWithDefault("REDIS_TIMEOUT", 0);
    	TEST_ON_BORROW = (boolean) ConfigCenter.getCfgByKeyWithDefault("REDIS_TEST_ON_BORROW", false);
    	
    	JedisPoolConfig config = new JedisPoolConfig();
    	config.setMaxTotal(MAX_ACTIVE);
    	config.setMaxIdle(MAX_IDLE);
    	config.setMaxWaitMillis(MAX_WAIT);
    	config.setTestOnBorrow(TEST_ON_BORROW);
    	jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
	}
	
	/**
	 * 从jedis连接池中获取
	 * @return
	 */
    public synchronized static Jedis getJedis() {
    	if(null == jedisPool) {
    		logger.error("Jedis pool is not initialized.");
    		return null;
    	}
    	return jedisPool.getResource();
    }
    
    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
    
    
    
    
}
