package put.poznan.tsdcache.task;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

public class RedisTaskQueue implements TaskQueue {

    private final ListOperations<String, String> redisOperations;

    public RedisTaskQueue(RedisTemplate<String, String> redis) {
        this.redisOperations = redis.opsForList();
    }

    // TODO 3.1 - Implement this method
    @Override
    public void push(String user, String task) {
        redisOperations.rightPush(getKey(user), task);
    }

    // TODO 3.1 - Implement this method
    @Override
    public String pop(String user) {
        String task = redisOperations.leftPop(getKey(user));
        if (task != null) {
            redisOperations.getOperations().expire(getKey(user), Duration.ofDays(1));
        }
        return task;
    }

    // TODO 3.1 - Implement this method
    @Override
    public void clear(String user) {
        redisOperations.getOperations().delete(getKey(user));
    }

    // --- NOTICE ---
    // Use this method whenever you perform any operation
    private static String getKey(String username) {
        return "task:" + username;
    }
}
