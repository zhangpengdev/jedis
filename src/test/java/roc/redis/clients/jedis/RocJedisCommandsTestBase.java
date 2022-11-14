package roc.redis.clients.jedis;

import org.junit.After;
import org.junit.Before;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

public abstract class RocJedisCommandsTestBase {

  protected static final HostAndPort hnp = RocHostAndPorts.getRedisServers().get(0);

  protected Jedis jedis;

  public RocJedisCommandsTestBase() {
    super();
  }

  @Before
  public void setUp() throws Exception {
    jedis = createJedis();
    jedis.flushAll();
  }

  @After
  public void tearDown() throws Exception {
    jedis.close();
  }

  protected Jedis createJedis() {
    // return new Jedis(hnp, DefaultJedisClientConfig.builder().timeoutMillis(500).password("foobared").build());
    return new Jedis(hnp, DefaultJedisClientConfig.builder().timeoutMillis(500).build());
  }
}
