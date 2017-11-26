package redis;

import com.clan.redis.Redis;
import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by robot on 2017/11/6.
 */
public class RedisConnTest {

    @Test
    public void testRedis(){
        Jedis jedis = Redis.getJedis();
        jedis.set("test","hello");
        jedis.close();
    }
}
