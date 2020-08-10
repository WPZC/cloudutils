package dbtest.controller;

import dbtest.entity.Test;
import dbtest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/10 17:35
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/findByAll")
    @ResponseBody
    public List<Test> findByAll(){

        return testService.findByAll();

    }

}
