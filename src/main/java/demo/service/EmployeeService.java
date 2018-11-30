package demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import demo.dao.DepartmentRepository;
import demo.dao.EmployeeRepository;
import demo.dto.DepartmentDTO;
import demo.dto.EmployeeDTO;
import demo.entity.Department;
import demo.entity.Employee;

@Component
public class EmployeeService {

    private static final int PAGE_SIZE_DEFAULT = 10;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<EmployeeDTO> getAllEmployees(int pageNumber) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (Employee employee : employeeRepository.findAll(PageRequest.of(pageNumber, PAGE_SIZE_DEFAULT)).getContent()) {
            employeeDTOList.add(convertToDTO(employee));
        }

        return employeeDTOList;
    }

    public List<EmployeeDTO> findByName(int pageNumber, String name) {

        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (Employee employee : employeeRepository.findByEmpNameStartingWith(name, PageRequest.of(pageNumber, PAGE_SIZE_DEFAULT)).getContent()) {
            employeeDTOList.add(convertToDTO(employee));
        }

        return employeeDTOList;
    }

    public EmployeeDTO findById(int id) {
        return convertToDTO(employeeRepository.findById(id).get());
    }

    public void create(EmployeeDTO employeeDTO) {

        Employee employee = new Employee();
        employee.setEmpName(employeeDTO.getEmpName());
        employee.setEmpActive(employeeDTO.getEmpActive());

        DepartmentDTO departmentDTO = employeeDTO.getEmpDepartment();
        Department departmentDB = departmentRepository.findById(departmentDTO.getDepID()).get();
        employee.setEmpDepartment(departmentDB);

        employeeRepository.save(employee);
    }

    public void update(EmployeeDTO employeeUpdate) {

        Employee employeeDB = employeeRepository.findById(employeeUpdate.getEmpID()).get();
        employeeDB.setEmpName(employeeUpdate.getEmpName());
        employeeDB.setEmpActive(employeeUpdate.getEmpActive());
        employeeDB.setEmpName(employeeUpdate.getEmpName());
        employeeDB.setEmpActive(employeeUpdate.getEmpActive());

        DepartmentDTO departmentDTO = employeeUpdate.getEmpDepartment();
        Department departmentDB = departmentRepository.findById(departmentDTO.getDepID()).get();
        employeeDB.setEmpDepartment(departmentDB);

        employeeRepository.save(employeeDB);
    }

    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDTO convertToDTO(Employee employee) {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        DepartmentDTO departmentDTO = new DepartmentDTO();

        employeeDTO.setEmpID(employee.getEmpID());
        employeeDTO.setEmpName(employee.getEmpName());
        employeeDTO.setEmpActive(employee.getEmpActive());

        departmentDTO.setDepID(employee.getEmpDepartment().getDepID());
        departmentDTO.setName(employee.getEmpDepartment().getName());
        employeeDTO.setEmpDepartment(departmentDTO);

        return employeeDTO;
    }
}
