# api/result
> 统一封装结果集
```yaml
suifeng:
    api:
      result:
        enabled: true  # 可不显式设置，默认开启
        ann: false  # 默认为 false，即不开启注解；true，配合 @ResponseResult 或 @RestResponseResult 开启注解
```