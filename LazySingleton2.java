多线程版--性能低

public class LazySingleton {
    private static Singleton instance = null;
    private Singleton() {

    }
    public synchronized static Singleton getInstance() {
        if (instance == null) {
                instance = new Singleton();
        }
        return instance;
    }
}
问题：
public synchronized static Singleton getInstance()保证了线程安全性；
但是只有在第一次执行此方法时，才真正需要同步，
所以一旦初始化完毕后，就每次调用这个方法，同步都是多余的。增加了开销。
