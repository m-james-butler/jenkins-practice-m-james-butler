package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.model.Employee;
import com.revature.repositories.EmployeeDAOImpl;
import com.revature.service.EmployeeService;


public class ServiceTests {

	//Our service layers depends on the dao
	private EmployeeDAOImpl edaoImpl;
	
	@Before
	public void setup() {
		edaoImpl = mock(EmployeeDAOImpl.class);
		
		EmployeeService.edao = edaoImpl;
	}
	
	//Happy Path Scenario test
	
	@Test
	public void test_Employee_findByUsername() {
		Employee dumEE = new Employee (1, "Dum", "-E", "dum-e", "stark");
		Employee test2 = new Employee (2, "e", "f", "g", "h");
		
		List<Employee> list = new ArrayList<Employee>();
		list.add(test2);
		list.add(dumEE);
		
		when(edaoImpl.findAll()).thenReturn(list);
		
		Employee returned = EmployeeService.findByUsername("dum-e");
		
		assertEquals(dumEE, returned);
	}
	
	@Test
	public void employeeNotPresentInDb() {
		List<Employee> emptyList = new ArrayList<Employee>();
		when(edaoImpl.findAll()).thenReturn(emptyList);
		
		Employee found = EmployeeService.findByUsername("test");
		
		assertNull(found);
	}
	
}
