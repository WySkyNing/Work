package com.wy.work.reflex;

import java.lang.reflect.Field;

/**
 * 通过反射获取属性名和值 Demo
 */

public class Text {

    public static void main(String[] arg) {

        getAttributeName();
        getAttributeValue();

    }

    /**
     * 通过Field反射的各方法访问属性
     */
    private static void getAttributeName() {

        Person person = new Person();

        try {
            //通过Class.getDeclaredField(String name)获取类或接口的指定已声明字段。
            Field field1 = person.getClass().getDeclaredField("name");
            System.out.println("-----Class.getDeclaredField(String name)用法-------");
            System.out.println(field1);
            System.out.println();


            //通过Class.getDeclaredFields()获取类或接口的指定已声明字段。
            System.out.println("-----Class.getDeclaredFields()用法-------");
            Field[] field2 = person.getClass().getDeclaredFields();
            for (Field field : field2) {
                System.out.println(field);
            }
            System.out.println();


            //通过Class.getField(String name)返回一个类或接口的指定公共成员字段，私有成员报错。
            System.out.println("-----Class.getField(String name)用法-------");
            Field field3 = person.getClass().getField("name");
            System.out.println(field3);
            System.out.println();


            //通过Class.getField()，返回 Class 对象所表示的类或接口的所有可访问公共字段。
            System.out.println("-----Class.getFields()用法-------");
            Field[] field4 = person.getClass().getFields();
            for (Field field : field4) {
                //因为只有name属性为共有，因此只能遍历出name属性
                System.out.println(field);
            }
            System.out.println();


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取属性的属性值并修改属性值
     */
    private static void getAttributeValue() {

        Person person = new Person();


        try {

            //通过Class.getDeclaredField(String name)获取类或接口的指定属性值。
            Field field1 = person.getClass().getDeclaredField("name");
            System.out.println("-----Class.getDeclaredField(String name)用法-------");
            System.out.println(field1.get(person));
            System.out.println();



            //通过Class.getDeclaredFields()获取类或接口的指定属性值。
            System.out.println("-----Class.getDeclaredFields()用法-------");
            Field[] field2 = person.getClass().getDeclaredFields();
            for (Field field : field2) {
                field.setAccessible(true);
                System.out.println(field.get(person));
            }
            System.out.println();



            //修改属性值
            System.out.println("----修改name属性------");
            field1.set(person, "Maoge");
            //修改后再遍历各属性的值
            Field[] f3 = person.getClass().getDeclaredFields();
            for (Field fields : f3) {
                /**
                 *   需要获取私有属性的属性值得时候，我们必须设置Accessible为true
                 */
                fields.setAccessible(true);
                System.out.println(fields.get(person));
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
