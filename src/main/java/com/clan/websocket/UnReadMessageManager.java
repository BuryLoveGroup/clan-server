package com.clan.websocket;

import com.clan.bean.socket.ChatMessage;
import com.clan.utils.Keys;
import com.clan.redis.Redis;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by robot on 2017/11/29.
 */
public class UnReadMessageManager {

    public static List<String> getUnReadMessages(Long uid) {

        Jedis redisClient = Redis.getJedis();
        List<String> lrange = null;
        try {
            lrange = redisClient.lrange(Keys.UnRead(uid), 0, -1);
        }catch (Exception e){
            //todo logger
        }finally {
            redisClient.close();
        }
        return lrange;
    }

    public static void addUnReadMessage(Long uid, String chatMessage){

        Jedis redisClient = Redis.getJedis();
        try{
            redisClient.lpush(Keys.UnRead(uid), chatMessage);
        }catch (Exception e){
            //todo logger
        }finally {
            redisClient.close();
        }

    }
}
