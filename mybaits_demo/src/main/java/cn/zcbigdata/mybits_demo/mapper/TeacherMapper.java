package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Teacher;

import java.util.List;

public interface TeacherMapper {
    Teacher find(String account);

    int insertTeacher(Teacher teacher);

    int deleteTeacher(int id);

    int updateTeacher(Teacher teacher);

    int countTeacher();

    List<Teacher> selectTeacher(int a,int b);
}
