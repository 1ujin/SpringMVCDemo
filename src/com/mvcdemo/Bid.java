package com.mvcdemo;

// MVC 中的 Model
public class Bid {
    private Integer no;
    private String title;
    private String url;
    private String date;
    private String source;
    private String[] checks;

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getNo() {
        return no;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setChecks(String[] checks) {
        this.checks = checks;
    }

    public String[] getChecks() {
        return checks;
    }
}
