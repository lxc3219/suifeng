package org.suifeng.baseframework.demo.jasypt;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class JasyptTest {

    @Autowired
    StringEncryptor encryptor;

    @Test
    public void getPass() {
        String url = encryptor.encrypt("jdbc:mysql://localhost:3306/tzydb?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8");
        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("root");
        log.info(url);
        log.info(name);
        log.info(password);
        Assert.assertTrue(name.length() > 0);
        Assert.assertTrue(password.length() > 0);
    }
}
