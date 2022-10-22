package com.mytomcat;

public class ServletMapping {
    private String url;
    private String servletName;
    private String clazz;

    public ServletMapping(String url, String servletName, String clazz) {
        this.url = url;
        this.servletName = servletName;
        this.clazz = clazz;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
