package demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "tblEmployees")
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer empID;

    private boolean empActive;

    private String empName;

    @ManyToOne
    @JoinColumn(name="depID")
    private Department empDepartment;

    public Department getEmpDepartment() {
        return empDepartment;
    }

    public void setEmpDepartment(Department empDepartment) {
        this.empDepartment = empDepartment;
    }

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public boolean getEmpActive() {
        return empActive;
    }

    public void setEmpActive(boolean empActive) {
        this.empActive = empActive;
    }
}