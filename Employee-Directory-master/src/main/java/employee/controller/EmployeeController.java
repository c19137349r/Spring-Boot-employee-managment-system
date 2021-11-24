package employee.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import employee.entity.Employee;
import employee.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService empService;

	public EmployeeController(EmployeeService empService) {
		this.empService = empService;
	}

	@GetMapping("/list")
	public String ListEmployees(Model model) {
		List<Employee> empList = empService.findAll();
		model.addAttribute("empList", empList);
		
		return "employees/list-employees"; 
		
	}
	
	@GetMapping("/add-employee")
	public String addEmployee(Model model) {
		Employee emp = new Employee();
		model.addAttribute("employee", emp);
		
		return "employees/employee-form";
	}
	
	@PostMapping("/save-employee")
	public String saveEmployee(@ModelAttribute("employee") Employee emp) {
		empService.save(emp);
		
		return "redirect:/employees/list";
		
	}
	
	@PostMapping("/update-employee")
	public String updateEmployee(@RequestParam("employeeId") int id, Model model) {
		model.addAttribute("employee", empService.findById(id));
		
		return "employees/employee-form";
	}
	
	@PostMapping("/delete-employee")
	public String deleteEmployee(@RequestParam("employeeId") int id) {
		empService.deleteById(id);
		
		return "redirect:/employees/list";
	}

}
