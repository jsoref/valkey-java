package io.valkey.util;

import io.valkey.Jedis;

public class ClientKillerUtil {

  public static void killClient(Jedis jedis, String clientName) {
    for (String clientInfo : jedis.clientList().split("\n")) {
      if (clientInfo.contains("name=" + clientName)) {
        // Ugly, but c'mon, it's a test.
        String hostAndPortString = clientInfo.split(" ")[1].split("=")[1];
        // It would be better if we kill the client by ID as it's safer but jedis doesn't implement
        // the command yet.
        jedis.clientKill(hostAndPortString);
      }
    }
  }

  public static void tagClient(Jedis j, String name) {
    j.clientSetname(name);
  }
}
