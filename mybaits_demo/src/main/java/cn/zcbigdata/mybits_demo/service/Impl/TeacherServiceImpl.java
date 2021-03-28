package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Teacher;
import cn.zcbigdata.mybits_demo.mapper.TeacherMapper;
import cn.zcbigdata.mybits_demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {


    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public Teacher find(String username) {
        return teacherMapper.find(username);
    }

    @Override
    public int insertTeacher(Teacher teacher) {
        return this.teacherMapper.insertTeacher(teacher);
    }

    @Override
    public int deleteTeacher(int id) {
        return this.teacherMapper.deleteTeacher(id);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return this.teacherMapper.updateTeacher(teacher);
    }

    @Override
    public int countTeacher() {
        return this.teacherMapper.countTeacher();
    }

    @Override
    public List<Teacher> selectTeacher(int pageInteger, int limitInteger) {
        int pageIndex = (pageInteger-1) * limitInteger;
        int pageSize = limitInteger;
        return this.teacherMapper.selectTeacher(pageIndex, pageSize);
    }
}
