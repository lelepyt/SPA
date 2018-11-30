package demo.controller;

import demo.dto.EmployeeDTO;

import demo.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO>  getEmployees(@RequestParam("page") int page, @RequestParam("search") String searchEmployee) {
        List<EmployeeDTO> employeeList;
        if (StringUtils.isEmpty(searchEmployee)) {
            employeeList = employeeService.getAllEmployees(page);
        } else {
            employeeList =  employeeService.findByName(page, searchEmployee);
        }
        return employeeList;
    }

    @GetMapping(value = "/{id}")
    public EmployeeDTO getEmployee(@PathVariable int id) {
        return employeeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.create(employeeDTO);
        return employeeDTO;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeUpdate) {
       employeeService.update(employeeUpdate);

       return employeeUpdate;
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable int id) {
        employeeService.delete(id);
    }
}