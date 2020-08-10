package securitypermission_db.authority.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import securitypermission_db.commondb.entity.PageData;

import java.util.List;

/**
 * 基础server
 * @author wqy
 * @version 1.0
 * @date 2020/6/11 18:34
 */
public abstract class BaseService<T,K extends JpaRepository> {

    K k;

    public BaseService(K k){
        this.k = k;
    }


    /**
     * 分页查询
     * @param currentPage
     * @return
     */
    public PageData<T> findPages(Integer currentPage, Integer pagesize){

        //Sort.Direction是个枚举有ASC(升序)和DESC(降序)
        Sort.Direction sort =  Sort.Direction.ASC;
        //PageRequest继承于AbstractPageRequest并且实现了Pageable
        //获取PageRequest对象 index:页码 从0开始  size每页容量 sort排序方式 "id"->properties 以谁为准排序
        Pageable pageable = PageRequest.of((currentPage-1)>=0?(currentPage-1):0, pagesize, sort, "id");

        Page<T> page = k.findAll(pageable);
        ///获取该分页的列表
        List<T> list = page.getContent();

        PageData<T> pageData = new PageData<>();
        //获取总页数
        pageData.setPagesize(page.getTotalPages());
        //获取总元素个数
        pageData.setTotal(page.getTotalElements());
        pageData.setListData(list);


        return pageData;
    }


}
