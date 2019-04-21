package mybatis02.dao;

import mybatis02.bean.Employee;

import java.util.List;

public interface EmployeeMapperPuls {
    public Employee getEmployeeById(Integer id);
    public  Employee getEmpDept(Integer id);
    public Employee getEmpIDSetp(Integer id);
    public List<Employee> getEmpsByDeptId(Integer d_id);
}
