package org.suifeng.baseframework.demo.rest;

import org.suifeng.baseframework.api.common.helper.RestClientHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/demo")
public class RestClientController {

    @Autowired
    private RestClientHelper restClient;

    @Autowired
    @Qualifier("pt1")
    private RestClientHelper restClient1;

    @Autowired
    @Qualifier("pt2")
    private RestClientHelper restClient2;

    @GetMapping("/hi")
    public String hi() {
        return "Hello,I am pt2";
    }

    @GetMapping("/test")
    public void test() {
        //String msg = this.restClient.get("/demo/hi", String.class);
        //log.info(msg);
        String msg1 = this.restClient1.get("/api/demo/hi", String.class);
        log.info(msg1);
        String msg2 = this.restClient2.get("/demo/hi", String.class);
        log.info(msg2);
    }
}
