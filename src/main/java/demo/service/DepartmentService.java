package demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.dao.DepartmentRepository;
import demo.dto.DepartmentDTO;
import demo.entity.Department;

@Component
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<DepartmentDTO> getDepartments() {
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        for (Department department : departmentRepository.findAll()) {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setDepID(department.getDepID());
            departmentDTO.setName(department.getName());

            departmentDTOList.add(departmentDTO);
        }
        return departmentDTOList;
    }

    public DepartmentDTO create(DepartmentDTO departmentDTO) {

        Department department = new Department();
        department.setName(departmentDTO.getName());
        departmentRepository.save(department);
        departmentDTO.setDepID(department.getDepID());

        return departmentDTO;
    }
}
