package com.nestordfi.backendninja.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nestordfi.backendninja.model.Person;

@Controller
@RequestMapping("example")
public class ExampleController {
	
	public static final String EXAMPLE_VIEW="example";

//	@RequestMapping(value="/exampleMAV" , method=RequestMethod.GET)
	@GetMapping("/exampleString")
	public String exampleString(Model model) {
//		model.addAttribute("person",new Person("Nf7", 24));
		model.addAttribute("people",getPersons());
		return EXAMPLE_VIEW;
	}
	
	@GetMapping("/exampleMAV")
	public ModelAndView exampleMAV() {
		ModelAndView mav = new ModelAndView(EXAMPLE_VIEW);
		mav.addObject("people",getPersons());
		return mav;
	}
	
	private List<Person> getPersons(){
		List<Person> people = new ArrayList<>();
		people.add(new Person("Nf7", 24));
		people.add(new Person("MIKEL", 30));
		people.add(new Person("EVA", 43));
		people.add(new Person("WALLY", 28));
		return people;
	}
	
}
