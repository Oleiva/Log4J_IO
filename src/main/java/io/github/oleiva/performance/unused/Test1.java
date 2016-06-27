package io.github.oleiva.performance.unused;

/**
 * Created by Oleh Ivashko on 27.06.2016.
 */
public class Test1 {

    public static void main(String[] args) {
        System.out.println(System.getenv("PROCESSOR_IDENTIFIER"));
        System.out.println( System.getenv("PROCESSOR_ARCHITECTURE"));
        System.out.println(System.getenv("PROCESSOR_ARCHITEW6432") );
        System.out.println( System.getenv("NUMBER_OF_PROCESSORS"));

    }
}
