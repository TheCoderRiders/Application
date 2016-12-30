package com.self.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * Created by akash.p on 30/12/16.
 */
@Repository
@Transactional
public class SearchRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<String> getCriteriaByRestrictions(String searchKey,Class<?> dtoClass,String criteriaField) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(dtoClass).add(Restrictions.ilike(criteriaField, searchKey, MatchMode.ANYWHERE));
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property(criteriaField));
        criteria.setProjection(projectionList);
        return criteria.list();
    }
}
