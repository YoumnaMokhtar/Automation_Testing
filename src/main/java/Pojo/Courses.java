package Pojo;

import groovyjarjarantlr4.v4.analysis.AnalysisPipeline;

import java.util.List;

public class Courses {
    private List <webAutomation> webAutomation;
    private List<Api>  api;
    private  List<mobile> mobile;

    public List<webAutomation> getWebAutomation() {
        return webAutomation;
    }

    public List <Api> getApi() {
        return api;
    }

    public List <mobile >getMobile() {
        return mobile;
    }

    public void setWebAutomation(List <webAutomation> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public void setApi(List<Api> api) {
        this.api = api;
    }

    public void setMobile(List<mobile> mobile) {
        this.mobile = mobile;
    }
}
