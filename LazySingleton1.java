public class Singleton { 
	private static Singleton instance = null; 
	private Singleton() {
	} 
	public static Singleton getInstance() { 
		if (instance == null) { 
			instance = new Singleton(); 
			}
		return instance; 
	} 
}

问题：
例如两个线程同时进入如下代码块，那么会new出两个不同的实例，那就违背了对象只有一个的目的。
