package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.Paper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PaperService {
    int insertPaper(Paper paper);

    int deletePaper(int id);

    int updatePaper(Paper paper);

    int countPaper();

    List<Paper> selectPaper(int a, int b);

    int checkPaper(Paper paper);

    Paper selectPaperByStudentId(HttpServletRequest request);

    Paper selectPaperById(int paperId);
}
