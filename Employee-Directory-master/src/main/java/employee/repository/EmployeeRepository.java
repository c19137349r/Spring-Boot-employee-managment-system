package employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	// sort employees
	public List<Employee> findAllByOrderByFirstNameAsc();

}
