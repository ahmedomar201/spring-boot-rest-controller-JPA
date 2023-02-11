package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class EmployeeDAOJpaImp implements EmployeeDAO {

	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImp(EntityManager theEntityManager) {
		theEntityManager=entityManager;
	}
	@Override
	public List<Employee> findAll() {
		
		// create query 
		Query theQuery=entityManager.createQuery("from Employee");
		
		//execute a query and get result list
		List <Employee> employees=theQuery.getResultList();
		
		//return the result
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		// get employee
		Employee theEmployee=entityManager.find(Employee.class, theId);
		
		//return employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		// save or update the employee
		Employee dbEmployee=entityManager.merge(theEmployee);
		
		//update with id from DB ....so we can get generated 
		theEmployee.setId(dbEmployee.getId());

	}

	@Override
	public void deleteById(int theId) {
		
		// delete object with primary key
		Query theQuery=entityManager.createQuery(" delete from Employee where id =employeeId");
		
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
		
		

	}

}
