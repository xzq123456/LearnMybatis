package mybatis02.dao;

import mybatis02.bean.Department;
import mybatis02.bean.Employee;

import java.util.List;

public interface DepartmentMapper {
    public Department getDeptById(Integer id);
    public Department getPdetByIDPlus(Integer id);
    public Department getDepyByIdStep(Integer id);

}
