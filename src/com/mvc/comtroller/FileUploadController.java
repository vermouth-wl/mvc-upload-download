package com.mvc.comtroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @Classname FileUploadController
 * @Description TODO
 * @Author 将烬星火是否太炽热, 明日黄花会开败阡陌
 * @Date 2020-05-04 15:52
 * @Version 1.0
 **/
@Controller
public class FileUploadController {

    // 访问默认页
    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    // 访问单文件上传页
    @RequestMapping(value = "/oneFileuploadPage")
    public String oneFileuploadPage(){
        return "oneFileuploadPage";
    }

    // 访问多文件上传页
    @RequestMapping(value = "/manyFileuploadPage")
    public String manyFileuploadPage(){
        return "manyFileuploadPage";
    }

    // 单文件上传实现
    @RequestMapping(value = "/oneFileupload")
    // @RequestParam注解将请求参数oneFile绑定到MultipartFile的file对象, required = false属性指不传oneFile参数
    public String oneFileupload(@RequestParam(value = "oneFile", required = false) MultipartFile file,
                                HttpServletRequest request, ModelMap modelMap){

        // 服务器端upload文件夹物理路径
        String path = request.getSession().getServletContext().getRealPath("upload");
        System.out.println("服务器端upload文件夹物理路径为: " + path);

        // 获取上传文件文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传文件文件名为: " + fileName);

        // 实例化一个File对象, 表示目标文件(含物理路径)
        File targetFile = new File(path, fileName);

        // 判断如果该路径不存在, 则创建该目录
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        try {
            // 将文件通过transFerTo()方法保存到物理硬件中
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件上传失败");
        }
        // 将文件完成上传地址及名称保存到modelMap对象中, 前端获取展视
        modelMap.put("fileUrl", request.getContextPath() + "/upload/" + fileName);
        modelMap.put("fileName", fileName);
        return "success";
    }

    // 多文件上传实现
    @RequestMapping(value = "/manyFileupload", method = RequestMethod.POST)
    // @RequestParam注解绑定请求参数description到String 对象中, 将请求参数manyFile绑定到泛型List<MultipartFile>集合类型中
    public String manyFileupload(@RequestParam(value = "description") String description,
                                 @RequestParam(value = "manyFile", required = false) List<MultipartFile> files,
    ModelMap modelMap, HttpServletRequest request){

        // 判断上传文件是否存在
        if (!files.isEmpty() && files.size() > 0) {

            // 创建全局变量
            StringBuffer newFileName = new StringBuffer();


            // 循环输出上传的文件
            for (MultipartFile file : files) {

                // 获取上传文件的原始名称
                String originalFileName = file.getOriginalFilename();
                System.out.println("文件原始名称为: " + originalFileName);

                // 设置上传文件的保存地址目录
                String dirpath = request.getServletContext().getRealPath("/upload/");
                System.out.println("上传文件的保存地址目录为: " + dirpath);

                // 实例化File对象
                File filePath = new File(dirpath);

                // 判断如果上传目录的地址不存在则创建目录
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }

                // 使用UUID重新命名上传的文件名称(文件描述_UUID_文件原始名称)
                newFileName.append(description + "_" + UUID.randomUUID() + "_" + originalFileName);
                System.out.println("重新命名后的文件名称为: " + newFileName);

                try {
                    file.transferTo(new File(dirpath + newFileName));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("文件上传失败");
                    return "error";
                }
            }
            modelMap.put("fileUrl", request.getContextPath() + "/upload/" + newFileName);
            return "success";
            } else {
            return "error";
        }
    }
}
