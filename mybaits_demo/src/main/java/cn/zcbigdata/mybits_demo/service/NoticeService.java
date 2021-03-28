package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.Notice;

import java.util.List;

public interface NoticeService {
    int insertNotice(Notice notice);

    int deleteNotice(int id);

    int updateNotice(Notice notice);

    int countNotice();

    List<Notice> selectNotice(int a, int b);
}
