> 此处有个问题，当返回值是 String 时，会使用 StringHttpMessageConverter 解析器，导致报错。 

```java
@GetMapping(value = "/test4")
public String test4() {
    return "只要998";
}
```

> 临时使用 `CommonResult` 包装解决

```java
@GetMapping(value = "/test4")
public CommonResult test4() {
    return RestHelper.ok("只要998");
}
```