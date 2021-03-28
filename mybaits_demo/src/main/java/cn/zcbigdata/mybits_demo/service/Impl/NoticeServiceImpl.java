package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Notice;
import cn.zcbigdata.mybits_demo.mapper.NoticeMapper;
import cn.zcbigdata.mybits_demo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public int insertNotice(Notice notice) {
        return this.noticeMapper.insertNotice(notice);
    }

    @Override
    public int deleteNotice(int id) {
        return this.noticeMapper.deleteNotice(id);
    }

    @Override
    public int updateNotice(Notice notice) {
        return this.noticeMapper.updateNotice(notice);
    }

    @Override
    public int countNotice() {
        return this.noticeMapper.countNotice();
    }

    @Override
    public List<Notice> selectNotice(int pageInteger, int limitInteger) {
        int pageIndex = (pageInteger-1) * limitInteger;
        int pageSize = limitInteger;
        return this.noticeMapper.selectNotice(pageIndex, pageSize);
    }
}
