package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Student;
import cn.zcbigdata.mybits_demo.mapper.StudentMapper;
import cn.zcbigdata.mybits_demo.service.FileService;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class FileServiceImpl implements FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);
    private static final String UTF_8 = "UTF-8";

    @Value("${define.nginx.path}")
    private String nginxPath;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Boolean saveFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            // 如果指定文件夹不存在则创建该文件夹
            targetFile.mkdirs();
        }
        int length = fileName.length();
        String fileExtension = fileName.substring(length-4,length);
        if(!fileExtension.equals(".xml")) {
            return false;
        }else {
            FileOutputStream out = new FileOutputStream(filePath + fileName);
            out.write(file);
            out.flush();
            out.close();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            try{
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document document = db.parse(filePath + fileName);
                NodeList studentList = document.getElementsByTagName("student");

                List<Student> students = new ArrayList<Student>();

                for (int i = 0; i < studentList.getLength(); i++) {
                    Node student = studentList.item(i);
                    NodeList childNodes = student.getChildNodes();
                    students.add(new Student());
                    for (int k = 0; k < childNodes.getLength(); k++) {
                        if(childNodes.item(k).getNodeType() == Node.ELEMENT_NODE){
                            if(childNodes.item(k).getNodeName().equals("account")){
                                students.get(i).setAccount(childNodes.item(k).getFirstChild().getNodeValue());
                            }else if(childNodes.item(k).getNodeName().equals("password")){
                                students.get(i).setPassword(childNodes.item(k).getFirstChild().getNodeValue());
                            }else if(childNodes.item(k).getNodeName().equals("name")){
                                students.get(i).setName(childNodes.item(k).getFirstChild().getNodeValue());
                            }
                            //获取了element类型节点的节点名
                            System.out.print("第" + (k + 1) + "个节点的节点名：" + childNodes.item(k).getNodeName());
                            //获取了element类型节点的节点值
                            System.out.println("--节点值是：" + childNodes.item(k).getFirstChild().getNodeValue());
                        }
                    }
                }
                System.out.println("students info:");
                for (Student stu:students
                     ) {
                    System.out.println(stu.toString());
                }
                studentMapper.insertStuFromXml(students);
            }catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public void download(HttpServletResponse response, String filename, Model model) {
        //待下载文件名
        String fileName = "新建文本文档.txt";
        //设置为png格式的文件
//        response.setHeader("content-type", "image/png");
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentType("application/force-download");
        response.setCharacterEncoding(UTF_8);
        // 设置下载后的文件名以及header
        response.addHeader("Content-disposition", "attachment;fileName=" + URLEncoder.encode(filename));
        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();

            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(new FileInputStream(new File("E:/bishe/" + fileName )));
            int read = bis.read(buff);

            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
            //出现异常返回给页面失败的信息
            System.out.println("--------------------------------");
            model.addAttribute("result","下载失败");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}