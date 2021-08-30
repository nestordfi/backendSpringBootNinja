package com.nestordfi.backendninja.repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.nestordfi.backendninja.entity.Course;
import com.nestordfi.backendninja.entity.QCourse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("queryDSLExampleRepo")
public class QueryDSLExampleRepo {

	private QCourse qcourse = QCourse.course;
	
	@PersistenceContext
	private EntityManager em;
	
	public Course find(boolean exist) {
		JPAQuery<Course> query = new JPAQuery<Course>(em);
		
		Predicate predicate1 = qcourse.description.endsWith("OP");
		
		BooleanBuilder predicateBuilder = new BooleanBuilder(predicate1);
		
		
		if (exist) {
			Predicate predicate2 =  qcourse.id.eq(23);
			predicateBuilder.and(predicate2);
		}else {
			Predicate predicate3 =  qcourse.name.endsWith("F7");
			predicateBuilder.or(predicate3);
		}
		Course course1 = query.select(qcourse).from(qcourse).where(predicateBuilder).fetchOne();
		
		return course1;
		
//		List<Course> courses = query.select(qcourse).from(qcourse).where(qcourse.hours.between(20, 50)).fetch();
	}
	
}
