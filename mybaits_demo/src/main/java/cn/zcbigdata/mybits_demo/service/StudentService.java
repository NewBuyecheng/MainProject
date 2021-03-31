package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.Student;

import java.util.List;

public interface StudentService {

    Student find(String username);

    int insertStudent(Student student);

    int deleteStudent(int id);

    int updateStudent(Student student);

    int countStudent();

    List<Student> selectStudent(int a, int b);

    Student selectStudentById(int id);

    int checkOpen(Student student);

    int checkMid(Student student);

    int checkLast(Student student);

    int choosePaper(Student student);
}
