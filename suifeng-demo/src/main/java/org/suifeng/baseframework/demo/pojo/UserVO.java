package org.suifeng.baseframework.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    private String id;

    private String name;

    private String gender;

    private String age;

    private String address;
}
