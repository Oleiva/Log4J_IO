package io.github.oleiva;

/**
 * Created by Oleh Ivashko on 21.06.2016.
 */

public class Main {

    public static String username = "sparktestapp@gmail.com";
    public static String password = "spark4testapp";

    private static SenderTLS tlsSender = new SenderTLS(username, password);
    private static SenderSSL sslSender = new SenderSSL(username, password);

    public static void main(String[] args){
        tlsSender.send("This is Subject", "TLS: This is text!", "oleg.Ivashko@yandex.ua", "Oleg.ivashko@gmail.com");
        sslSender.send("This is Subject", "SSL: This is text!", "oleg.Ivashko@yandex.ua", "Oleg.ivashko@gmail.com");
    }
}