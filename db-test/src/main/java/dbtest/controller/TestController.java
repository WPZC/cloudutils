package dbtest.controller;

import dbtest.entity.Test;
import dbtest.service.TestService;
import lombok.extern.slf4j.Slf4j;
import methods.thread.ThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redistemplate.utils.RedisTemplateUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/10 17:35
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private TestService testService;
    @Resource
    private RedisTemplateUtils redisTemplateUtils;

    @RequestMapping(value = "/findByAll")
    @ResponseBody
    public List<Test> findByAll(){

        testService.findByPage();

        return testService.findByAll();

    }

    @RequestMapping(value = "/exce")
    @ResponseBody
    public void exce(){

        ThreadFactory.threadPoolExecutorTask(new Runnable() {
            @Override
            public void run() {
                log.info("我是一个粉刷匠");
                //System.out.println("我是一个粉刷匠");
            }
        });
        ThreadFactory.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                log.info("我是第二个粉刷匠");
            }
        },5,10, TimeUnit.SECONDS);

    }
    @RequestMapping(value = "/redisSet")
    @ResponseBody
    public void redisSet(){

        AtomicInteger tc = new AtomicInteger();

        for (int i = 0;i<10;i++){

            int finalI = i;
            ThreadFactory.threadPoolExecutorTask(()->{
                if(finalI==3){
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                boolean b = redisTemplateUtils.lock("lock_1",null);
                if(b){
                    tc.set(finalI);
                    log.info(tc.toString()+"-抢到了锁");
                    try {
                        if(finalI ==tc.get()){
                            Thread.sleep(5000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //释放锁
                    boolean bs = redisTemplateUtils.delete("lock_1");
                    if(bs){
                        log.info(finalI+"-锁释放");
                    }else{
                        log.info("锁释放失败");
                    }

                }else{
                    log.info("锁被抢走了");
                }
            });

        }
    }

    @RequestMapping(value = "/redisGet")
    @ResponseBody
    public Object redisGet(String key){
        return redisTemplateUtils.get(key);
    }

}
