package com.qzkMybatis;

import com.github.pagehelper.PageHelper;
import com.qzkMybatis.dao.StudentDao;
import com.qzkMybatis.domain.Student;
import com.qzkMybatis.utils.MybatisUtils;
import com.qzkMybatis.vo.QueryParam;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {

    @Test
    public void TestInsert() throws IOException {
        String config = "mybatis.xml";
        //2.读取这个文件
        InputStream in = Resources.getResourceAsStream(config);
        //3.创建SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //4.创建SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //5.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //6.指定要执行sql语句的标示
        String sqlId="com.qzkMybatis.dao.StudentDao"+"."+"insertStudent";

        Student student = new Student();
        student.setId(1005);
        student.setName("李四");
        student.setEmail("44.@qq.com");
        student.setAge(21);

        int nums = sqlSession.insert(sqlId,student);
        sqlSession.commit();
//        students.forEach(student -> System.out.println(student));
        System.out.println(nums);

        sqlSession.close();
    }

    @Test
    public void TestSearch() throws IOException{
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        String sqlId="com.qzkMybatis.dao.StudentDao"+"."+"searchAll";
        List<Student> students = sqlSession.selectList(sqlId);
        for(Student stu : students){
            System.out.println("name:"+stu.getName());
        }
        sqlSession.close();
    }

    @Test
    public void TestSearch2()  throws IOException{ //测试动态代理机制

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        PageHelper.startPage(1,1);
        List<Student> students = dao.searchAll();
        for(Student stu : students){
            System.out.println("name:"+stu.getName());
        }
        sqlSession.close();
    }

    @Test
    public void TestSearch3() throws IOException{
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student stu = dao.selectStudentbyid(1004);
        System.out.println(stu.name);
        sqlSession.close();
    }

    @Test
    public void TestSearch4() throws IOException{
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> students = dao.selectMulitParam("李四",20);
        for(Student stu : students){
            System.out.println("name:"+stu.getName());
        }
        sqlSession.close();
    }

    @Test
    public void TestSearch5() throws IOException{
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        QueryParam queryParam =new QueryParam();
        queryParam.setParamname("李四");
        queryParam.setParamAge(20);
        List<Student> students = dao.selectMulitObject(queryParam);
        for(Student stu : students){
            System.out.println("name:"+stu.getName());
        }
        sqlSession.close();
    }

    @Test
    public void TestSearch6() throws IOException{
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        int count = dao.selectCount();
        System.out.println(count);
        sqlSession.close();
    }

}
