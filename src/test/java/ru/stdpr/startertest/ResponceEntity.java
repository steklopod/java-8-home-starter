package ru.stdpr.startertest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.stdpr.startertest.controller_ex.MyWebConfig;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@RunWith(JUnitPlatform.class)
@ContextConfiguration(classes = MyWebConfig.class)
public class ResponceEntity {
    private static Logger logger = LoggerFactory.getLogger(ResponceEntity.class);

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    @Test
    void testUserController() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/test")
                        .header("testHeader",
                                "headerValue")
                        .content("test body");
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testUserController2() throws Exception {

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/user")
                        .header("testHeader",
                                "headerValue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createUserInJson("joe",
                                "joe@example.com"));
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String createUserInJson(String name, String email) {
        return "{ \"name\": \"" + name + "\", " +
                "\"emailAddress\":\"" + email + "\"}";
    }
}
