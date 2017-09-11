package com.qee.business.test;

import com.qee.api.model.Request;
import com.qee.api.model.RequestParam;
import com.qee.business.service.HelloService;
import com.qee.business.service.impl.HelloServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by zhuqi on 2017/9/10.
 */
public class RequestParamTest {

    public static String f() {
        RequestParam firstParam = new RequestParam();

        firstParam.setIndex(1);
        firstParam.setComplex(0);
        firstParam.setValue("teacherId");

        RequestParam secondParam = new RequestParam();
        secondParam.setIndex(2);
        secondParam.setComplex(1);
        secondParam.setClazz(User.class);
        secondParam.setValue(new User(1, "xiaoming"));


        RequestParam[] all = new RequestParam[]{firstParam, secondParam};
        Request request = new Request();
        request.setParams(all);

        String json = JSONObject.fromObject(request).toString();
        System.out.println(json);
        return json;

    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // String teacherId ,User user
        String json=f();
        JSONObject jsonObject = JSONObject.fromObject(json);
        Object rq= JSONObject.toBean(jsonObject, Request.class);

        ///////////////////////////////

        ///json to Object

        JSONArray params = (JSONArray) jsonObject.get("params");
        
        if(params.isArray()){
            Object[] objects = params.toArray();
            System.out.println(objects);
        }
        Iterator<JSONObject> iterator = params.iterator();
        Object[] objects = new Object[2];
        while (iterator.hasNext()) {
            JSONObject jObj = iterator.next();
            int index = jObj.getInt("index");
            int complex = jObj.getInt("complex");
            if (complex == 0) {
                objects[index - 1] = jObj.get("value");
            } else {
                String strClass = jObj.getString("clazz");
                Class<?> clazz = Class.forName(strClass);
                Object value = jObj.get("value");
                Object obj = JSONObject.toBean((JSONObject) value, clazz);
                objects[1] = obj;
            }
        }
        System.out.println(objects);
        HelloService helloService = new HelloServiceImpl();
        helloService.userHello((String) objects[0], (User) objects[1]);
        //   Method userHello = HelloServiceImpl.class.getMethod("userHello");
        Method[] methods = HelloServiceImpl.class.getMethods();
        //userHello.invoke(helloService,objects);
        for (Method method : methods) {
            if (method.getName().equals("userHello")) {
                method.invoke(helloService, objects);
            }
        }
        System.out.println(methods);
    }
}
