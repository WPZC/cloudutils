package dbtest.test;

import methods.thread.ThreadFactory;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/14 14:26
 */
public class T1 {

    public static void main(String[] args){

        for (int i = 0;i<2;i++){
            int finalI = i;
            ThreadFactory.threadPoolExecutorTask(new Runnable() {
                @Override
                public void run() {
                    ThreadLoadMsg.YEARS_THREAD_LOCAL.set("2020");
                    try {
                        if(finalI ==0){
                            Thread.sleep(3000);
                        }else{
                            ThreadLoadMsg.YEARS_THREAD_LOCAL.set("2027");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ThreadLoadMsg.YEARS_THREAD_LOCAL.get());

                }
            });
        }
    }

}
