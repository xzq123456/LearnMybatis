package mybatis02.dao;

import mybatis02.bean.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapperAnotation {
    @Select("select * from employee where id=#{id}")
    public Employee getEmpById(Integer id);
}
