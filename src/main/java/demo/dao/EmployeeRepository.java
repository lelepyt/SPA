package demo.dao;

import demo.entity.Employee;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableAutoConfiguration
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

    Page<Employee> findByEmpNameStartingWith(String empName, Pageable page);
}