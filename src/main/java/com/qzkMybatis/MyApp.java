package com.qzkMybatis;

import com.qzkMybatis.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyApp {
    public static void main(String[] args) throws IOException {
        //访问mybatis
        //1.定义mybatis主配置文件的名称，路径从target/classes开始
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
        String sqlId="com.qzkMybatis.dao.StudentDao"+"."+"searchAll";
        //7.执行sql
        List<Student> students = sqlSession.selectList(sqlId);
        for(Student stu : students){
            System.out.println("name:"+stu.getName());
        }

//        students.forEach(student -> System.out.println(student));

        sqlSession.close();

    }
}
