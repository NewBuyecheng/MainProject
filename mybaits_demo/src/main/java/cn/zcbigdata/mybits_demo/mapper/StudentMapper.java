package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Student;

import java.util.List;

public interface StudentMapper {

    Student find(String account);

    int insertStudent(Student student);

    int deleteStudent(int id);

    int updateStudent(Student student);

    int countStudent();

    List<Student> selectStudent(int a, int b);

    Student selectStudentById(int id);

    int updateStudentOpen(Student student);

    int updateStudentMid(Student student);

    int updateStudentLast(Student student);
}
