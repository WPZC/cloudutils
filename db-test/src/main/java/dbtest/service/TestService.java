package dbtest.service;

import dbtest.entity.Test;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/10 17:23
 */
public interface TestService {

    List<Test> findByAll();

    void findByPage();

}
