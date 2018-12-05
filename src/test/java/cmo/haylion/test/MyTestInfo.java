package cmo.haylion.test;

import com.haylion.mp.beans.User;
import org.junit.Test;

public class MyTestInfo {

    @Test
    public void testClass() throws Exception{
        Class clazz = null;

        //1.得到Class对象
        clazz = User.class;
        java.lang.reflect.Method[] methods = clazz.getMethods();
        for(java.lang.reflect.Method method:methods){
            System.out.println(method.getName());
        }
    }
}
