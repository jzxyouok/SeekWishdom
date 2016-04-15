package cn.edu.ctbu.sw.util;


import cn.edu.ctbu.sw.model.FileImage;

public interface FileUpload {

	// 实现文件上传功能，返回上传后的新文件名称
	public abstract String uploadFile(FileImage fileImage);

}