package org.suifeng.baseframework.demo.responseresult;

import org.suifeng.baseframework.api.result.annotation.ResponseResult;
import org.suifeng.baseframework.model.vo.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.suifeng.baseframework.api.common.helper.RestHelper.ok;

@RequestMapping("/response/result")
@Controller
public class ResponseResultController {

    @GetMapping("/str1")
    @ResponseBody
    public String responseStr1() {
        return "ok";
    }

    @GetMapping(value="/str2")
    @ResponseBody
    public CommonResult responseStr2() {
        return ok("ok");
    }

    @GetMapping(value="/str3")
    @ResponseResult
    public String responseStr3() {
        return "ok";
    }

}
