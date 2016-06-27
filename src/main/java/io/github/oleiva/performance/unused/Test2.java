//package io.github.oleiva.performance;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.Map;
//
//import static javax.swing.text.html.HTML.Attribute.TARGET;
//
///**
// * Created by Oleh Ivashko on 27.06.2016.
// */
//public class Test2 {
//
//    public static void main(String[] args) {
//        String[] vCmd = {System.getenv("ANT_HOME") + "/bin/ant", "-f",
//                ANT_BUILD_FILE, TARGET};
//        ProcessBuilder pb = new ProcessBuilder(vCmd);
//        Map<String, String> env = pb.environment();
//        env.put("CLASSPATH",
//                antHome + "/lib/ant.jar:"
//                        + antHome + "/lib/ant-launcher.jar:"
//                        + antHome + "/lib/ant-nodeps.jar:"
//        );
//        try {
//
//            Process process = pb.start();
//            InputStream is = process.getInputStream();
//            InputStreamReader isr = new InputStreamReader(is);
//            BufferedReader br = new BufferedReader(isr);
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//            pb.wait();
//        } catch (Exception e) catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(e.getMessage());
//
//
//    }
//}
//}
