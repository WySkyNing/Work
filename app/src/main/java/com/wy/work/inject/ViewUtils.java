package com.wy.work.inject;

import android.app.Activity;

import java.lang.reflect.Field;

/**
 *
 //Field用于获取某个类的属性或该属性的属性值

 1：Class.getDeclaredField(String name);

 返回一个 Field 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明字段（包括私有成员）。

 2：Class.getDeclaredFields();

 返回 Field 对象的一个数组，该数组包含此 Class 对象所表示的类或接口所声明的所有字段（包括私有成员）。

 3:Class.getField(String name);

 返回一个 Field 对象，它反映此 Class 对象所表示的类或接口的指定公共成员字段。

 4:Class.getFields();

 返回一个包含某些 Field 对象的数组，该数组包含此 Class 对象所表示的类或接口的所有可访问公共字段。
 */

public class ViewUtils {

    public static ViewUtils viewUtils;

    public Activity activity;

    public ViewUtils(Activity activity){

        this.activity = activity;
    }

    public static ViewUtils inject(Activity activity){

        if (viewUtils != null){

            return viewUtils;
        }else {

            viewUtils = new ViewUtils(activity);
            return viewUtils;
        }
    }



    /**
     * 通过ctx.getClass（）得到实体类。然后得到这个类中所有的注解跟ViewInject注解类匹配。如果匹配成功，则set（ctx,ctx.findById()）；
     * */
    private void autoInjectAllField(){

        Class clazz = activity.getClass();


        //clazz.getDeclaredFields()  返回 Field 对象的一个数组，该数组包含此 Class 对象所表示的类或接口所声明的所有字段（包括私有成员）。
        Field [] fields = clazz.getDeclaredFields();

        for (Field field : fields) {

            if (field.isAnnotationPresent(ViewInject.class)){

                //返回改程序元素上存在的、指定类型的注解，如果该类型注解不存在，则返回null。
                ViewInject viewInject = field.getAnnotation(ViewInject.class);

                int id = viewInject.value();

                if (id > 0){

                    field.setAccessible(true);

                    try {

                        field.set(activity,activity.findViewById(id));

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }
}
