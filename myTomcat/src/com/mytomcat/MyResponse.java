package com.mytomcat;

import java.io.IOException;
import java.io.OutputStream;

public class MyResponse {
    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content)  throws IOException {

        String httpResponse = "HTTP/1.1 200 OK\n" +
                "Content-Type text/html\n" +
                "\r\n" +
                "<html><bode>" +
                content +
                "</body></html>";

        outputStream.write(httpResponse.getBytes());
        outputStream.close();
    }
}
