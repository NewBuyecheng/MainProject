package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/upload")
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);
    private static final String NULL_FILE = "";

    @Value("${define.nginx.path}")
    private String nginxPath;

    @Autowired
    private FileService fileService;

    @GetMapping(value = "/goToUpload")
    public String goToUploadHtml() {
        LOGGER.info("Go To upload.html");
        return "upload";
    }


    @PostMapping("/uploadFile")
    @ResponseBody
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("fileName = {}", file.getOriginalFilename());
        }
        try {
            if (file == null || NULL_FILE.equals(file.getOriginalFilename())) {
                return "null file!";
            }
            if(!fileService.saveFile(file.getBytes(), nginxPath, file.getOriginalFilename())){
                return "upload failure,fileName not right";
            }
        } catch (Exception e) {
            LOGGER.error("file upload error! ");
            e.printStackTrace();
            return "upload fail";
        }
        return "upload success";
    }


    @PostMapping("/uploadFiles")
    @ResponseBody
    public String multiFileUpload(@RequestParam("file") MultipartFile[] files) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("fileName = {}", files[0].getOriginalFilename());
        }
        try {

            for (int i = 0; i < files.length; i++) {
                //check file
                if (NULL_FILE.equals(files[i].getOriginalFilename())) {
                    continue;
                }
                fileService.saveFile(files[i].getBytes(), nginxPath, files[i].getOriginalFilename());
            }
        } catch (Exception e) {
            return "upload failure";
        }
        return "upload success";
    }

    @PostMapping("/download")
    public String  testDownload(HttpServletResponse response , Model model) {
        fileService.download(response,"新建文本文档.txt",model);

        //成功后返回成功信息
        model.addAttribute("result","下载成功");
        return "employee/EmployeeDownloadFile";
    }


}