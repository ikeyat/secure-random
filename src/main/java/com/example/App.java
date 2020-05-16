package com.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.Provider;
import java.security.SecureRandom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.NativePRNG;

@SpringBootApplication
@RestController
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@GetMapping("/secure-random")
	public String secureRandom() {
		SecureRandom secureRandom = new SecureRandom();
		Provider provider = secureRandom.getProvider();
		
		StringBuilder sb = new StringBuilder();
		sb.append("getProvider().getName(): ").append( provider.getName()).append("<br />");
		sb.append("getAlgorithm(): ").append(secureRandom.getAlgorithm()).append("<br />");
		appendEgd(sb);
		
		sb.append("System Properties: <ul>");
		for (Object key : System.getProperties().keySet()) {
			Object value = System.getProperty((String) key);
			sb.append("<li>").append(key).append("=").append(value).append("</li>");
		} 
		sb.append("</ul>");
		return sb.toString();
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	private void appendEgd(StringBuilder sb) {
        Method method = null;
        try {
            method = NativePRNG.class.getDeclaredMethod("getEgdUrl");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        method.setAccessible(true);

        Object object = null;
        try {
            object = method.invoke(NativePRNG.class);
            sb.append("NativePRNG.getEgdUrl(): ").append(((URL) object).toString()).append("<br />");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        

        System.out.println(object);
	}
}

