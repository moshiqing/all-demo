package com.skin.demo.annotationDemo;


import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class test {

    public static int val = 0;

    public static String query(Person person) {

        StringBuilder sb = new StringBuilder();
        Class p = person.getClass();
        boolean exist = p.isAnnotationPresent(Table.class);
        if (!exist) {
            return null;
        }
        Table table = (Table) p.getAnnotation(Table.class);
        String tableName = table.value();

        sb.append("select * form ").append(tableName).append(" where 1=1");
        Field[] fArray = p.getDeclaredFields();
        for (Field field : fArray) {
            boolean fExist = field.isAnnotationPresent(Column.class);
            if (!fExist) {
                return null;
            }
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            String fileName = field.getName();//字段的方法
            Object fieldVale = null;
            String first = fileName.substring(0, 1).toUpperCase();
            String end = fileName.substring(1);
            String getMethodName = "get" + first + end;

            try {

                Method method = p.getMethod(getMethodName);
                fieldVale = method.invoke(person);

                Method method1 = p.getMethod("print", new Class[]{String.class});
                Object str = method1.invoke(person, "q123");  //慕课网有
                System.out.println(str);

            } catch (Exception e) {
                e.printStackTrace();
            }
            sb.append(" and ").append(columnName).append("=").append(fieldVale);

        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {

//        Person p1=new Person();
//        p1.setName("ai");
//        p1.setOld("30");
//
//        String str =query(p1);
//        System.out.println(str);
//
//        p1.setName("moshiqing");
//        p1.setOld("7329");
//        String str1=query(p1);
//        System.out.println(str1);


        Class c = test.class;
//        System.out.println(c.toString());

        Field[] fileds = c.getDeclaredFields();

        for (Field f : fileds) {
            //获取成员变量的类型
            Class filedType = f.getType();
            System.out.println(filedType.getName() + " ::: " + f.getName() + ":::");
        }

    }
}
