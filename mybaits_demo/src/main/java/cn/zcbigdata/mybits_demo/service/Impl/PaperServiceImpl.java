package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Paper;
import cn.zcbigdata.mybits_demo.mapper.PaperMapper;
import cn.zcbigdata.mybits_demo.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;

    @Override
    public int insertPaper(Paper paper) {
        return this.paperMapper.insertPaper(paper);
    }

    @Override
    public int deletePaper(int id) {
        return this.paperMapper.deletePaper(id);
    }

    @Override
    public int updatePaper(Paper paper) {
        return this.paperMapper.updatePaper(paper);
    }

    @Override
    public int countPaper() {
        return this.paperMapper.countPaper();
    }

    @Override
    public List<Paper> selectPaper(int pageInteger, int limitInteger) {
        int pageIndex = (pageInteger-1) * limitInteger;
        int pageSize = limitInteger;
        return this.paperMapper.selectPaper(pageIndex,pageSize);
    }

    @Override
    public int checkPaper(Paper paper) {
        return this.paperMapper.updatePaperIsChecked(paper);
    }
}
