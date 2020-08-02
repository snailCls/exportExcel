package zzh.excel.excel.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author snail
 */
public class FileUtils {

    public static String upload(MultipartFile file,String path, String fileName) {

        String newFileName = FileNameUtil.getFileName(fileName);

        //生成新的文件名
        String realPath = path + "/" + newFileName;

        File dest = new File(realPath);

        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try{
            //保存文件
            file.transferTo(dest);
            return newFileName;
        }catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    }

}
