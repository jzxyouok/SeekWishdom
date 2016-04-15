package test;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

/**
 * Created by Pomodoro on 2015/11/15.
 */
public class ExportSql {

    @Test
    public void exportSql() {
        Configuration configuration = new Configuration().configure();
        SchemaExport sexport = new SchemaExport(configuration);
        sexport.setFormat(true);//格式化输出
        sexport.setDelimiter(";");
        sexport.setOutputFile("D:\\auto.sql");
        sexport.create(true, true);
    }
}
