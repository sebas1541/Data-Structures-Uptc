package co.edu.uptc.test;

import co.edu.uptc.net.HelloServer;

import java.io.IOException;

public class TestServer {
    public static void main(String[] args) throws IOException {
        new HelloServer().start();
    }
}
