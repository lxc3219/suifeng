package org.suifeng.baseframework.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.suifeng.baseframework.api.common.domain.ResultCode;
import org.suifeng.baseframework.api.common.domain.ValidationErrorDTO;
import org.suifeng.baseframework.api.common.exception.BizException;
import org.suifeng.baseframework.api.common.exception.RestException;
import org.suifeng.baseframework.api.common.helper.RestHelper;
import org.suifeng.baseframework.api.constant.MediaTypes;
import org.suifeng.baseframework.model.vo.CommonResult;

import java.util.List;

/**
 * Restful接口异常统一处理
 * @author luoxc
 * @since 1.0.0
 */
@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

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
     * 处理RestException.
     * 主要返回客户端异常信息
     */
    @ExceptionHandler(value = { RestException.class })
    public final ResponseEntity<?> handleException(RestException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
        // TODO 继续优化
        ResultCode rc = new ResultCode(ex.getCode(), ex.getMessage());
        return handleExceptionInternal(ex, rc, headers, ex.status, request);
    }





//	@ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    public ResponseEntity<ResMessage> processValidationError(MethodArgumentNotValidException ex) {
//        BindingResult result = ex.getBindingResult();
//        List<FieldError> fieldErrors = result.getFieldErrors();
//        ValidationErrorDTO errorDTO = processFieldErrors(fieldErrors);
//        ResMessage resMessage = new ResMessage<>(ErrorType.PARAM_ERROR, errorDTO);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
//        log.info("RestException:{}",resMessage);
//        return new ResponseEntity<>(resMessage,headers,HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
//    public final ResponseEntity<?> handleException(HttpMessageNotReadableException ex, WebRequest request) {
//        //Map<String, String> errors = BeanValidators.extractPropertyAndMessage(ex.getConstraintViolations());
//        String body = "请求参数错误";
//        log.warn(body,ex);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
//        ResMessage resMessage = new ResMessage<>(ErrorType.PARAM_ERROR);
//        log.info("RestException:{}",resMessage);
//        return new ResponseEntity<>(resMessage,headers,HttpStatus.BAD_REQUEST);
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
////        return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
//    }

//    @ExceptionHandler(value = { HttpMessageNotWritableException.class })
//    public final ResponseEntity<?> handleException(HttpMessageNotWritableException ex, WebRequest request) {
//        //Map<String, String> errors = BeanValidators.extractPropertyAndMessage(ex.getConstraintViolations());
//        String body = "返回结果错误";
//        log.warn(body,ex);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
//        ResMessage resMessage = new ResMessage<>(ErrorType.PARAM_ERROR);
//        log.info("RestException:{}",resMessage);
//        return new ResponseEntity<>(resMessage,headers,HttpStatus.BAD_REQUEST);
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
////        return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
//    }

//    @ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
//    public final ResponseEntity<?> handleException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
//        //Map<String, String> errors = BeanValidators.extractPropertyAndMessage(ex.getConstraintViolations());
//        String body = "接口不支持方法："+request.getMethod();
//        log.warn(body,ex);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
//        ResMessage resMessage = new ResMessage<>(ErrorType.PARAM_ERROR);
//        log.info("RestException:{}",resMessage);
//        return new ResponseEntity<>(resMessage,headers,HttpStatus.BAD_REQUEST);
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
////        return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
//    }


    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, WebRequest.SCOPE_REQUEST);
        }
        // 可将附加信息放入Response.header中，简化Response.body的数据结构
        // TODO 待完善
        headers.add("ex", body.toString());
        log.error("RestException:{}", body);
        return new ResponseEntity<Object>(body, headers, status);
    }

//	@ExceptionHandler(Exception.class)
//    @ResponseBody
//    public ResponseEntity<ResMessage> processOtherException(Exception ex) {
//        ResMessage resMessage = new ResMessage<>(ErrorType.SERVER_ERROR);
//        log.error(ErrorType.SERVER_ERROR.getName(),ex);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
//        return new ResponseEntity<>(resMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDTO dto = new ValidationErrorDTO();

        for (FieldError fieldError: fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }

        return dto;
    }

	private String resolveLocalizedErrorMessage(FieldError fieldError) {
		return fieldError.getDefaultMessage();
	}


}
