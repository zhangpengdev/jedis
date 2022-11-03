package roc.redis.clients.jedis;

import org.junit.Test;
import redis.clients.jedis.CommandArguments;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.args.Rawable;
import redis.clients.jedis.commands.ProtocolCommand;
import redis.clients.jedis.exceptions.JedisConnectionException;
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
    socket.connect(new InetSocketAddress("localhost", 6379), 500);

    int soTimeout = socket.getSoTimeout();

    /**
     * 获取stream
     */
    RedisOutputStream outputStream = new RedisOutputStream(socket.getOutputStream());
    RedisInputStream inputStream = new RedisInputStream(socket.getInputStream());

    ProtocolCommand command = Protocol.Command.GET;
    CommandArguments args = new CommandArguments(command);
    sendCommand(outputStream, args);
  }


  public static final byte DOLLAR_BYTE = '$';
  public static final byte ASTERISK_BYTE = '*';
  public static void sendCommand(final RedisOutputStream os, CommandArguments args) {
    try {
      os.write(ASTERISK_BYTE);
      os.writeIntCrLf(args.size());
      for (Rawable arg : args) {
        os.write(DOLLAR_BYTE);
        final byte[] bin = arg.getRaw();
        os.writeIntCrLf(bin.length);
        os.write(bin);
        os.writeCrLf();
      }
    } catch (IOException e) {
      throw new JedisConnectionException(e);
    }
  }
}
