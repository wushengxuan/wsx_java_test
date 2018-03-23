package hotswap;

/**
 * 修改class文件 暂时只提供修改常量池常量的功能
 */
public class ClassModifier {

    /**
     * class 文件中常量池的起始偏移量
     */
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;

    /**
     * CONSTANT_UTF8_INFO常量的tag标志
     */
    private static final int CONSTANT_UTF8_INFO = 1;

    /**
     * 常量池中11种常量所占用的长度 CONSTANT_UTF8_INFO除外 因为他是不定长的
     */
    private static final int[] CONSTANT_ITEM_LENGTH = {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5};

    private static final int u1 = 1;
    private static final int u2 = 2;

    private byte[] classByte;

    public ClassModifier(byte[] classByte) {
        this.classByte = classByte;
    }

    /**
     * 修改常量池中 CONSTANT_UTF8_INFO常量的内容
     */
    public byte[] modifyUTF8Constant(String oldStr, String newStr) {
        int cpc = getConstantPoolCount();
        int offset = CONSTANT_POOL_COUNT_INDEX + u2;
        for (int i = 0; i < cpc; i++) {
            int tag = ByteUtils.bytesToInt(classByte, offset, u1);
            if (tag == CONSTANT_UTF8_INFO) {
                int len = ByteUtils.bytesToInt(classByte, offset + u1, u2);
                offset += (u1 + u2);
                String str = ByteUtils.byteToString(classByte, offset, len);
                if(str.equalsIgnoreCase(oldStr)) {
                    byte[] strBytes = ByteUtils.stringToByte(newStr);
                    byte[] strLen = ByteUtils.intToByte(newStr.length(), u2);
                    classByte = ByteUtils.byteReplace(classByte, offset-u2, u2,strLen);
                    classByte = ByteUtils.byteReplace(classByte, offset, len,strBytes);
                    return classByte;
                } else {
                    offset += len;
                }
            } else {
                offset += CONSTANT_ITEM_LENGTH[tag];
            }
        }
        return classByte;
    }

    public int getConstantPoolCount() {
        return ByteUtils.bytesToInt(classByte, CONSTANT_POOL_COUNT_INDEX, u2);
    }
}
