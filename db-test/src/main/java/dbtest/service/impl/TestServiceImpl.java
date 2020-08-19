package dbtest.service.impl;

import com.cloudutils.jpatemplate.dao.BaseDao;
import com.cloudutils.jpatemplate.service.BaseService;
import com.sun.xml.bind.v2.model.core.ID;
import dbtest.dao.TestJpa;
import dbtest.entity.Test;
import dbtest.service.TestService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/10 17:23
 */
@Service
public class TestServiceImpl extends BaseService<Test, TestJpa> implements TestService {


    /**
     * 注入仓库位置
     * {@link BaseDao}
     *
     * @param testJpa
     */
    public TestServiceImpl(TestJpa testJpa) {
        super(testJpa);
    }

    @Override
    public List<Test> findByAll(){

        return k().findAll();

    }

    @Override
    public void findByPage() {

        HashMap<String,List<Object>> map = new HashMap<>();
        List<Object> list = new ArrayList<>();
        list.add("2");
        map.put("field3",list);
        Specification<Test> specification = createSpecification(map,"eq");
        Page<Test> page = findPages(1,5,specification);

        System.out.println(page.getTotalElements());

    }
}
