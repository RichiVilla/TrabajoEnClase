/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.springeci;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ricardo.villamizar-m
 */
public class SpringECI {

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InvocationTargetException {
        Class c = Class.forName(args[0]);
        Map<String, Method> services = new HashMap();
        
        if (c.isAnnotationPresent(RestController.class)){
            Method[] methods = c.getDeclaredMethods();
            for (Method m : methods){
                if (m.isAnnotationPresent(GetMapping.class)){
                    String key = m.getAnnotation(GetMapping.class).value();
                    services.put(key, m);
                }
            }
        }
   
        
        URL serviceurl = new URL("http://localhost8080//App/pi");
        String path = serviceurl.getPath();
        System.out.println("Path: " + path);
        String servicename = path.substring(4);
        System.out.println("Service name: " + servicename);
        
        
        Method ms = services.get(servicename);
        System.out.println("Respuesta servicio: " + ms.invoke(ms));

    }
}