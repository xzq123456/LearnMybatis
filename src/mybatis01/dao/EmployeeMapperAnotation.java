package mybatis01.dao;

import mybatis01.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapperAnotation {
    @Select("select * from employee where id=#{id}")
    public Employee getEmpById(Integer id);
}
