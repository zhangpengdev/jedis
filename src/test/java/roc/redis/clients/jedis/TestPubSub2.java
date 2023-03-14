package roc.redis.clients.jedis;

import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 发布订阅测试
 * https://developer.aliyun.com/article/371896
 */
public class TestPubSub2 {

    protected static final HostAndPort hnp = RocHostAndPorts.getRedisServers().get(0);

    public static Jedis createJedis() {
        // return new Jedis(hnp, DefaultJedisClientConfig.builder().timeoutMillis(500).password("foobared").build());
        return new Jedis(hnp, DefaultJedisClientConfig.builder().timeoutMillis(500).build());
    }

    public static void main(String[] args) {
//        final JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.75.131");

        //两个订阅者
        new Thread(() -> {
//            Jedis jedis = pool.getResource();
            Jedis jedis = createJedis();
            MyListener listener = new MyListener();
            jedis.subscribe(listener, "channel01");

        }).start();

        new Thread(() -> {
//            Jedis jedis = pool.getResource();
            Jedis jedis = createJedis();
            MyListener listener = new MyListener(); //listener为JedisPubSub对象，监听类
            jedis.subscribe(listener, "channel01");

        }).start();

        //一个发布者
//        new Thread(() -> {
//            //注意，订阅使用的jedis对象与发布使用的jedis对象不能相同，否者运行时会报错
////            Jedis jedis = pool.getResource();
//            Jedis jedis = createJedis();
//            while(true){
//                try {
//                    TimeUnit.SECONDS.sleep(3);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                String msg = new Date().toLocaleString() + " --- " + UUID.randomUUID();
//                System.out.println("publish a msg:" + msg);
//                jedis.publish("channel01", msg);
//            }
//
//        }).start();

        //pool.close();
    }

}
