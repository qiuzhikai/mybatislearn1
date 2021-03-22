package com.qzkMybatis.dao;

import com.qzkMybatis.domain.Student;
import com.qzkMybatis.vo.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {

    public List<Student> searchAll();

    public int insertStudent(Student student);

    public Student selectStudentbyid(Integer id);

    public List<Student> selectMulitParam(@Param("myname") String name, @Param("myage") int age);

    public List<Student> selectMulitObject(QueryParam queryParam);

    public int selectCount();

}
