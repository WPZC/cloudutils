package com.cloudutils.jpatemplate.dao;

import com.cloudutils.jpatemplate.dao.domain.BaseDoMain;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/10 15:05
 */
@NoRepositoryBean
public interface BaseDao<T extends BaseDoMain,ID> extends JpaRepositoryImplementation<T, ID> {

}
