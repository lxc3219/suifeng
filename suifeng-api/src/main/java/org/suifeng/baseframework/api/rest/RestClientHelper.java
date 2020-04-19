package org.suifeng.baseframework.api.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import org.suifeng.baseframework.api.common.domain.ResultCode;
import org.suifeng.baseframework.api.common.exception.BizException;
import org.suifeng.baseframework.api.common.exception.RestException;
import org.suifeng.baseframework.common.util.databind.JsonMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotBlank;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 使用 spring 的 restTemplate替代 httpClient 工具
 * 支持 json 返回格式
 * @author luoxc
 * @since 1.0.0
 */
@Slf4j
public class RestClientHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestProperties restProperties;

    public <T> T get(String url, TypeReference<T> typeReference)  {
        String serverAddr = restProperties.getServerAddr();
        try {
            log.debug("get请求地址：{}", serverAddr + url);
            String result = restTemplate.getForObject(serverAddr + url, String.class);
            log.debug("返回结果：{}", result);
            return parseJson(result, typeReference);
        } catch (org.springframework.web.client.ResourceAccessException e) {
            log.error("接口服务器无法连接", e);
            throw new BizException("接口服务器无法连接");
        } catch (RestException ex) {
            log.error("调用get接口失败" + serverAddr + url + ",错误代码：" + ex.getStatus(), ex);
            ResultCode resultCode = parseJson(ex.getMessage(), ResultCode.class);
            throw new BizException(resultCode);
        } catch (Exception ex) {
            log.error("调用get接口失败" + serverAddr + url, ex);
            throw new BizException(ex.getMessage());
        }
    }

    public <T> T get(String url, Class<T> responseType) {
        String serverAddr = restProperties.getServerAddr();
        try {
            log.debug("get请求地址：{}", serverAddr + url);
            String result = restTemplate.getForObject(serverAddr + url, String.class);
            log.debug("返回结果：{}", result);
            return parseJson(result, responseType);
        } catch (org.springframework.web.client.ResourceAccessException e) {
            log.error("接口服务器无法连接", e);
            throw new BizException("接口服务器无法连接");
        } catch (RestException ex) {
            log.error("调用get接口失败" + serverAddr + url + ",错误代码：" + ex.getStatus(), ex);
            ResultCode resultCode = parseJson(ex.getMessage(), ResultCode.class);
            throw new BizException(resultCode);
        } catch (Exception ex) {
            log.error("调用get接口失败" + serverAddr + url, ex);
            throw new BizException(ex.getMessage());
        }
    }

    public void post(String url, Object request) {
        post(url, request, Void.class);
    }

    public <T> T post(String url, Object request, Class<T> responseType) {
        String serverAddr = restProperties.getServerAddr();
        try {
            String result = restTemplate.postForObject(serverAddr + url, request, String.class);
            log.debug("返回结果:{}", result);
            return parseJson(result, responseType);
        } catch (org.springframework.web.client.ResourceAccessException e) {
            log.error("接口服务器无法连接", e);
            throw new BizException("接口服务器无法连接");
        } catch (RestException ex) {
            log.error("调用post接口失败" + serverAddr + url + ",错误代码：" + ex.getStatus(), ex);
            ResultCode resultCode = parseJson(ex.getMessage(), ResultCode.class);
            throw new BizException(resultCode);
        } catch (Exception ex) {
            log.error("调用post接口失败:" + serverAddr + url, ex);
            throw new BizException(ex.getMessage());
        }
    }

    public <T> T post(String url, Object request, TypeReference<T> typeReference) {
        String serverAddr = restProperties.getServerAddr();
        log.debug("post请求地址：{}，请求参数：{}", serverAddr + url, request);
        try {
            String result = restTemplate.postForObject(serverAddr + url, request, String.class);
            log.debug("返回结果：{}", result);
            return parseJson(result, typeReference);
        } catch (org.springframework.web.client.ResourceAccessException e) {
            log.error("接口服务器无法连接", e);
            throw new BizException("接口服务器无法连接：" + serverAddr + url);
        } catch (RestException ex) {
            log.error("调用post接口失败" + serverAddr + url + ",错误代码：" + ex.getStatus(), ex);
            ResultCode resultCode = parseJson(ex.getMessage(), ResultCode.class);
            throw new BizException(resultCode);
        } catch (Exception ex) {
            log.error("调用post接口失败" + serverAddr + url, ex);
            throw new BizException(ex.getMessage());
        }
    }

    public <T> T post(String url, Object request, TypeReference<T> typeReference, Object... objects) {
        String serverAddr = restProperties.getServerAddr();
        try {
            log.debug("post请求地址：{}，请求参数：{}", serverAddr + url, request);
            String result = restTemplate.postForObject(serverAddr + url, request, String.class, objects);
            log.debug("返回结果：{}", result);
            return parseJson(result, typeReference);
        } catch (org.springframework.web.client.ResourceAccessException e) {
            log.error("接口服务器无法连接", e);
            throw new BizException("接口服务器无法连接");
        } catch (RestException ex) {
            log.error("调用post接口失败" + serverAddr + url + ",错误代码：" + ex.getStatus(), ex);
            ResultCode resultCode = parseJson(ex.getMessage(), ResultCode.class);
            throw new BizException(resultCode);
        } catch (Exception ex) {
            log.error("调用post接口失败" + serverAddr + url, ex);
            throw new BizException(ex.getMessage());
        }
    }

    public void put(String url, Object request) {
        put(url, request, Void.class);
    }

    public <T> T put(String url, Object request, Class<T> responseType) {
        String serverAddr = restProperties.getServerAddr();
        log.debug("put请求地址：{}，请求参数：{}", serverAddr + url, request);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<Object> entity = new HttpEntity<Object>(request, headers);
            ResponseEntity<String> response = restTemplate.exchange(serverAddr + url, HttpMethod.PUT, entity, String.class);
            String result = response.getBody();
            log.debug("返回结果：{}", result);
            return parseJson(result, responseType);
        } catch (org.springframework.web.client.ResourceAccessException e) {
            log.error("接口服务器无法连接", e);
            throw new BizException("接口服务器无法连接");
        } catch (RestException ex) {
            log.error("调用put接口失败" + serverAddr + url + ",错误代码：" + ex.getStatus(), ex);
            ResultCode resultCode = parseJson(ex.getMessage(), ResultCode.class);
            throw new BizException(resultCode);
        } catch (Exception ex) {
            log.error("调用put接口失败" + serverAddr + url, ex);
            throw new BizException(ex.getMessage());
        }
    }

    /**
     * 调用删除的rest接口
     */
    public void delete(String url) {
        delete(url, Void.class);
    }

    /**
     * 调用删除的rest接口
     * @param url          地址
     * @param responseType 返回类型
     * @return 结果
     */
    public <T> T delete(String url, Class<T> responseType) {
        String serverAddr = restProperties.getServerAddr();
        log.debug("DELETE请求地址：{}", serverAddr + url);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<Object> entity = new HttpEntity<Object>("", headers);
            ResponseEntity<String> response = restTemplate.exchange(serverAddr + url, HttpMethod.DELETE, entity, String.class);
            String result = response.getBody();
            log.debug("返回结果：{}", result);
            return parseJson(result, responseType);
        } catch (org.springframework.web.client.ResourceAccessException e) {
            log.error("接口服务器无法连接", e);
            throw new BizException("接口服务器无法连接");
        } catch (RestException ex) {
            log.error("调用delete接口失败" + serverAddr + url + ",错误代码：" + ex.getStatus(), ex);
            ResultCode resultCode = parseJson(ex.getMessage(), ResultCode.class);
            throw new BizException(resultCode);
        } catch (Exception ex) {
            log.error("调用DELETE接口失败" + serverAddr + url, ex);
            throw new BizException(ex.getMessage());
        }
    }

    /**
     * 反序列化
     * @param result
     * @param responseType
     * @param <T>
     * @return
     */
    private <T> T parseJson(String result, Class<T> responseType) {
        if (responseType == String.class) {
            return responseType.cast(result);
        }
        if (responseType == Void.class) {
            return null;
        }
        JsonMapperUtils mapper = new JsonMapperUtils();
        T res = (T) mapper.fromJson(result, responseType);
        return res;
    }

    /**
     * 反序列化
     * @param result
     * @param typeReference
     * @param <T>
     * @return
     */
    private <T> T parseJson(String result, TypeReference<?> typeReference) {
        Type type = typeReference.getType();
        if (type instanceof Class<?>) {
            Class<?> clz = (Class<?>) type;
            if (clz == String.class) {
                return (T) result;
            }
        }
        JsonMapperUtils mapper = new JsonMapperUtils();
        T res = (T) mapper.fromJson(result, typeReference);
        return res;
    }

    /**
     * 请求中若带MultipartFile，需将请求参数转换成MultiValueMap<String, Object>
     * @param key, val
     * @return
     * @author luoxc
     * @since 1.0.0
     */
    public static MultiValueMap<String, Object> convert(@NotBlank String key, Object val) {
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add(key, val);
        return multiValueMap;
    }

    public static void convert(MultiValueMap<String, Object> multiValueMap, @NotBlank String key, Object val) {
        if (multiValueMap == null) {
            multiValueMap = new LinkedMultiValueMap<>();
        }
        multiValueMap.add(key, val);
    }

    /**
     * 请求中若带MultipartFile，需将请求参数转换成MultiValueMap<String, Object>
     * @param variables
     * @return
     * @author luoxc
     * @since 1.0.0
     */
    public static MultiValueMap<String, Object> convert(Map<String, Object> variables) {
        if (variables == null) {
            return new LinkedMultiValueMap<>();
        }
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            if (entry != null) {
                String key = entry.getKey();
                if (key != null) {
                    multiValueMap.add(key, entry.getValue());
                }
            }
        }
        return multiValueMap;
    }

    /**
     * multipartfile文件要经过转换，才能使用restTemplate操作
     * @param multiValueMap, key, vals
     * @return
     * @author luoxc
     * @since 1.0.0
     */
    public static void multipartFileConvert(MultiValueMap<String,Object> multiValueMap, @NotBlank String key, MultipartFile val) {
        if (val != null) {
            if (multiValueMap == null) {
                multiValueMap = new LinkedMultiValueMap<>();
            }
            List<Resource> tmpResource = new ArrayList<>();
            tmpResource.add(val.getResource());
            multiValueMap.addAll(key, tmpResource);
        }
    }

    public static void multipartFileConvert(MultiValueMap<String,Object> multiValueMap, @NotBlank String key, MultipartFile[] vals) {
        if (vals != null && vals.length > 0) {
            if (multiValueMap == null) {
                multiValueMap = new LinkedMultiValueMap<>();
            }
            List<Resource> tmpResource = new ArrayList<>();
            for (MultipartFile val : vals) {
                if (val != null) {
                    tmpResource.add(val.getResource());
                }
            }
            multiValueMap.addAll(key, tmpResource);
        }
    }

    public static MultiValueMap<String, Object> multipartFileConvert(@NotBlank String key, MultipartFile val) {
        if (val == null) {
            return null;
        }
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multipartFileConvert(multiValueMap, key, val);
        return multiValueMap;
    }

    public static MultiValueMap<String, Object> multipartFileConvert(@NotBlank String key, MultipartFile[] vals) {
        if (vals == null || vals.length == 0) {
            return null;
        }
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multipartFileConvert(multiValueMap, key, vals);
        return multiValueMap;
    }
}