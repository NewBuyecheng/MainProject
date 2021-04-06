package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.Util.ObjtoLayJson;
import cn.zcbigdata.mybits_demo.entity.Paper;
import cn.zcbigdata.mybits_demo.entity.Student;
import cn.zcbigdata.mybits_demo.service.PaperService;
import cn.zcbigdata.mybits_demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private PaperService paperService;

    @RequestMapping(value = "/selectStudentById", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String selectStudentById(@RequestParam("id") String id, HttpServletRequest request) throws Exception {

        Integer idInteger = Integer.valueOf(id);

        String[] colums = {"id","account","password","name","paperId","teacherId","open","openpass","opencomment","mid","midpass","midcomment","last","lastpass","lastcomment","grade"};
        Student student = studentService.selectStudentById(idInteger);
        String data = ObjtoLayJson.toJson(student,colums);
        return data;
    }

    @RequestMapping(value = "/choosePaper", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String choosePaper(@RequestParam("id") String id,@RequestParam("paperId") String paperId,HttpServletRequest request){
        Integer idInteger = Integer.valueOf(id);
        Integer paperIdInteger = Integer.valueOf(paperId);

        Student student = new Student();
        student.setId(idInteger);
        student.setPaperId(paperIdInteger);

        int count = studentService.choosePaper(student);
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
    public String insertPaper(@RequestParam("subject") String subject,@RequestParam("teacherId") String teacherId,@RequestParam("id") String id,HttpServletRequest request){

        Integer teacherIdInteger = Integer.valueOf(teacherId);
        Paper paper = new Paper();
        paper.setSubject(subject);
        paper.setIsChecked(false);
        paper.setTeacherId(teacherIdInteger);
        int count1 = paperService.insertPaper(paper);

        Integer idInteger = Integer.valueOf(id);
        Student student = new Student();
        student.setId(idInteger);
        student.setTeacherId(teacherIdInteger);
        int count2 = studentService.choosePaper(student);

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

}
