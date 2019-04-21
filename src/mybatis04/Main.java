package mybatis04;

import mybatis04.bean.Employee;
import mybatis04.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
/*
  1.两级缓存：
     一级缓存（本地缓存） 与数据库的一次回话的数据会保存在本地缓存中 sqlsession级别
     二级缓存L（全局缓存）

     一级缓存失效：
         1.不同的sqlsession
         2.sqlsession相同，查询条件不同
         3.sqlsession相同 ，查询条件相同 但是中间有增删改操作
         4.手动清空缓存    sqlSession.clearCache();
     二级缓存：基于namespace级别（不同namespace 的数据会保存在相应的二级缓存中）
       1.一次会话关闭的时候，会把数据保存在二级缓存中。
        2.开启全局二级缓存
         1)setting name="cacheEnabled" value="true"></setting>
         2)在xml文件中<cache></cache>
         3)javabean并实现序列化接口
         查出的数据先放在一级缓存中，一级缓存中。关闭后才会被放在二级缓存中

        和缓存有关的属性：
        1.setting name="cacheEnabled" value="false"></setting> 关闭二级缓存，不关闭一级缓存
        2.每个select标签有usecache =false 不影响一级缓存，会影响二级缓存（前提全局要开 ）
        3. 每个增删改标签  flushcache=false 一级二级缓存都会清掉
        4.查询标签默认flushcache为false  设置为true 缓存就请了
        5.  sqlSession.clearCache() 清除的是一级缓存

        新会话先去二级缓存找，没有去一级缓存，再没有就去数据库查

       整合第三方缓存，Ehcache

*/


public class Main {
    SqlSessionFactory sqlSessionFactory=null;
    {
        BasicConfigurator.configure();
        String resource = "mybatis04/conf/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    @Test
    public  void test(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
       try {
           EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
           Employee emp01 = mapper.getEmpById(1);
           System.out.println(emp01);
//           sqlSession.clearCache();

           //xxxx  只发了一次sql
           Employee emp02 = mapper.getEmpById(1);
           System.out.println(emp02);
           System.out.println(emp01 == emp02);//true 说明是一个对象 并没有再发送sql查

       }finally {
           sqlSession.close();
       }

    }
    @Test
    public void test2(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        try{
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
         //发送了一条sql  第二次从缓存中拿 Cache Hit Ratio [mybatis04.dao.EmployeeMapper]: 0.5
            Employee emp1 = mapper.getEmpById(1);
            System.out.println(emp1);
            sqlSession.close();
            Employee emp2=mapper2.getEmpById(1);
            System.out.println(emp2);
            sqlSession2.close();
        }finally {


        }
    }
    }

