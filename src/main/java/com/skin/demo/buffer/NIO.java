package com.skin.demo.buffer;

import com.skin.demo.EncodeAndDecode.Student;

import java.io.FileOutputStream;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/3/27 14:30
 */
public class NIO {


    public static void main(String[] args) throws Exception {
        StringBuffer str = new StringBuffer();
        char[] buf = new char[1024];
//        FileReader f = new FileReader("C:\\Users\\Administrator\\Desktop\\dnsaiwdsssssmt.txt");
//        while(f.read(buf)>0){
//            str.append(buf);
//        }
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\dnsaiwdsssssmt.txt");
//        byte[] bytes = str.toString().getBytes();
//        fileOutputStream.write(bytes);
        Student student = new Student();
        student.setName("Sdawda");
        student.setId(123212);
        byte[] bytes1 = student.toString().getBytes();
        fileOutputStream.write(bytes1);
    }
}
