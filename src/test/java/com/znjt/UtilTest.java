package com.znjt;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * Created by qiuzx on 2019-03-26
 * Company BTT
 * Depart Tech
 */
public class UtilTest {
    @Test
    public void test01(){
        String paths = "D:/es/IR200CM/2019/03/05/11/16/IR200CM2019030511163924.jpg;\r\nD:/es/IR200CM/2019/03/05/11/16/IR200CM2019030511163976.jpg";
        paths = paths.replaceAll("(\r\n|\r|\n|\n\r)", "");
        System.err.println(paths);
    }
}
