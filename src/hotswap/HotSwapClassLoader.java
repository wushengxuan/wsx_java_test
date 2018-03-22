package hotswap;

/**
 * 自定义类加载器
 */
public class HotSwapClassLoader extends ClassLoader{
    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }
}
