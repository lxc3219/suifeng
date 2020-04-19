package org.suifeng.baseframework.tests.api.result;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 返回体统一包装测试
 * @author luoxc
 * @since 1.0.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiResultTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void test1() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/api/result/test1"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        log.info(resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void test2() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/api/result/test2"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        log.info(resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void test3() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/api/result/test3"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        log.info(resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void test4() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/api/result/test4"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        log.info(resultActions.andReturn().getResponse().getContentAsString());
    }
}
