package cn.edu.ctbu.sw.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import cn.edu.ctbu.sw.model.FileImage;
import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("fileUpload")
public class FileUploadUtil implements FileUpload {

    @Value("#{prop.basePath+prop.filePath}")
    private String filePath;

    // 实现文件上传功能，返回上传后的新文件名称
    @Override
    public String uploadFile(FileImage fileImage) {
        String pic = newFileName(fileImage.getFilename());
        try {
            FileUtil.copyFile(fileImage.getFile(), new File(filePath, pic));
            return pic;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } finally {
            fileImage.getFile().delete();
        }
    }

    // 获取文件扩展名
    private String getFileExt(String fileName) {
        return FilenameUtils.getExtension(fileName);
    }

    // 生成UUID文件名
    private String newFileName(String fileName) {
        String ext = getFileExt(fileName);
        return UUID.randomUUID().toString() + "." + ext;
    }

}
