package com.nestordfi.backendninja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nestordfi.backendninja.entity.Course;
import com.nestordfi.backendninja.repository.CourseJpaRepository;
import com.nestordfi.backendninja.service.CourseService;

@Service("courseServiceImpl")
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	@Qualifier("courseJpaRepository")
	private CourseJpaRepository courseJpaRepository;

	@Override
	public List<Course> listAllCourses() {
		return courseJpaRepository.findAll();
	}

	@Override
	public Course addCourse(Course course) {
		return courseJpaRepository.save(course);
	}

	@Override
	public int removeCourse(int id) {
		int r = 0;
		courseJpaRepository.deleteById(id);
		return r;
	}

	@Override
	public Course updateCourse(Course course) {
		return courseJpaRepository.save(course);
	}
	
	

}
