package com.mytomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MyTomcat {
    private int port;
    private Map<String, String> urlServletMap = new HashMap<>();

    public MyTomcat(int port) {
        this.port = port;
    }

    public void start() {
        initServletMapping();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("myTomcat is starting.... at " + port);

            while (true) {
                Socket socket = serverSocket.accept();

                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                MyRequest myRequest = new MyRequest(inputStream);
                MyResponse myResponse = new MyResponse(outputStream);

                dispatch(myRequest, myResponse);

                socket.close();
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initServletMapping() {
        for (ServletMapping servletMapping : ServletMappingConfig.servletMappingList) {
            urlServletMap.put(servletMapping.getUrl(), servletMapping.getClazz());
        }
        System.out.println("initServletMapping : " + urlServletMap);
    }

    private void dispatch(MyRequest myRequest, MyResponse myResponse) {
//        System.out.println("dispatch myRequest : " + myRequest);
        String clazz = urlServletMap.get(myRequest.getUrl());
//        System.out.println("dispatch : " + clazz);

        try {
            Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);
//            System.out.println("myServletClass : " + myServletClass);
            MyServlet myServlet = myServletClass.newInstance();
//            System.out.println("myServlet : " + myServlet);

            myServlet.service(myRequest, myResponse);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MyTomcat(8081).start();
    }
}
