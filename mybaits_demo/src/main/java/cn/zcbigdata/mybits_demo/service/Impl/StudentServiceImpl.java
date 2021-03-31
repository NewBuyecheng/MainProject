package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Student;
import cn.zcbigdata.mybits_demo.mapper.StudentMapper;
import cn.zcbigdata.mybits_demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public Student find(String username) {
        return studentMapper.find(username);
    }

    @Override
    public int insertStudent(Student student) {
        return this.studentMapper.insertStudent(student);
    }

    @Override
    public int deleteStudent(int id) {
        return this.studentMapper.deleteStudent(id);
    }

    @Override
    public int updateStudent(Student student) {
        return this.studentMapper.updateStudent(student);
    }

    @Override
    public int countStudent() {
        return this.studentMapper.countStudent();
    }

    @Override
    public List<Student> selectStudent(int pageInteger, int limitInteger) {
        int pageIndex = (pageInteger-1) * limitInteger;
        int pageSize = limitInteger;
        return this.studentMapper.selectStudent(pageIndex, pageSize);
    }

    @Override
    public Student selectStudentById(int id) {
        return this.studentMapper.selectStudentById(id);
    }

    @Override
    public int checkOpen(Student student) {
        return this.studentMapper.updateStudentOpen(student);
    }

    @Override
    public int checkMid(Student student) {
        return this.studentMapper.updateStudentMid(student);
    }

    @Override
    public int checkLast(Student student) {
        return this.studentMapper.updateStudentLast(student);
    }

    @Override
    public int choosePaper(Student student) {
        return this.studentMapper.choosePaper(student);
    }


}
