package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.Util.ObjtoLayJson;
import cn.zcbigdata.mybits_demo.entity.Paper;
import cn.zcbigdata.mybits_demo.entity.Student;
import cn.zcbigdata.mybits_demo.entity.StudentChoosePaper;
import cn.zcbigdata.mybits_demo.service.PaperService;
import cn.zcbigdata.mybits_demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private PaperService paperService;

    @RequestMapping(value = "/selectStudentById", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String selectStudentById(HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        Integer idInteger = (Integer)session.getAttribute("userid");

        String[] colums = {"id","account","password","name","paperId","teacherId","open","openpass","opencomment","mid","midpass","midcomment","last","lastpass","lastcomment","grade"};
        Student student = studentService.selectStudentById(idInteger);
        String data = ObjtoLayJson.toJson(student,colums);
        return data;
    }

    @RequestMapping(value = "/choosePaper", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String choosePaper(@RequestParam("paperId") String paperId,HttpServletRequest request){
        Integer paperIdInteger = Integer.valueOf(paperId);

        HttpSession session = request.getSession();
        Integer idInteger = (Integer)session.getAttribute("userid");
        Student student = studentService.selectStudentById(idInteger);
        if(student == null || student.getPaperId() != 0){
            return "{\"code\":\"888\",\"message\":\"已经选择了一个论文题目，不能够多选！\"}";
        }

        Paper paper = paperService.selectPaperById(paperIdInteger);

        StudentChoosePaper studentChoosePaper = new StudentChoosePaper();
        studentChoosePaper.setId(idInteger);
        studentChoosePaper.setSubject(paper.getSubject());
        studentChoosePaper.setTeacherId(paper.getTeacherId());
        int count = studentService.choosePaper(studentChoosePaper);
        if (count == 1) {
            String data = "{\"code\":\"200\",\"message\":\"选择成功\"}";
            return data;
        }else {
            String data = "{\"code\":\"999\",\"message\":\"选择失败\"}";
            return data;
        }
    }

    @RequestMapping(value = "/insertPaper", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String insertPaper(@RequestParam("subject") String subject,@RequestParam("teacherId") String teacherId,HttpServletRequest request){

        HttpSession session = request.getSession();
        Integer idInteger = (Integer)session.getAttribute("userid");
        Student student = studentService.selectStudentById(idInteger);
        if(student == null || student.getPaperId() != 0){
            return "{\"code\":\"888\",\"message\":\"已经选择了一个论文题目，不能够再次申请！\"}";
        }

        Integer teacherIdInteger = Integer.valueOf(teacherId);
        Paper paper = new Paper();
        paper.setSubject(subject);
        paper.setIsChecked(false);
        paper.setTeacherId(teacherIdInteger);
        int count1 = paperService.insertPaper(paper);

        StudentChoosePaper studentChoosePaper = new StudentChoosePaper();
        studentChoosePaper.setId(idInteger);
        studentChoosePaper.setTeacherId(teacherIdInteger);
        studentChoosePaper.setSubject(subject);
        int count2 = studentService.choosePaper(studentChoosePaper);

        if(count1 + count2 ==2){
            String data = "{\"code\":\"200\",\"message\":\"提交题目成功\"}";
            return data;
        }else {
            String data = "{\"code\":\"999\",\"message\":\"提交题目失败\"}";
            return data;
        }
    }

    @RequestMapping(value = "/selectPaperByStudentId", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String selectPaperByStudentId(HttpServletRequest request) throws Exception {
        Paper paper = paperService.selectPaperByStudentId(request);
        if(paper == null)
            return null;
        String[] colums = {"id","subject","teacherId","isChecked"};
        String data = ObjtoLayJson.toJson(paper,colums);
        return data;

    }

    @RequestMapping(value = "/selectPaperById", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String selectPaperById(@RequestParam("id") String paperId,HttpServletRequest request) throws Exception {

        Integer paperIdInteger = Integer.valueOf(paperId);

        Paper paper = paperService.selectPaperById(paperIdInteger);
        if(paper == null)
            return null;
        String[] colums = {"id","subject","teacherId","isChecked"};
        String data = ObjtoLayJson.toJson(paper,colums);
        return data;

    }

    @RequestMapping(value = "/countPaperNotChecked", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String countPaperNotChecked(HttpServletRequest request){
        int count = paperService.countPaperNotChecked();
        String data = "{\"code\":\"200\",\"message\":\"查询成功\",\"count\":"+count+"}";
        return data;
    }

    @RequestMapping(value = "/selectPaperNotChecked", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String selectPaperNotChecked(@RequestParam("page") String page,@RequestParam("limit") String limit,HttpServletRequest request) throws Exception {
        Integer pageInteger = Integer.valueOf(page);
        Integer limitInteger = Integer.valueOf(limit);

        List<Paper> papers = paperService.selectPaperNotChecked(pageInteger, limitInteger);
        String[] colums = {"id", "subject", "teacherId", "isChecked"};
        String data = ObjtoLayJson.ListtoJson(papers, colums);
        return data;
    }


}
