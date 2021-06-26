package com.skin.demo.EncodeAndDecode;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/2/27 11:53
 */
public class Test {

    public static int encode(Student stu, byte[] data) throws UnsupportedEncodingException {
        ByteBuffer bbuf = ByteBuffer.wrap(data);
        //id编码
        bbuf.putInt(stu.id);
        //name
        byte[] strbuf = stu.name.getBytes("UTF-8");
        bbuf.putShort((short) strbuf.length);
        bbuf.put(strbuf);

        //sex
        bbuf.put(stu.sex ? (byte) 1 : (byte) 0);

        //cellphone
        strbuf = stu.cellphone.getBytes("UTF-8");
        bbuf.putShort((short) strbuf.length);
        bbuf.put(strbuf);
        return bbuf.position();
    }

    public static Student decode(byte[] data, int n) throws UnsupportedEncodingException {
        ByteBuffer bbuf = ByteBuffer.wrap(data);
        Student stu = new Student();

        //id
        stu.id = bbuf.getInt();

        //name
        byte[] strbuf = new byte[1000];
        int strlen = bbuf.getShort();//
        bbuf.get(strbuf, 0, strlen);
        stu.name = new String(strbuf, 0, strlen, "UTF-8");
        //sex
        stu.sex = (bbuf.get() != 0);
        strlen = bbuf.getShort();
        bbuf.get(strbuf, 0, strlen);
        stu.cellphone = new String(strbuf, 0, strlen, "UTF-8");
        return stu;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        //
        byte[] data = new byte[1000];

        //
        Student s1 = new Student();
        s1.id = 20190001;
        s1.name = "小明";
        s1.sex = true;
        s1.cellphone = "13810012345";

        // encodeֵ
        int n = encode(s1, data);

        Student s2 = decode(data, n);

        System.out.println(s2.toString());
        System.out.println("\nexit");

    }

}
