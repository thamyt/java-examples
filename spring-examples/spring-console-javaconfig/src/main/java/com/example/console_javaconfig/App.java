package com.example.console_javaconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.console_javaconfig.beans.GreetingPrototypeBean;
import com.example.console_javaconfig.beans.GreetingSingletonBean;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	@SuppressWarnings("resource")
    	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    	
    	System.out.println("Test Spring Bean declared in JavaConfig");
    	System.out.println("---------------------------------------");
    	
    	System.out.println("Testing singleton...");
    	Helloworld hwSingleton1 = (Helloworld) context.getBean("helloworldSingleton");
    	hwSingleton1.printMessage();
    	hwSingleton1.setMessage("Good Morning");
    	hwSingleton1.printMessage();
    	Helloworld hwSingleton2 = (Helloworld) context.getBean("helloworldSingleton");
    	hwSingleton2.printMessage();
    	
    	System.out.println("");
    	
    	System.out.println("Testing prototype...");
    	Helloworld hwPrototype1 = (Helloworld) context.getBean("helloworldPrototype");
    	hwPrototype1.printMessage();
    	hwPrototype1.setMessage("Good Morning");
    	hwSingleton1.printMessage();
    	Helloworld hwPrototype2 = (Helloworld) context.getBean("helloworldPrototype");
    	hwPrototype2.printMessage();
    	
    	System.out.println("\n\n");
    	
    	System.out.println("Test Spring Bean declared using Component Scan");
    	System.out.println("----------------------------------------------");
    	
    	System.out.println("Testing singleton...");
    	GreetingSingletonBean gtSingleton1 = (GreetingSingletonBean) context.getBean("greetingSingletonBean");
    	gtSingleton1.printMessage();
    	gtSingleton1.setMessage("Good Morning");
    	gtSingleton1.printMessage();
    	GreetingSingletonBean gtSingleton2 = (GreetingSingletonBean) context.getBean("greetingSingletonBean");
    	gtSingleton2.printMessage();
    	
    	System.out.println("");
    	
    	System.out.println("Testing prototype...");
    	GreetingPrototypeBean gtPrototype1 = (GreetingPrototypeBean) context.getBean("greetingPrototypeBean");
    	gtPrototype1.printMessage();
    	gtPrototype1.setMessage("Good Morning");
    	gtPrototype1.printMessage();
    	GreetingPrototypeBean gtPrototype2 = (GreetingPrototypeBean) context.getBean("greetingPrototypeBean");
    	gtPrototype2.printMessage();
    }
}
