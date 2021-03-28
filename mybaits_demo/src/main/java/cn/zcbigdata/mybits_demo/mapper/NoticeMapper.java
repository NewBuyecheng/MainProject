package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Notice;

import java.util.List;

public interface NoticeMapper {

    int insertNotice(Notice notice);

    int deleteNotice(int id);

    int updateNotice(Notice notice);

    int countNotice();

    List<Notice> selectNotice(int a,int b);
}
