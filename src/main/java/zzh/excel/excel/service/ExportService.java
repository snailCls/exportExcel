package zzh.excel.excel.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * excel导出
 * @author snail
 */

public interface ExportService {

    /**
     *  返回字节实体对象
     * @param request
     * @param response
     * @return
     */
    ResponseEntity<byte[]> exportExcel(HttpServletRequest request, HttpServletResponse response);

}
