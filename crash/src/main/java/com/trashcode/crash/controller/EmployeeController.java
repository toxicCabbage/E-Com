// package com.trashcode.crash.controller;

// import java.util.List;

// import com.trashcode.crash.user_entity.Employee;
// import com.trashcode.crash.user_repository.EmployeeRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.servlet.ModelAndView;




// @Controller
// public class EmployeeController {
	
// 	@Autowired
// 	private EmployeeRepository eRepo;
	
// 	@GetMapping({"/showEmployess", "/","/list"})
// 	public ModelAndView showEmployees() {
// 		ModelAndView mav = new ModelAndView("List-employees");
// 		List <Employee> list = eRepo.findAll();
// 		mav.addObject("employess", list);
      
// 		return mav;
// 	}

// }
