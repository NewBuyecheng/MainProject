package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher find(String username);

    int insertTeacher(Teacher teacher);

    int deleteTeacher(int id);

    int updateTeacher(Teacher teacher);

    int countTeacher();

    List<Teacher> selectTeacher(int a, int b);

    Teacher selectTeacherById(int teacherId);

    Teacher selectTeacherByStudentById(int studentId);
}
