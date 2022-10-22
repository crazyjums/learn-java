package com.mytomcat;

import java.io.IOException;
import java.io.InputStream;

public class MyRequest {
    private String url;
    private String method;

    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];
        int length = 0;
        if ((length = inputStream.read(httpRequestBytes)) > 0) {
            httpRequest = new String(httpRequestBytes, 0, length);
        }

        String httpHead = httpRequest.split("\n")[0];
        method = httpHead.split("\\s")[0];
        url = httpHead.split("\\s")[1];

        System.out.println("MyRequest : " + this.getClass().getName() + ", " + this);
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MyRequest{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                '}';
    }

    public String getMethod() {
        return method;
    }
}
