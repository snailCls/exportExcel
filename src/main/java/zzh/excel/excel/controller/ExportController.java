package zzh.excel.excel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zzh.excel.excel.service.ExportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author snail
 */
@RestController
public class ExportController {

    @Autowired
    private ExportService exportService;

    @GetMapping("/exportExcel")
    public ResponseEntity<byte[]> exportExcel(HttpServletRequest request, HttpServletResponse response) {
        return exportService.exportExcel(request, response);
    }

}
