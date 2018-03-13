package design.module;

/**
 * 单例模式集合
 */
public class Singleton {
    public static void main(String[] args) {
        LazySingleton.InnerClass.getInstance();
    }
}

/**
 * 饿汉单例模式
 */
class HungrySingleton {
    {
        System.out.println("我是饿汉单例模式");
    }
    private static HungrySingleton hungrySingleton= new HungrySingleton();
    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }
}

/**
 * 懒汉单例
 */
class LazySingleton{
    private static LazySingleton lazySingleton;

    /**
     * 线程安全(耗时)
     * @return
     */
    public synchronized static LazySingleton getInstance() {
        System.out.println("线程安全(耗时)");
        lazySingleton = new LazySingleton();
        return lazySingleton;
    }

    /**
     * 双重检验模型
     * @return
     */
    public static LazySingleton getInstance2() {
        if (lazySingleton == null) {
            synchronized(LazySingleton.class) {
                if (lazySingleton == null) {
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }

    /**
     * 内部静态类
     */
    static class InnerClass {
        static LazySingleton getInstance() {
            System.out.println("内部静态类实现的单例模式");
            lazySingleton = new LazySingleton();
            return lazySingleton;
        }
    }
}

/**
 * 枚举单例模式
 */
enum Instance {
    INSTANCE;
}

