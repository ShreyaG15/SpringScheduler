package com.JDBC.MySpringJDBC.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerConfig {

    public String scannerNext(){
        Scanner sc=new Scanner(System.in);
        return sc.next() ;
    }

    public  int scannerNextInt(){
        Scanner sc=new Scanner(System.in);
        return sc.nextInt();
    }
}
