package employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import employee.repository.EmployeeRepository;
import employee.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository empRep;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository empRep) {
		this.empRep = empRep;
	}
	
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		
		return empRep.findAllByOrderByFirstNameAsc();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		Optional<Employee> opEmp = empRep.findById(theId);
		Employee emp = null;
		
		if(opEmp.isPresent()) 
			emp = opEmp.get();
		else 
			throw new RuntimeException("Did not find Employee id: " + theId);
		
		return emp;
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		
		empRep.save(theEmployee);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		
		empRep.deleteById(theId);
	}

}






