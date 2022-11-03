package roc.redis.clients.jedis;

import org.junit.Test;
import redis.clients.jedis.util.RedisInputStream;
import redis.clients.jedis.util.RedisOutputStream;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionTestRoc {


  @Test
  public void connection() throws IOException {

    /**
     * 建立Socket连接
     */
    Socket socket = new Socket();

    socket.setReuseAddress(true);
    socket.setKeepAlive(true); // Will monitor the TCP connection is valid
    socket.setTcpNoDelay(true); // Socket buffer Whetherclosed, to ensure timely delivery of data
    socket.setSoLinger(true, 0); // Control calls close () method, the underlying socket is closed immediately
    socket.connect(new InetSocketAddress("localhost", 6384), 500);

    int soTimeout = socket.getSoTimeout();

    /**
     * 获取stream
     */
    RedisOutputStream outputStream = new RedisOutputStream(socket.getOutputStream());
    RedisInputStream inputStream = new RedisInputStream(socket.getInputStream());

  }
}
