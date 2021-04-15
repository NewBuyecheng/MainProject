package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.entity.Admin;
import cn.zcbigdata.mybits_demo.entity.Student;
import cn.zcbigdata.mybits_demo.entity.Teacher;
import cn.zcbigdata.mybits_demo.entity.User;
import cn.zcbigdata.mybits_demo.service.AdminService;
import cn.zcbigdata.mybits_demo.service.StudentService;
import cn.zcbigdata.mybits_demo.service.TeacherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;


@Controller
public class LoginController {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/index1")
    public String index1(){
        return "back";
    }

    @RequestMapping("/back")
    public String back() {
        return "back";
    }


    @RequestMapping("{ip}")
    public String ip(@PathVariable String ip){
        return ""+ip+"";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody  //返回json类型的数据
    public String login(@RequestParam("flag") String flag, @RequestParam("account") String account, @RequestParam("password") String password, HttpServletRequest request){
        Integer integerflag = Integer.valueOf(flag);
        String UserName = "";
        HttpSession session = request.getSession();
        User user = new User();

        // 登录操作
        if (integerflag == 0){
            Admin admin = adminService.find(account);
            if(admin.getPassword().equals(password)){
                UserName = admin.getAccount();
                //存储id
                int id = admin.getId();
                session.setAttribute("userid",id);
                //标识身份
                session.setAttribute("flag",0);
            }

        }else if(integerflag == 1){
            Teacher teacher = teacherService.find(account);
            if(teacher.getPassword().equals(password)){
                UserName = teacher.getName();
                //存储id
                int id = teacher.getId();
                session.setAttribute("userid",id);
                //标识身份
                session.setAttribute("flag",1);
            }

        }else{
            Student student = studentService.find(account);
            if (student.getPassword().equals(password)){
                UserName = student.getName();
                //存储id
                int id = student.getId();
                session.setAttribute("userid",id);
                //标识身份
                session.setAttribute("flag",2);
            }
        }


        if (null != session.getAttribute("loginUser")) {
            // 清除旧的用户
            session.removeAttribute("loginUser");
        }
        // 新登录，需要构建一个用户
        // 随机生成一个用户
        String id = UUID.randomUUID().toString();
        user.setId(id);
        System.out.println(id + "****************");
        // 将用户放入session
        session.setAttribute("loginUser", user);


        // 将登录信息放入数据库，便于协查跟踪聊天者
        System.out.println("新用户诞生了：" + user);
        String data =  "{\"userName\":\""+UserName+"\",\"flag\":"+integerflag+"}";
        return data;

    }



}
