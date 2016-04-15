package cn.edu.ctbu.sw.action;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;

/**
 * Created by ShuangYang on 2015/11/26.
 */
public class ImageAction {
    private File pic;   //(在此省略 get 和 set 方法)
    private String picFileName; //（省略get和set方法， 该属性Struts2会自动赋值为上传文件的文件名）

    public String uploadPic() {
        String[] str = {".jpg", ".jpeg", ".bmp", ".gif"};
        // 获取用户登录名
      /*  TbUser curruser = (TbUser) getValue(SCOPE_SESSION, "curruser");*/
        // 限定文件大小是4MB
        if (pic == null || pic.length() > 4194304) {
            //文件过大
            return "error";
        }
        for (String s : str) {
            if (picFileName.endsWith(s)) {
                String realPath = ServletActionContext.getServletContext().getRealPath("/uploadpic");// 在tomcat中保存图片的实际路径  ==  "webRoot/uploadpic/"
                File saveFile = new File(new File(realPath), "新文件名.jpg"); // 在该实际路径下实例化一个文件
                // 判断父目录是否存在
                if (!saveFile.getParentFile().exists()) {
                    saveFile.getParentFile().mkdirs();
                }
                try {
                    // 执行文件上传
                    // FileUtils 类名 org.apache.commons.io.FileUtils;
                    // 是commons-io包中的，commons-fileupload 必须依赖
                    // commons-io包实现文件上次，实际上就是将一个文件转换成流文件进行读写
                    FileUtils.copyFile(pic, saveFile);
                } catch (IOException e) {
                    return "imageError";
                }
            }
        }
        return "success";
    }
}
