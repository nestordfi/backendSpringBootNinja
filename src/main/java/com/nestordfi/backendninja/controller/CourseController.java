package com.nestordfi.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nestordfi.backendninja.converter.CourseConverter;
import com.nestordfi.backendninja.entity.Course;
import com.nestordfi.backendninja.model.CourseModel;
import com.nestordfi.backendninja.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {

	private static final Log LOG = LogFactory.getLog(CourseController.class);

	private static final String COURSES_VIEW = "courses";

	@Autowired
	@Qualifier("courseServiceImpl")
	private CourseService courseService;

	@Autowired
	@Qualifier("courseConverter")
	private CourseConverter courseConverter;

	@GetMapping("/listcourses")
	public ModelAndView listAllCourses() {
		LOG.info("Call: listAllCourses()");
		ModelAndView mav = new ModelAndView(COURSES_VIEW);
		mav.addObject("course", new CourseModel());
		mav.addObject("courses", courseConverter.entity2Model(courseService.listAllCourses()));
		return mav;

	}

	@PostMapping("/addcourse")
	public String addCourse(@ModelAttribute("course") CourseModel course) {
		LOG.info("Call: addCourse() Course : " + course.toString());
		Course courseFinded = null;
		if (course.getId() != 0) {
			courseFinded = courseService.findCourseById(course.getId());
			if (courseFinded != null) {
				courseFinded.setName(course.getName());
				courseFinded.setDescription(course.getDescription());
				courseFinded.setPrice(course.getPrice());
				courseFinded.setHours(course.getHours());
				courseService.updateCourse(courseFinded);
			}else {
				courseService.addCourse(courseConverter.model2Entity(course));				
			}
		}else {
			courseService.addCourse(courseConverter.model2Entity(course));
		}
		return "redirect:/courses/listcourses";
	}
	
	@PostMapping("/deletecourse")
	public String deleteCourse(@ModelAttribute("course") CourseModel course) {
		LOG.info("Call: deleteCourse() Course : " + course.toString());
		courseService.removeCourse(course.getId());
		return "redirect:/courses/listcourses";
	}

}
