package dbtest.controller;

import dbtest.entity.Test;
import dbtest.service.TestService;
import lombok.extern.slf4j.Slf4j;
import methods.thread.ThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @RequestMapping(value = "/findByAll")
    @ResponseBody
    public List<Test> findByAll(){

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

}
