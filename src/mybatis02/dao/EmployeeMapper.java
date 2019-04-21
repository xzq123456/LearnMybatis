package mybatis02.dao;

import mybatis02.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

//接口式编程
public interface EmployeeMapper {
    public Employee getEmpById(Integer id);

    public void addEmp(Employee employee);

    public Long updateEmp(Employee employee);

    public Boolean deleteEmp(Integer id);

    public Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    public Employee getEmpByMap(Map<String,Object> map);

    public List<Employee> getEmpsByNameLike(String lastName);
    //返回map  如果没有业务对象 key:列名 值：对应的值
    public Map<String ,Object> getEmpByIdRetuenMap(Integer id);
    //多条记录封装成为Map  @MapKey("id")使用id作为主键
    @MapKey("id")
    public Map<Integer,Employee> getEmpsByLastNameReturnMap(String lastName);




}
