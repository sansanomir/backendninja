package com.udemy.backendninja.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.udemy.backendninja.entity.Course;
import com.udemy.backendninja.entity.QCourse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("queryDSLExampleRepo")
public class QueryDSLExampleRepo {
    private QCourse qCourse = QCourse.course;

    @PersistenceContext
    public EntityManager em;

    public Course find(boolean exist){
        JPAQuery<Course> query = new JPAQuery<Course>();

        BooleanBuilder predicate = new BooleanBuilder(qCourse.description.endsWith("OP"));

        if(exist){
            predicate.and(qCourse.id.eq(23));
        }
        else {
            predicate.and(qCourse.name.endsWith("OP"));
        }

        Course course = query.select(qCourse).from(qCourse).where(predicate).fetchOne();

        List<Course> courses = query.select(qCourse).from(qCourse).where(qCourse.hours.between(20,50)).fetch();

        return course;
    }
}
