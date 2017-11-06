package redis;

import com.clan.redis.ConnectionPool;
import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by robot on 2017/11/6.
 */
public class RedisConnTest {

    @Test
    public void testRedis(){
        Jedis jedis = ConnectionPool.getJedis();
        jedis.set("test","hello");
        jedis.close();
    }
}
