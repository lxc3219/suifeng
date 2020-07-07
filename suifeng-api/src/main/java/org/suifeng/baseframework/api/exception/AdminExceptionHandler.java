package org.suifeng.baseframework.api.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.*;
import org.suifeng.baseframework.model.vo.CommonResult;
import org.suifeng.baseframework.api.common.enums.ResultEnum;
import org.suifeng.baseframework.api.common.exception.BizException;
import org.suifeng.baseframework.api.common.helper.RestHelper;
import org.suifeng.baseframework.common.helper.ParamHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Map;

/**
 * 全局异常捕获
 * @author luoxc
 * @since 1.0.0
 */
@Slf4j
@ControllerAdvice
public class AdminExceptionHandler {

    private static final String STATUS = "status";

    private static final String MSG = "msg";

    @Autowired
    private ErrorViewProperties errorViewProperties;

    @Autowired
    private ExceptionProperties exceptionProperties;

    /**
     * 业务异常
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public CommonResult bizError(BizException e) {
        log.error("业务异常：", e);
        return RestHelper.bizError(e.getCode(), e.getMessage());
    }

    /**
     * 无权访问资源异常
     */
    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handleForbidden(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Exception e) {
        log.info("无权访问资源异常：", e);
        if (RestHelper.isAjax(request)) {
            // FIXME 待完善
            return handleWithoutView(request, response, Maps.newHashMap());
        }
        return toErrorView(request, HttpStatus.FORBIDDEN.value(), "用户无此权限");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonResult unknowError(RuntimeException e) {
        log.error("运行时异常:", e);
        return RestHelper.error(ResultEnum.SERVICE_EXCEPTION.getCode(), ResultEnum.SERVICE_EXCEPTION.getMessage());
    }

    /**
     * 系统错误
     * @param request, response, e
     * @return
     * @author luoxc
     * @since 1.0.0
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handle(HttpServletRequest request,
                               HttpServletResponse response, Exception e) {
        log.error("500错误", e);
        if (RestHelper.isAjax(request)) {
            // FIXME 待完善
            return handleWithoutView(request, response, Maps.newHashMap());
        }
        return toErrorView(request, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    /**
     * 返回json
     * @param request, response, result
     * @return
     * @author luoxc
     * @since 1.0.0
     */
    private ModelAndView handleWithoutView(HttpServletRequest request,
                                           HttpServletResponse response,
                                           Map<String, Object> result) {
        try {
            String jsonStr = JSON.toJSONString(result).replaceAll("null", "\"\"");
            response.setContentType("text/xml;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(jsonStr);
            out.flush();
            out.close();
        } catch (JSONException e) {
            log.error("序列化异常", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 跳转到error页面
     * @param request, status, msg
     * @return
     * @author luoxc
     * @since 1.0.0
     */
    private ModelAndView toErrorView(HttpServletRequest request, Integer status, String msg) {
        Map<String, Object> result = ParamHelper.getParam(request);
        result.put(STATUS, status);
        result.put(MSG, msg);
        String errorView = this.errorViewProperties.getError();
        return handleWithView(result, errorView);
    }

    /**
     * 返回页面
     * @param result, viewName
     * @return
     * @author luoxc
     * @since 1.0.0
     */
    private ModelAndView handleWithView(Map<String, Object> result, String viewName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(viewName);
        modelAndView.addAllObjects(result);
        return modelAndView;
    }

}
