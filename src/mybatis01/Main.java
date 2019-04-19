package mybatis01;


import mybatis01.dao.EmployeeMapper;
import mybatis01.dao.EmployeeMapperAnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 *  1.根据全局配置文件  创建一个sqlSessionFactory对象
 *     全局配置文件：数据源等一些环境信息
 *   2.sql 映射文件 配置了每一个sql和封装规则
 *   3.将sql映射文件注册到全局配置文件中
 *   4.写代码：
 *     （1） sqlSessionFactory
 *      （2）sqlsession就是和数据库的一次会话，用它进行增删改查
 *
 * 接口式编程
 *    原生 ：  Dao ====>DaoImpl
 *    Mybatis  Mapper===>xxMapper.xml
 *    sqlsession代表和数据库的一次交互，用完关闭
 *    sqlsession 和connection是非线程安全的，每次获取都要重新获取
 *     mapper接口没有实现类，mybatis为接口自动生成代理对象（将接口和xml文件绑定）
 *     两个重要的配置文件： mybatis全局配置文件（也可以main new出来，就是麻烦点）  sql映射文件
 *
 */
public class Main {
    @Test
    public void test(){
        //加上这一句配合出log日志
        BasicConfigurator.configure();
        //读取全局配置文件
        String resource = "mybatis01/conf/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //创建 sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
         //namespace +id 防止冲突
        try {
            //用sqlSession对象进行增删改查
            //方法一
//            Employee employee = sqlSession.selectOne("mybatis01.conf.EmployeeMapper.selectEmp", 1);
             //方法二  通过接口
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//            //class com.sun.proxy.$Proxy4 绑定接口获取得到代理对象
//            System.out.println(mapper.getClass());
            Employee employee=mapper.getEmpById(1);
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }

    }
    @Test
    public void testAnotation(){
        BasicConfigurator.configure();
        String resource = "mybatis01/conf/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {

            EmployeeMapperAnotation Anotationmapper = sqlSession.getMapper(EmployeeMapperAnotation.class);
            Employee employee=Anotationmapper.getEmpById(2);
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }

    }
}
