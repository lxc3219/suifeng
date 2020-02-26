package org.suifeng.baseframework.demo.hibernatevalid;


import org.suifeng.baseframework.demo.pojo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/hibernatevalid")
@RestController
public class HibernateValidController {

    @PostMapping("/insert")
    public void insert(@RequestBody @Valid UserVO userVO) {
        log.info("hibernate valid...");
    }

    @GetMapping("/get")
    public void get(@RequestParam(required = false, value = "name") String name) {
        log.info("hibernate valid...");
    }
}
