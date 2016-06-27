//package io.github.oleiva.performance;
//
///**
// * Created by Oleh Ivashko on 27.06.2016.
// */
//import java.lang.*;
//
//import static com.sun.deploy.trace.TraceLevel.TEMP;
//import static com.sun.rowset.JdbcRowSetResourceBundle.PATH;
//import static com.sun.security.sasl.digest.DigestMD5Server.USERNAME;
//
//
//public class SystemDemo {
//
//    public static void main(String[] args) throws Exception {
//
//        // gets the value of the specified environment variable "PATH"
//        System.out.println("System.getenv("PATH") = ");
//        System.out.println(System.getenv("PATH"));
//
//        // gets the value of the specified environment variable "TEMP"
//        System.out.print("System.getenv("TEMP") = ");
//        System.out.println(System.getenv("TEMP"));
//
//        // gets the value of the specified environment variable "USERNAME"
//        System.out.print("System.getenv("USERNAME") = ");
//        System.out.println(System.getenv("USERNAME"));
//
//    }
//}