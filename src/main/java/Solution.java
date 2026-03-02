import java.util.LinkedList;
import java.util.Random;

class Solution {

    private LinkedList<Integer> list = new LinkedList<>();

    public Solution() {
        new Consumer().start();
        new Provider().start();
    }

    /**
     * 消费者
     */
    class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                synchronized (list) {
                    if (list.size() > 0) {
                        System.out.println(list.removeFirst());
                    }
                }
            }
        }
    }

    /**
     * 生产者
     */
    class Provider extends Thread {
        Random random = new Random();

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                synchronized (list) {
                    list.addLast(random.nextInt(100));
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("hello");
    }
}
