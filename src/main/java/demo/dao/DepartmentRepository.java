package demo.dao;

import org.springframework.data.repository.CrudRepository;

import demo.entity.Department;


public interface DepartmentRepository extends CrudRepository<Department, Integer> {}
