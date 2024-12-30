package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Employee;

@RestController
public class RestEmployeesController {
	private static final List <Employee> employees = new ArrayList<>();
	
	static {
		employees.add(new Employee(1L,"JUAN","PEREZ","DEVELOPER"));
		employees.add(new Employee(2L,"LUIS","VALLEJOS","CONTADOR"));
		employees.add(new Employee(1L,"CARLA","HUCHANI","ARQUITECTA"));
		employees.add(new Employee(2L,"ENRRIQUE","VARGAS","ING DE SISTEMAS"));
		
	}
	@GetMapping("/")
	public String nombre() {
		return "Prueba de ruta";
	}

	@GetMapping("/employees")
	public List <Employee> empleados() {
		return employees;
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable Long id) {
		return employees.stream().filter(employee -> employee.getId().equals(id)).findFirst().orElse(null);
	}
	
	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		Employee employe = employees.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
		if(employe != null) {
			employees.remove(employe);
			return "Empleado eliminado correctamente";
		}else {
		 return "Empleado no encontrado";	
		}
	}
	
	@PostMapping("/employee")
	public String addEmployee(@RequestBody Employee employee) {
		Long id = employees.stream().mapToLong(Employee::getId).max().orElse(0)+1;
		employee.setId(id);
		employees.add(employee);
		return "Empleado añadido correctamente";
	}
	
	@PutMapping("/employee/{id}")
	public String updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		Employee employeeToUpdate = employees.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
		if(employeeToUpdate!=null) {
			employeeToUpdate.setName(employee.getName());
			employeeToUpdate.setLastName(employee.getLastName());
			employeeToUpdate.setPosition(employee.getPosition());
			return "Empleado actualizado correctamente";
		}else {
			return "Empleado no encontrado";
		}
	}
	
	
}
