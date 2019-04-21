package mybatis03;


import mybatis03.bean.Department;
import mybatis03.bean.Employee;
import mybatis03.dao.EmployeeMappperDynamic;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**

 */
public class Main {
    SqlSessionFactory sqlSessionFactory=null;
    {
        BasicConfigurator.configure();
        String resource = "mybatis03/conf/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
       sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
   @Test
    public void test(){
       SqlSession sqlSession = sqlSessionFactory.openSession(true);
       EmployeeMappperDynamic mapper = sqlSession.getMapper(EmployeeMappperDynamic.class);
       Employee employee=new Employee(1,"Tom",null,"null");
//       List<Employee> emps= mapper.getEmpsByCOnditionIf(employee);
//       for(Employee emp:emps){
//           System.out.println(emp);
//       }

       //测试choose select * from employee WHERE last_name like ?
//       List<Employee> emps = mapper.getEmpsByCondationChoose(employee);
//       for(Employee emp:emps){
//           System.out.println(emp);
//       }
       //测试更新
//       mapper.updateEmployee(employee);

        //测试foreach
//       List<Employee> emps = mapper.getEmpsByConditionForeach(Arrays.asList(1,2));
//       for(Employee emp:emps){
//           System.out.println(emp);
//       }
       //测试批量insert
       List<Employee> employees=new ArrayList<>();
       employees.add(new Employee(null,"wangyu22","0","ssds@qq.com",new Department(1)));
       employees.add(new Employee(null,"zhangsa222n","1","ee@qq.com",new Department(2)));
       mapper.addEmps(employees);


//       Employee employee2=new Employee();
//       employee2.setLastName("xzq");
//       List<Employee> empsTestParameter = mapper.getEmpsTestParameter(employee2);
//       for(Employee emp:empsTestParameter){
//           System.out.println(emp);
//       }
   }
}
