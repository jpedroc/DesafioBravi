package com.example.apitask.util;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.example.apitask.TaskAPIApplication;

@SpringBootTest(classes = {TaskAPIApplication.class})
@WebAppConfiguration
@TestConfiguration
public class BaseIntTest {

    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            /*.apply(SecurityMockMvcConfigurers.springSecurity())*/.build();
    }

    @Autowired
    public void setWebApplicationContext(WebApplicationContext pWebApplicationContext) {
        webApplicationContext = pWebApplicationContext;
    }

    protected MockMvc getMockMvc() {
        return mockMvc;
    }

}
