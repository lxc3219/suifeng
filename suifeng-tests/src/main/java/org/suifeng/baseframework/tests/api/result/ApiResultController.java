package org.suifeng.baseframework.tests.api.result;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.suifeng.baseframework.api.common.helper.RestHelper;
import org.suifeng.baseframework.api.result.annotation.ResponseResult;
import org.suifeng.baseframework.model.vo.CommonResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回体包装测试
 * @author luoxc
 * @since 1.0.0
 */
@RequestMapping("/api/result")
@RestController
public class ApiResultController {

    @GetMapping("/test1")
    public Map test1() {
        Map map = new HashMap();
        map.put("name", "xiaoming");
        map.put("age", 18);
        return map;
    }

    @GetMapping("/test2")
    @ResponseResult
    public CommonResult test2() {
        return RestHelper.ok("成功");
    }

    @GetMapping(value = "/test3")
    public CommonResult test3() {
        return RestHelper.bizError(1, "啊，报错了");
    }

    @GetMapping(value = "/test4")
    public String test4() {
        return "只要998";
    }

    @GetMapping(value = "/test5")
    public CommonResult test5() {
        return RestHelper.ok("只要998");
    }

}
