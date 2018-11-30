package demo.dto;

public class EmployeeDTO {

    private Integer empID;

    private boolean empActive;

    private String empName;

    private DepartmentDTO empDepartment;

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(final Integer empID) {
        this.empID = empID;
    }

    public boolean getEmpActive() {
        return empActive;
    }

    public void setEmpActive(final boolean empActive) {
        this.empActive = empActive;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(final String empName) {
        this.empName = empName;
    }

    public boolean isEmpActive() {
        return empActive;
    }

    public DepartmentDTO getEmpDepartment() {
        return empDepartment;
    }

    public void setEmpDepartment(final DepartmentDTO empDepartment) {
        this.empDepartment = empDepartment;
    }
}
