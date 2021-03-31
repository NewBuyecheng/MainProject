package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Paper;

import java.util.List;

public interface PaperMapper {

    int insertPaper(Paper paper);

    int deletePaper(int id);

    int updatePaper(Paper paper);

    int countPaper();

    List<Paper> selectPaper(int a,int b);

    int updatePaperIsChecked(Paper paper);

    Paper selectPaperByStudentId(int studentId);
}
