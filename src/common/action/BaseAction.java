package common.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/*
 * 在项目启动时struts的过滤器已经把相应的内置对象和对应的map存储
 * 到了actioncontext中，如果实现了相应的Aware接口，就会从actioncontext
 * 中获取相应的map精心on个传入，实现的拦截为servletconfig
 */
@SuppressWarnings({"rawtypes", "unchecked", "serial"})
@Controller
@Scope("prototype")
public class BaseAction<T> extends ActionSupport implements RequestAware,
        SessionAware, ApplicationAware, ModelDriven<T> {

    // 前台传入，模型驱动
    protected T model;

    // 获取要删除的id数组
    protected String ids;

    // 返回的流
    protected InputStream inputStream;

    // 分页参数 page
    protected Integer page;

    // 分页参数 rows
    protected Integer rows;

    // 分页内容
    protected Map<String, Object> pageMap = null;

    // 普通list
    protected List<T> jsonList = null;

    // 工具类
    //@Resource
    //protected FileUpload fileUpload;

    // 三个context
    protected Map<String, Object> request;
    protected Map<String, Object> session;
    protected Map<String, Object> application;

    // 实现ModelDriven接口方法将category模型压到栈顶
    @Override
    public T getModel() {
        // 泛型转换模型
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        Class clazz = (Class) type.getActualTypeArguments()[0];
        try {
            model = (T) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return model;
    }


    // get、set方法
    @Override
    public void setApplication(Map<String, Object> application) {
        this.application = application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Map<String, Object> getPageMap() {
        return pageMap;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public List<T> getJsonList() {
        return jsonList;
    }

}
