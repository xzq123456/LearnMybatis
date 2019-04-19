package mybatis01.dao;

import mybatis01.Employee;
//接口式编程
public interface EmployeeMapper {
    public Employee getEmpById(Integer id);
}
