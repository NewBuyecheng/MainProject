package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.Util.CheckLogin;
import cn.zcbigdata.mybits_demo.Util.ObjtoLayJson;
import cn.zcbigdata.mybits_demo.entity.Student;
import cn.zcbigdata.mybits_demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/selectStudentById", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String selectStudentById(@RequestParam("id") String id, HttpServletRequest request) throws Exception {

        Integer idInteger = Integer.valueOf(id);

        String[] colums = {"id","account","password","name","paperId","teacherId","open","openpass","opencomment","mid","midpass","midcomment","last","lastpass","lastcomment","grade"};
        Student student = studentService.selectStudentById(idInteger);
        String data = ObjtoLayJson.toJson(student,colums);
        return data;
    }

    @RequestMapping(value = "/checkOpen", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String checkOpen(@RequestParam("id") String id,@RequestParam("open") String open,@RequestParam("openpass") String openpass,
                            @RequestParam("opencomment") String opencomment,HttpServletRequest request){
        if(CheckLogin.checkLoginFlag(request) == 2){
            String data = "{\"code\":\"777\",\"message\":\"没有权限\"}";
            return data;
        }else{
            Integer idInteger = Integer.valueOf(id);
            Boolean openpassBoolean = Boolean.valueOf(openpass);
            Student student = new Student();
            student.setId(idInteger);
            student.setOpen(open);
            student.setOpenpass(openpassBoolean);
            student.setOpencomment(opencomment);

            int count = studentService.checkOpen(student);
            if(count == 1) {
                String data = "{\"code\":\"200\",\"message\":\"添加成功\"}";
                return data;
            }else {
                String data = "{\"code\":\"999\",\"message\":\"添加失败\"}";
                return data;
            }
        }
    }
}
