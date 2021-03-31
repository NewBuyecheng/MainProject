package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.Util.CheckLogin;
import cn.zcbigdata.mybits_demo.Util.ObjtoLayJson;
import cn.zcbigdata.mybits_demo.entity.Notice;
import cn.zcbigdata.mybits_demo.entity.Student;
import cn.zcbigdata.mybits_demo.entity.Teacher;
import cn.zcbigdata.mybits_demo.service.AdminService;
import cn.zcbigdata.mybits_demo.service.NoticeService;
import cn.zcbigdata.mybits_demo.service.StudentService;
import cn.zcbigdata.mybits_demo.service.TeacherService;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/insertStudent", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String insertStudent(@RequestParam("account") String account,@RequestParam("password") String password,@RequestParam("name") String name, HttpServletRequest request){
        //System.out.println(account+"//"+password+"//"+name);
        HttpSession session = request.getSession();
        if(CheckLogin.checkLogin(request)){
            if(CheckLogin.checkLoginFlag(request) != 0){
                String data = "{\"code\":\"777\",\"message\":\"没有管理员权限\"}";
                return data;
            }else{
                Student student = new Student();
                student.setAccount(account);
                student.setPassword(password);
                student.setName(name);
                int count = studentService.insertStudent(student);
                if(count == 1) {
                    String data = "{\"code\":\"200\",\"message\":\"添加成功\"}";
                    return data;
                }else {
                    String data = "{\"code\":\"999\",\"message\":\"添加失败\"}";
                    return data;
                }
            }
        }else{
            String data = "{\"code\":\"777\",\"message\":\"用户未登录\"}";
            return data;
        }
    }
    @RequestMapping(value = "/deleteStudent", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String deleteStudent(@RequestParam("id") String id,HttpServletRequest request){
        Integer idInteger = Integer.valueOf(id);

        HttpSession session = request.getSession();
        if(CheckLogin.checkLogin(request)) {
            if (CheckLogin.checkLoginFlag(request) != 0) {
                String data = "{\"code\":\"777\",\"message\":\"没有管理员权限\"}";
                return data;
            } else {
                int count = studentService.deleteStudent(idInteger);
                if(count == 1) {
                    String data = "{\"code\":\"200\",\"message\":\"添加成功\"}";
                    return data;
                }else {
                    String data = "{\"code\":\"999\",\"message\":\"添加失败\"}";
                    return data;
                }
            }
        }else{
            String data = "{\"code\":\"777\",\"message\":\"用户未登录\"}";
            return data;
        }
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String updateStudent(@RequestParam("id") String id,@RequestParam("account") String account,@RequestParam("password") String password,
                                @RequestParam("name") String name,@RequestParam("paperId") String paperId,@RequestParam("teacherId") String teacherId,
                                @RequestParam("open") String open,@RequestParam("openpass") String openpass,@RequestParam("opencomment") String opencomment,
                                @RequestParam("mid") String mid,@RequestParam("openpass") String midpass,@RequestParam("midcomment") String midcomment,
                                @RequestParam("last") String last,@RequestParam("lastpass") String lastpass,@RequestParam("lastcomment") String lastcomment,
                                @RequestParam("grade") String grade,HttpServletRequest request){
        Integer idInteger = Integer.valueOf(id);
        Integer paperIdInteger = Integer.valueOf(paperId);
        Integer teacherIdInteger = Integer.valueOf(teacherId);
        Integer gradeInteger = Integer.valueOf(grade);
        Boolean openpassBoolean = Boolean.valueOf(openpass);
        Boolean midpassBoolean = Boolean.valueOf(midpass);
        Boolean lastpassBoolean = Boolean.valueOf(lastpass);

        HttpSession session = request.getSession();
        if(CheckLogin.checkLogin(request)) {
            if (CheckLogin.checkLoginFlag(request) != 0) {
                String data = "{\"code\":\"777\",\"message\":\"没有管理员权限\"}";
                return data;
            } else {
                Student student = new Student();
                student.setId(idInteger);
                student.setAccount(account);
                student.setPassword(password);
                student.setName(name);
                student.setPaperId(paperIdInteger);
                student.setTeacherId(teacherIdInteger);
                student.setOpen(open);  student.setOpenpass(openpassBoolean);   student.setOpencomment(opencomment);
                student.setMid(mid);  student.setMidpass(midpassBoolean);   student.setMidcomment(midcomment);
                student.setLast(last);  student.setLastpass(lastpassBoolean);   student.setLastcomment(lastcomment);
                int count = studentService.updateStudent(student);
                if(count == 1) {
                    String data = "{\"code\":\"200\",\"message\":\"修改成功\"}";
                    return data;
                }else {
                    String data = "{\"code\":\"999\",\"message\":\"修改失败\"}";
                    return data;
                }
            }
        }else{
            String data = "{\"code\":\"777\",\"message\":\"用户未登录\"}";
            return data;
        }
    }

    @RequestMapping(value = "/countStudent", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String countStudent(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(CheckLogin.checkLogin(request)) {
            if (CheckLogin.checkLoginFlag(request) != 0) {
                String data = "{\"code\":\"777\",\"message\":\"没有管理员权限\"}";
                return data;
            } else {
                int count = studentService.countStudent();
                if(count < 1) {
                    String data = "{\"code\":\"999\",\"message\":\"数据为空\",\"count\":\"0\"}";
                    return data;
                }else {
                    String data = "{\"code\":\"200\",\"message\":\"查询成功\",\"count\":"+count+"}";
                    return data;
                }
            }
        }else{
            String data = "{\"code\":\"777\",\"message\":\"用户未登录\"}";
            return data;
        }
    }

    @RequestMapping(value = "/selectStudent", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String selectStudent(@RequestParam("page") String page,@RequestParam("limit") String limit,HttpServletRequest request) throws Exception {
        Integer pageInteger = Integer.valueOf(page);
        Integer limitInteger = Integer.valueOf(limit);

        List<Student> students = studentService.selectStudent(pageInteger,limitInteger);
        String[] colums = {"id","account","password","name","paperId","teacherId","open","openpass","opencomment","mid","midpass","midcomment","last","lastpass","lastcomment","grade"};
        String data = ObjtoLayJson.ListtoJson(students,colums);
        return data;
    }

    @RequestMapping(value = "/insertTeacher", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String insertTeacher(@RequestParam("account") String account,@RequestParam("password") String password,@RequestParam("name") String name,@RequestParam("title") String title, HttpServletRequest request){
        if(CheckLogin.checkLogin(request)){
            if(CheckLogin.checkLoginFlag(request) != 0){
                String data = "{\"code\":\"777\",\"message\":\"没有管理员权限\"}";
                return data;
            }else{
                Teacher teacher = new Teacher();
                teacher.setAccount(account);
                teacher.setPassword(password);
                teacher.setName(name);
                teacher.setTitle(title);
                int count = teacherService.insertTeacher(teacher);
                if(count == 1) {
                    String data = "{\"code\":\"200\",\"message\":\"添加成功\"}";
                    return data;
                }else {
                    String data = "{\"code\":\"999\",\"message\":\"添加失败\"}";
                    return data;
                }
            }
        }else{
            String data = "{\"code\":\"777\",\"message\":\"用户未登录\"}";
            return data;
        }
    }
    @RequestMapping(value = "/deleteTeacher", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String deleteTeacher(@RequestParam("id") String id,HttpServletRequest request){
        Integer idInteger = Integer.valueOf(id);

        if(CheckLogin.checkLogin(request)) {
            if (CheckLogin.checkLoginFlag(request) != 0) {
                String data = "{\"code\":\"777\",\"message\":\"没有管理员权限\"}";
                return data;
            } else {
                int count = teacherService.deleteTeacher(idInteger);
                if(count == 1) {
                    String data = "{\"code\":\"200\",\"message\":\"添加成功\"}";
                    return data;
                }else {
                    String data = "{\"code\":\"999\",\"message\":\"添加失败\"}";
                    return data;
                }
            }
        }else{
            String data = "{\"code\":\"777\",\"message\":\"用户未登录\"}";
            return data;
        }
    }

    @RequestMapping(value = "/updateTeacher", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String updateStudent(@RequestParam("id") String id,@RequestParam("account") String account,@RequestParam("password") String password,
                                @RequestParam("name") String name,@RequestParam("title") String title,HttpServletRequest request){
        Integer idInteger = Integer.valueOf(id);

        if(CheckLogin.checkLogin(request)) {
            if (CheckLogin.checkLoginFlag(request) != 0) {
                String data = "{\"code\":\"777\",\"message\":\"没有管理员权限\"}";
                return data;
            } else {
                Teacher teacher = new Teacher();
                teacher.setId(idInteger);
                teacher.setAccount(account);
                teacher.setPassword(password);
                teacher.setName(name);
                teacher.setTitle(title);

                int count = teacherService.updateTeacher(teacher);
                if(count == 1) {
                    String data = "{\"code\":\"200\",\"message\":\"修改成功\"}";
                    return data;
                }else {
                    String data = "{\"code\":\"999\",\"message\":\"修改失败\"}";
                    return data;
                }
            }
        }else{
            String data = "{\"code\":\"777\",\"message\":\"用户未登录\"}";
            return data;
        }
    }

    @RequestMapping(value = "/countTeacher", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String countTeacher(HttpServletRequest request){
        if(CheckLogin.checkLogin(request)) {
            if (CheckLogin.checkLoginFlag(request) != 0) {
                String data = "{\"code\":\"777\",\"message\":\"没有管理员权限\"}";
                return data;
            } else {
                int count = teacherService.countTeacher();
                if(count < 1) {
                    String data = "{\"code\":\"999\",\"message\":\"数据为空\",\"count\":\"0\"}";
                    return data;
                }else {
                    String data = "{\"code\":\"200\",\"message\":\"查询成功\",\"count\":"+count+"}";
                    return data;
                }
            }
        }else{
            String data = "{\"code\":\"777\",\"message\":\"用户未登录\"}";
            return data;
        }
    }

    @RequestMapping(value = "/selectTeacher", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String selectTeacher(@RequestParam("page") String page,@RequestParam("limit") String limit,HttpServletRequest request) throws Exception {
        Integer pageInteger = Integer.valueOf(page);
        Integer limitInteger = Integer.valueOf(limit);

        List<Teacher> teachers = teacherService.selectTeacher(pageInteger,limitInteger);
        String[] colums = {"id","account","password","name","title"};
        String data = ObjtoLayJson.ListtoJson(teachers,colums);
        return data;
    }

    @RequestMapping(value = "/insertNotice", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String insertNotice(@RequestParam("content") String content,@RequestParam("flag") String flag, HttpServletRequest request){

        if(CheckLogin.checkLoginFlag(request) == 2){
            String data = "{\"code\":\"777\",\"message\":\"没有权限\"}";
            return data;
        }else{
            Notice notice = new Notice();
            notice.setContent(content);

            Boolean flagBoolean = Boolean.valueOf(flag);
            notice.setFlag(flagBoolean);

            int count = noticeService.insertNotice(notice);
            if(count == 1) {
                String data = "{\"code\":\"200\",\"message\":\"添加成功\"}";
                return data;
            }else {
                String data = "{\"code\":\"999\",\"message\":\"添加失败\"}";
                return data;
            }
        }
    }

    @RequestMapping(value = "/deleteNotice", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String deleteNotice(@RequestParam("id") String id,HttpServletRequest request){
        Integer idInteger = Integer.valueOf(id);

        if (CheckLogin.checkLoginFlag(request) != 0) {
            String data = "{\"code\":\"777\",\"message\":\"没有管理员权限\"}";
            return data;
        } else {
            int count = noticeService.deleteNotice(idInteger);
            if(count == 1) {
                String data = "{\"code\":\"200\",\"message\":\"添加成功\"}";
                return data;
            }else {
                String data = "{\"code\":\"999\",\"message\":\"添加失败\"}";
                return data;
            }
        }

    }

    @RequestMapping(value = "/updateNotice", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String updateNotice(@RequestParam("content") String content,@RequestParam("flag") String flag,
                                @RequestParam("id") String id,HttpServletRequest request) {
        Integer idInteger = Integer.valueOf(id);
        Boolean flagBoolean = Boolean.valueOf(flag);
        if (CheckLogin.checkLoginFlag(request) != 0) {
            String data = "{\"code\":\"777\",\"message\":\"没有管理员权限\"}";
            return data;
        } else {
            Notice notice = new Notice();
            notice.setId(idInteger);
            notice.setContent(content);
            notice.setFlag(flagBoolean);

            int count = noticeService.updateNotice(notice);
            if (count == 1) {
                String data = "{\"code\":\"200\",\"message\":\"修改成功\"}";
                return data;
            } else {
                String data = "{\"code\":\"999\",\"message\":\"修改失败\"}";
                return data;
            }
        }
    }

    @RequestMapping(value = "/countNotice", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String countNotice(HttpServletRequest request){

        int count = noticeService.countNotice();
        if(count < 1) {
            String data = "{\"code\":\"999\",\"message\":\"数据为空\",\"count\":\"0\"}";
            return data;
        }else {
            String data = "{\"code\":\"200\",\"message\":\"查询成功\",\"count\":"+count+"}";
            return data;
        }

    }

    @RequestMapping(value = "/selectNotice", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String selectNotice(@RequestParam("page") String page,@RequestParam("limit") String limit,HttpServletRequest request) throws Exception {
        Integer pageInteger = Integer.valueOf(page);
        Integer limitInteger = Integer.valueOf(limit);

        List<Notice> notices = noticeService.selectNotice(pageInteger,limitInteger);
        String[] colums = {"id","content","flag"};
        String data = ObjtoLayJson.ListtoJson(notices,colums);
        return data;
    }
}
