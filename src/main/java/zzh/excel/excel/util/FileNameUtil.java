package zzh.excel.excel.util;

/**
 * @author snail
 */
public class FileNameUtil {

    /**
     * 获取文件后缀名
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     * @param fileOriginName
     * @return
     */
    public static String getFileName(String fileOriginName) {
        return UUIDUtils.getUUID() + FileNameUtil.getSuffix(fileOriginName);
    }

}
