package hotswap;

/**
 * 自定义类加载器
 */
public class HotSwapClassLoader extends ClassLoader{

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    /**
     * 工具byte数组 加载字节码对应的类
     * @param classByte
     * @return
     */
    public Class loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }
}
