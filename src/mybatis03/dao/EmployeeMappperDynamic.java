package mybatis03.dao;

import mybatis03.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMappperDynamic {
//    查询员工，要求：携带那个字段就带上哪个字段的值-
    public List<Employee> getEmpsByCOnditionIf(Employee employee);

    public List<Employee> getEmpsByCondationChoose(Employee employee);

    public void updateEmployee(Employee employee);

    public List<Employee> getEmpsByConditionForeach(@Param("ids") List<Integer> ids);

    public void addEmps(@Param("emps") List<Employee> emps);

    public List<Employee> getEmpsTestParameter(Employee employee);
}
