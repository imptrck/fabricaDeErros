package deadlock;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class FactoryDeadLock implements Runnable {

	public int i = 1;
    static Object x = new Object(), y = new Object();
    public void run() {
        System.out.println("current thread=" + i);
        if (i == 0) {
            synchronized (x) { 
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
                synchronized (y) {
                    System.out.println("locked y");
                }
            }
        }
        if (i == 1) {
            synchronized (y) { 
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
                synchronized (x) {
                    System.out.println("locked x");
                }
            }
        }
    }
    
    public void generateDeadLock(){
    	
    	FactoryDeadLock test0 = new FactoryDeadLock();
    	FactoryDeadLock test1 = new FactoryDeadLock();
        test0.i = 0;
        test1.i = 1;
        Thread t1 = new Thread(test0);
        Thread t2 = new Thread(test1);
        t1.start();
        t2.start();
    	
    }
    
    public static void main(String[] args) {
    	FactoryDeadLock test0 = new FactoryDeadLock();
    	FactoryDeadLock test1 = new FactoryDeadLock();
        test0.i = 0;
        test1.i = 1;
        Thread t1 = new Thread(test0);
        Thread t2 = new Thread(test1);
        t1.start();
        t2.start();
    }

}
