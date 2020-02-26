package org.suifeng.baseframework.demo.pojo;

import org.suifeng.baseframework.model.dataobject.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDO extends BaseDO {

    private Long id;

    private String name;

    private Integer gender;

    private Integer age;

    private String address;
}
