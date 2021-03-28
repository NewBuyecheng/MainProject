package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Student;

public interface StudentMapper {
    Student find(String username);
}
