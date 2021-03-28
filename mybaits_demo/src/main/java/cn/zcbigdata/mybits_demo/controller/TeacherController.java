package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.Util.CheckLogin;
import cn.zcbigdata.mybits_demo.Util.ObjtoLayJson;
import cn.zcbigdata.mybits_demo.entity.Paper;
import cn.zcbigdata.mybits_demo.service.PaperService;
import cn.zcbigdata.mybits_demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private PaperService paperService;

    @RequestMapping(value = "/insertPaper", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String insertPaper(@RequestParam("subject") String subject, @RequestParam("teacherId") String teacherId, @RequestParam("isChecked") String isChecked, HttpServletRequest request){
        if(CheckLogin.checkLoginFlag(request) == 2){
            String data = "{\"code\":\"777\",\"message\":\"没有权限\"}";
            return data;
        }else{
            Paper paper = new Paper();
            paper.setSubject(subject);
            Integer teacherIdTnteger = Integer.valueOf(teacherId);
            Boolean isCheckedBoolean = Boolean.valueOf(isChecked);
            paper.setTeacherId(teacherIdTnteger);
            paper.setIsChecked(isCheckedBoolean);
            int count = paperService.insertPaper(paper);
            if(count == 1) {
                String data = "{\"code\":\"200\",\"message\":\"添加成功\"}";
                return data;
            }else {
                String data = "{\"code\":\"999\",\"message\":\"添加失败\"}";
                return data;
            }
        }
    }

    @RequestMapping(value = "/updatePaper", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String updatePaper(@RequestParam("id") String id,@RequestParam("subject") String subject, @RequestParam("teacherId") String teacherId, @RequestParam("isChecked") String isChecked, HttpServletRequest request){
        if(CheckLogin.checkLoginFlag(request) == 2){
            String data = "{\"code\":\"777\",\"message\":\"没有权限\"}";
            return data;
        }else{
            Paper paper = new Paper();
            Integer idInteger = Integer.valueOf(id);
            Integer teacherIdTnteger = Integer.valueOf(teacherId);
            Boolean isCheckedBoolean = Boolean.valueOf(isChecked);

            paper.setId(idInteger);
            paper.setSubject(subject);
            paper.setTeacherId(teacherIdTnteger);
            paper.setIsChecked(isCheckedBoolean);

            int count = paperService.updatePaper(paper);
            if(count == 1) {
                String data = "{\"code\":\"200\",\"message\":\"修改成功\"}";
                return data;
            }else {
                String data = "{\"code\":\"999\",\"message\":\"修改失败\"}";
                return data;
            }
        }
    }

    @RequestMapping(value = "/deletePaper", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String deletePaper(@RequestParam("id") String id,HttpServletRequest request){
        if(CheckLogin.checkLoginFlag(request) == 2){
            String data = "{\"code\":\"777\",\"message\":\"没有权限\"}";
            return data;
        }else{
            Integer idInteger = Integer.valueOf(id);
            int count = paperService.deletePaper(idInteger);
            if(count == 1) {
                String data = "{\"code\":\"200\",\"message\":\"删除成功\"}";
                return data;
            }else {
                String data = "{\"code\":\"999\",\"message\":\"删除失败\"}";
                return data;
            }
        }
    }

    @RequestMapping(value = "/countPaper", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String countPaper(HttpServletRequest request){
        int count = paperService.countPaper();
        return "{\"code\":\"200\",\"message\":\"查找成功\",\"count\":"+count+"}";
    }

    @RequestMapping(value = "/selectPaper", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String selectPaper(@RequestParam("page") String page,@RequestParam("limit") String limit,HttpServletRequest request) throws Exception {
        Integer pageInteger = Integer.valueOf(page);
        Integer limitInteger = Integer.valueOf(limit);

        List<Paper> papers = paperService.selectPaper(pageInteger,limitInteger);
        String[] colums = {"id","subject","teacherId","isChecked"};
        String data = ObjtoLayJson.ListtoJson(papers,colums);
        return data;
    }

    @RequestMapping(value = "/checkPaper", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String checkPaper(@RequestParam("id") String id,@RequestParam("isChecked") String isChecked,HttpServletRequest request){
        if(CheckLogin.checkLoginFlag(request) == 2){
            String data = "{\"code\":\"777\",\"message\":\"没有权限\"}";
            return data;
        }else{
            Integer idInteger = Integer.valueOf(id);
            Boolean isCheckedBoolean = Boolean.valueOf(isChecked);
            Paper paper = new Paper();
            paper.setId(idInteger);
            paper.setIsChecked(isCheckedBoolean);

            int count = paperService.checkPaper(paper);
            if(count == 1) {
                String data = "{\"code\":\"200\",\"message\":\"审核成功\"}";
                return data;
            }else {
                String data = "{\"code\":\"999\",\"message\":\"审核失败\"}";
                return data;
            }
        }
    }
}
