多线程版--二次判断--性能高

public class Singleton {
    private volatile static Singleton instance = null;
    private Singleton() {

    }
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

注意：
首先会检查实例是否创建，如果没有，才进行同步，保证只有在第一次时会同步。避免了开销。

volatile关键字的作用：修饰的共享变量，可以保证可见性，部分保证顺序性。

实例化对象instance = new Singleton();
可以分成三个步骤：
1.分配内存空间
2.初始化对象
3.将对象指向刚分配的内存空间

但是有些编译器为了性能的原因，可能会将第二步和第三步进行重排序，顺序就成了：
1.分配内存空间
2.将对象指向刚分配的内存空间
3.初始化对象

如果顺序1->2->3顺序变为，1->3->2.此时1->3时，对象初始化并未完成，
但是此时另一个线程又判断instance == null，又开始了对象的初始化工作。
使用volatile关键字可以解决在这个问题。
