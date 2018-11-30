package demo.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "tblDepartments")
public class Department {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer depID;

    private String name;

    @OneToMany(mappedBy="empDepartment")
    private List<Employee> employees;

    public Integer getDepID() {
        return depID;
    }

    public void setDepID(final Integer depID) {
        this.depID = depID;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(final List<Employee> employees) {
        this.employees = employees;
    }
}
