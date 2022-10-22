package com.mytomcat;

public class HelloWorldServlet extends MyServlet{
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("hello world get test...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("hello world post test...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
