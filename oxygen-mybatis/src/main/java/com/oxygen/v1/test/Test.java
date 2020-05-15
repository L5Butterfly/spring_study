package com.oxygen.v1.test;

/**
 * 占位符，顺序传参
 * @author oxygen
 * @date 2020/5/14
 **/
public class Test {
    public static void main(String[] args) {
        String input = "%d %s %d";
        Object[] objs = new Object[]{1,"2",3};
        String result = String.format(input,objs);
        System.out.println(result);
    }
}
