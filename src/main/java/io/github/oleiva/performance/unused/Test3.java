//package io.github.oleiva.performance;
//
//import java.util.Map;
//
//import static sun.font.FontDesignMetrics.MetricsKey.key;
//
//
///**
// * Created by Oleh Ivashko on 27.06.2016.
// */
//public class Test3 {
//    public static void main(String[] args) {
//        public static String get(String key) {
//            Map map = System.getenv(); // уже здесь можно видеть неполный набор переменных
//            String variable = ((String) map.get(key)).trim();
//            final int length = variable.length();
//            if (length > 0) {
//                final char ch = variable.charAt(length - 1);
//                if (ch == '/' || ch == '\\')
//                    return variable.substring(0, length - 1);
//            } else
//                variable = null;
//            return variable;
//        }
//    }
//}
