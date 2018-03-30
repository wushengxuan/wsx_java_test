package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 同步非阻塞模型
 */
public class BioDemo {
    public static void main(String[] args) throws InterruptedException, IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);

        final List<SocketChannel> socketChannelList = new LinkedList<>();

        new Thread(() -> {
            while (true) {
                // 处理 每个连接是否可读, 这里的逻辑是 读4个字节后切断连接
                for (SocketChannel socketChannel : new ArrayList<>(socketChannelList)) {
                    try {
                        ByteBuffer buf = ByteBuffer.allocate(4);
                        int readed = socketChannel.read(buf);
                        System.out.println(readed);
                        System.out.println(Arrays.toString(buf.array()));
                        if (readed > 0 && buf.array()[0] == 'q') {
                            // close
                            socketChannel.close();
                            // remove from list
                            socketChannelList.remove(socketChannel);
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }, "server-handler-thread").start();

// 等待新连接连进来
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                socketChannelList.add(socketChannel);
            }
            Thread.sleep(1000);
        }
    }
}
