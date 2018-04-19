package NIO;

import java.nio.ByteBuffer;

public class Work {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte)1);
        buffer.put((byte)2);
        buffer.put((byte)3);
        buffer.put((byte)4);
        buffer.put((byte)5);
        buffer.position(3);
        buffer.limit(5);
        ByteBuffer buffer1 = buffer.slice();
        buffer1.put(0, (byte) 22);
        buffer1.put(1, (byte) 23);
        while (buffer1.remaining() > 0) {
            System.out.println(buffer1.get());
        }
    }
}
