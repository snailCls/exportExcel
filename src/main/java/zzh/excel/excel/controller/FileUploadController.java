package zzh.excel.excel.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import zzh.excel.excel.util.FileUtils;

/**
 * 文件上传控制层
 *
 * @author snail
 */
@RestController
public class FileUploadController {

    @Value("${web.upload-path}")
    private String path;

    @PostMapping("/fileUpload")
    public String upload(@RequestParam("file")MultipartFile file) {
        //1 定义要上传文件的存放位置
        String localPath = "E:/upload/image";

        //2 获得文件名字
        String fileName = file.getOriginalFilename();

        //3 上传失败提示
        String warning = "";
        String newFileName = FileUtils.upload(file, path, fileName);
        if(newFileName != null) {
            warning = "上传成功";
        }else {
            warning = "上传失败";
        }
        System.out.println(warning);
        return localPath + "/" + newFileName;
    }

}
