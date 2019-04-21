package mybatis04.dao;

import mybatis04.bean.Employee;

//接口式编程
public interface EmployeeMapper {
    public Employee getEmpById(Integer id);
}
