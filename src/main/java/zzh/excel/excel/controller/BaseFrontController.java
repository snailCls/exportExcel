package zzh.excel.excel.controller;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author snail
 */
@Validated
public class BaseFrontController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<byte[]> buildResponseEntity(InputStream is, String name) throws IOException {
        logger.info(">>>>>>>>>>>>开始下载文件>>>>>>>>>>>>>>");
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("download : " + name);
        }
        HttpHeaders httpHeaders =new HttpHeaders();
        String fileSuffix = name.substring(name.lastIndexOf('.') + 1);
        fileSuffix = fileSuffix.toLowerCase();

        Map<String, String> arguments = new HashMap<>();
        arguments.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        arguments.put("xls", "application/vnd.ms-excel");
        String contentType = arguments.get(fileSuffix);
        httpHeaders.add("content-Type", (StringUtils.hasText(contentType)? contentType : "application/x-download"));
        if(is != null && is.available() != 0) {
            httpHeaders.add("Content-Length", String.valueOf(is.available()));
            httpHeaders.add("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" +  URLEncoder.encode(name, "UTF-8"));
            byte[] bs = IOUtils.toByteArray(is);
            logger.info(">>>>>>>>>>>>>>>>>>结束下载文件-有记录>>>>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>>>>>>>>结束导出excel>>>>>>>>>>>>>>>>>>");
            return new ResponseEntity<byte[]>(bs, httpHeaders, HttpStatus.OK);
        }else {
            String string = "数据为空";
            httpHeaders.add("Content-Length", "0");
            httpHeaders.add("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + URLEncoder.encode(name, "UTF-8"));
            logger.info(">>>>>>>>>>>>>>>>>>>结束下载文件-无记录>>>>>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>>>>>>>>>结束导出excel>>>>>>>>>>>>>>>>>>>>");
            return new ResponseEntity<byte[]>(string.getBytes(), httpHeaders, HttpStatus.OK);
        }
    }

}
