package com.example;

import java.security.Provider;
import java.security.SecureRandom;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SecureRandom secureRandom = new SecureRandom();
        Provider provider = secureRandom.getProvider();
        System.out.println("getProvider().getName(): " + provider.getName());
        System.out.println("getAlgorithm()" + secureRandom.getAlgorithm());
    }
}
