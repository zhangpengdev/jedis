package roc.redis.clients.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Protocol;

import java.util.ArrayList;
import java.util.List;

public final class RocHostAndPorts {

  //  private static final String HOST = "localhost";
  private static final String HOST = "192.168.75.131";
  
  private static List<HostAndPort> redisHostAndPortList = new ArrayList<>();
  private static List<HostAndPort> sentinelHostAndPortList = new ArrayList<>();
  private static List<HostAndPort> clusterHostAndPortList = new ArrayList<>();

  static {
    redisHostAndPortList.add(new HostAndPort(HOST, Protocol.DEFAULT_PORT));
    redisHostAndPortList.add(new HostAndPort(HOST, Protocol.DEFAULT_PORT + 1));
    redisHostAndPortList.add(new HostAndPort(HOST, Protocol.DEFAULT_PORT + 2));
    redisHostAndPortList.add(new HostAndPort(HOST, Protocol.DEFAULT_PORT + 3));
    redisHostAndPortList.add(new HostAndPort(HOST, Protocol.DEFAULT_PORT + 4));
    redisHostAndPortList.add(new HostAndPort(HOST, Protocol.DEFAULT_PORT + 5));
    redisHostAndPortList.add(new HostAndPort(HOST, Protocol.DEFAULT_PORT + 6));
    redisHostAndPortList.add(new HostAndPort(HOST, Protocol.DEFAULT_PORT + 7));
    redisHostAndPortList.add(new HostAndPort(HOST, Protocol.DEFAULT_PORT + 8));
    redisHostAndPortList.add(new HostAndPort(HOST, Protocol.DEFAULT_PORT + 9));
    redisHostAndPortList.add(new HostAndPort(HOST, Protocol.DEFAULT_PORT + 10));

    sentinelHostAndPortList.add(new HostAndPort(HOST, Protocol.DEFAULT_SENTINEL_PORT));
    sentinelHostAndPortList.add(new HostAndPort(HOST, Protocol.DEFAULT_SENTINEL_PORT + 1));
    sentinelHostAndPortList.add(new HostAndPort(HOST, Protocol.DEFAULT_SENTINEL_PORT + 2));
    sentinelHostAndPortList.add(new HostAndPort(HOST, Protocol.DEFAULT_SENTINEL_PORT + 3));
    sentinelHostAndPortList.add(new HostAndPort(HOST, Protocol.DEFAULT_SENTINEL_PORT + 4));

    clusterHostAndPortList.add(new HostAndPort(HOST, 7379));
    clusterHostAndPortList.add(new HostAndPort(HOST, 7380));
    clusterHostAndPortList.add(new HostAndPort(HOST, 7381));
    clusterHostAndPortList.add(new HostAndPort(HOST, 7382));
    clusterHostAndPortList.add(new HostAndPort(HOST, 7383));
    clusterHostAndPortList.add(new HostAndPort(HOST, 7384));
  }

  public static List<HostAndPort> parseHosts(String envHosts,
      List<HostAndPort> existingHostsAndPorts) {

    if (null != envHosts && 0 < envHosts.length()) {

      String[] hostDefs = envHosts.split(",");

      if (null != hostDefs && 2 <= hostDefs.length) {

        List<HostAndPort> envHostsAndPorts = new ArrayList<>(hostDefs.length);

        for (String hostDef : hostDefs) {

          envHostsAndPorts.add(HostAndPort.from(hostDef));
        }

        return envHostsAndPorts;
      }
    }

    return existingHostsAndPorts;
  }

  public static List<HostAndPort> getRedisServers() {
    return redisHostAndPortList;
  }

  public static List<HostAndPort> getSentinelServers() {
    return sentinelHostAndPortList;
  }

  public static List<HostAndPort> getClusterServers() {
    return clusterHostAndPortList;
  }

  private RocHostAndPorts() {
    throw new InstantiationError("Must not instantiate this class");
  }
}
