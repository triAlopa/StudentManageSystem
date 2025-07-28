package config;

import com.sun.org.apache.xml.internal.security.Init;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class queryPage {
    public static final Integer PAGE_SIZE=10;
    public static final  Integer PAGE_NUM=0;

    public static Integer pageSize=PAGE_SIZE;
    public static Integer pageNum=PAGE_NUM;



    //用户离开查询页面，调用该方法初始化 页面相关数据
    public static void InitPage(){
        pageSize=PAGE_SIZE;
        pageNum=PAGE_NUM;
    }


}
