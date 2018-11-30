package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import demo.dto.DepartmentDTO;
import demo.service.DepartmentService;

@RestController
@RequestMapping(path="/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentDTO> getDepartments() {
        return  departmentService.getDepartments();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public DepartmentDTO createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentDTO = departmentService.create(departmentDTO);
        return departmentDTO;
    }
}
