package io.github.kookybot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import io.github.kookybot.client.Client;
import io.github.kookybot.contract.Self;
import io.github.kookybot.events.EventHandler;
import io.github.kookybot.events.Listener;
import io.github.kookybot.events.channel.ChannelMessageEvent;

public class Main {
    // Channel Message Listener / 频道消息监听器
    public static class ChannelMessageListener implements Listener {
        @SuppressWarnings("unused")
        @EventHandler
        // Received Channel Message Event / 收到频道消息事件
        public void onChannelMessage(ChannelMessageEvent event) {
            // Add a listener for channel messages / 添加一个监听器以侦听频道消息
            if (event.getContent().equals("hello")) {
                // Send "Hello, world!" to the channel / 发送 "Hello, world!" 到频道
                event.getChannel().sendMessage("Hello, world!", null);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // Read the KOOK bot token / 读取 KOOK bot token
        String token = new StringTokenizer(
                new BufferedReader(
                        new FileReader("data/token.txt")
                ).readLine()
        ).nextToken();
        // Create a new KOOK bot client / 创建一个新的 KOOK bot 客户端
        Client client = new Client(token, configure -> {
            // Register default Brigadier commands / 注册默认 Brigadier 命令
            configure.withDefaultCommands();
            return null;
        });
        // Start the KOOK bot client / 启动 KOOK bot 客户端
        @SuppressWarnings("unused")
        Self self = JavaBaseClass.utils.connectWebsocket(client);
        // Add a listener for channel messages / 添加一个监听器以侦听频道消息
        client.getEventManager().addClassListener(new ChannelMessageListener());
    }
}
