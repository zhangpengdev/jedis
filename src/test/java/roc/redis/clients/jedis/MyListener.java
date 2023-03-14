package roc.redis.clients.jedis;

import redis.clients.jedis.JedisPubSub;

class MyListener extends JedisPubSub {
    public void onMessage(String channel, String message) {
        System.out.println("get a msg: " + "channel=" + channel + ", message=" + message);
    }

    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("channel:" + channel + ", subscribedChannels:" + subscribedChannels);
    }

    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("channel:" + channel + ", subscribedChannels:" + subscribedChannels);
    }

    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("pattern:" + pattern + ", subscribedChannels:" + subscribedChannels);
    }

    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        System.out.println("pattern:" + pattern + ", subscribedChannels:" + subscribedChannels);
    }

    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("pattern:" + pattern + ", channel:" + channel + ", message:" + message);
    }
}
