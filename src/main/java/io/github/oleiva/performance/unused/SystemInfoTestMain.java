//package io.github.oleiva.performance;
//
//import java.io.IOException;
//
///**
// * Created by Oleh Ivashko on 27.06.2016.
// */
//public class SystemInfoTestMain {
//    public static void main(String[] args) {
//
//        SystemInfoTest systemInfoTest =new SystemInfoTest();
//
//        systemInfoTest.testCentralProcessor();
//        systemInfoTest.testDisplay();
//        try {
//            systemInfoTest.testFileSystem();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        systemInfoTest.testPowerSource();
//        systemInfoTest.testGlobalMemory();
//        systemInfoTest.testOSVersion();
//        try {
//            systemInfoTest.testDisks();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            systemInfoTest.testNetworkInterfaces();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//}
