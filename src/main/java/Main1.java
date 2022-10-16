import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Set;

public class Main1 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("hadoop101", 6379);
        jedis.auth("123456");
        jedis.flushDB();
        jedis.set("k1", "v1");
        Set<String> keys = jedis.keys("*");
        keys.stream().forEach(e -> System.out.println(e));
        jedis.rpush("list1", "lv1", "lv2", "lv3", "lv4", "lv5");
        List<String> lrange = jedis.lrange("list1", 0, -1);
        lrange.stream().forEach(System.out::println);
        System.out.println(jedis.configGet("dir"));
        String replication = jedis.info("replication");
        System.out.println(replication);
        jedis.close();
    }
}
