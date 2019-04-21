package mybatis02;



import mybatis02.bean.Department;
import mybatis02.bean.Employee;
import mybatis02.dao.DepartmentMapper;
import mybatis02.dao.EmployeeMapper;
import mybatis02.dao.EmployeeMapperAnotation;
import mybatis02.dao.EmployeeMapperPuls;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 */
public class Main {
    SqlSessionFactory sqlSessionFactory=null;
    {
        BasicConfigurator.configure();
        String resource = "mybatis02/conf/mybatis-config.xml";
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
       SqlSession sqlSession = sqlSessionFactory.openSession();
       try {
          EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
          Employee employee=mapper.getEmpById(1);
           System.out.println(employee);
       }finally {
           sqlSession.close();
       }
   }
   //测试增删改
    //mybatis 允许增删改查直接定义以下返回值，不需要自己定义
    //Integer Long Boolean
   @Test
    public void test02(){
        //openSession() 无参数 不会自动提交  openSession(true)自动提交
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper=sqlSession.getMapper(EmployeeMapper.class);
            //添加
            Employee employee = new Employee(null, "xzq3", "1", "xzq3@qq.com");
            employeeMapper.addEmp(employee);
            System.out.println(employee.getId());//输出添加的主键
              //修改
//             Employee employee = new Employee(4, "xzq222", "1", "xzq2222@qq.com");
//             employeeMapper.updateEmp(employee);
            //删除
//            Boolean aBoolean = employeeMapper.deleteEmp(5);
//            System.out.println(aBoolean);//true
            //手动提交
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
   }
   @Test
   public void test3(){
       SqlSession sqlSession=sqlSessionFactory.openSession();
       try {
           EmployeeMapper employeeMapper=sqlSession.getMapper(EmployeeMapper.class);
//           Employee employee = employeeMapper.getEmpByIdAndLastName(1,"Tom");
//           System.out.println(employee);

//           Map<String,Object> map=new HashMap<>();
//           map.put("id",6);
//           map.put("lastName","xzq3");
//           map.put("tableName","employee");
//           Employee employee = employeeMapper.getEmpByMap(map);
//           System.out.println(employee);

           //返回list
//           List<Employee> emps = employeeMapper.getEmpsByNameLike("%xzq%");
//           for(Employee emp:emps){
//               System.out.println(emp);
//           }

           //返回map
//           Map<String,Object> map=employeeMapper.getEmpByIdRetuenMap(1);
//           System.out.println(map);
//           {gender=0, last_name=Tom, id=1, email=tom@qq.com}

           //返回Map2
           Map<Integer, Employee> empsByLastNameReturnMap = employeeMapper.getEmpsByLastNameReturnMap("%xzq%");
           System.out.println(empsByLastNameReturnMap);
//           {6=Employee{id=6, lastName='xzq3', gender='1', email='xzq3@qq.com'}, 9=Employee{id=9, lastName='xzq3', gender='0', email='xzq3@qq.com'}, 10=Employee{id=10, lastName='xzq3', gender='1', email='xzq3@qq.com'}}
           sqlSession.commit();
       }finally {
           sqlSession.close();
       }
   }
   @Test
   public void test04(){
       SqlSession sqlSession = sqlSessionFactory.openSession(true);
       EmployeeMapperPuls mapper = sqlSession.getMapper(EmployeeMapperPuls.class);
//       Employee employee = mapper.getEmployeeById(1);
//       System.out.println(employee);
       //分布查询
//       Employee empIDSetp = mapper.getEmpIDSetp(1);
//       System.out.println(empIDSetp);
       //监视器的分布查询
       //男生的话0 就不会查出dept信息
       Employee empIDSetp = mapper.getEmpIDSetp(2);//2号id对应的gender是1 是女生 会查出部门信息
       System.out.println(empIDSetp);
       System.out.println(empIDSetp.getDept());

//       Employee empDept = mapper.getEmpDept(1);
//       System.out.println(empDept);
//       System.out.println(empDept.getDept());

   }
   @Test
    public void test5(){
       SqlSession sqlSession = sqlSessionFactory.openSession(true);
       try {

//       DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
//       Department department = mapper.getDeptById(1);
//       System.out.println(department);

            EmployeeMapperPuls mapper = sqlSession.getMapper(EmployeeMapperPuls.class);
//       Employee empIDSetp = mapper.getEmpIDSetp(1);
//       System.out.println(empIDSetp);
//       System.out.println(empIDSetp.getDept());
//       select * from employee where id=?  就发了一条sql
//       System.out.println(empIDSetp.getLastName());

           //测试集合
           DepartmentMapper mapper2 = sqlSession.getMapper(DepartmentMapper.class);
//           Department department = mapper2.getPdetByIDPlus(1);
//           System.out.println(department);
//           System.out.println(department.getEmps());

           //分段查询
           Department depyByIdStep = mapper2.getDepyByIdStep(2);
           System.out.println(depyByIdStep);
           System.out.println(depyByIdStep.getEmps());
       }finally {
           sqlSession.close();
       }
   }
}
