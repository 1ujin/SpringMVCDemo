package com.mvcdemo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("/web")
@ContextConfiguration(locations = {"classpath*:*.xml"})
public class VideoControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getMostViewed() {
        VideoController videoController = new VideoController();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/mostViewed");
        request.setAttribute("id", 1);
        MockHttpServletResponse response = new MockHttpServletResponse();

        videoController.getMostViewed(request, response);
        assertEquals(200, response.getStatus());
        assertEquals(100L, (int) request.getAttribute("viewed"));
    }

    @Test
    public void testGetMostViewedWithNoId() {
        VideoController videoController = new VideoController();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/mostViewed");
        MockHttpServletResponse response = new MockHttpServletResponse();

        videoController.getMostViewed(request, response);
        assertEquals(500, response.getStatus());
        assertNull(request.getAttribute("viewed"));
    }

    @Test
    public void getMostViewedTest() throws Exception {
        System.out.println(
                // 这里的状态码是404?
                mockMvc.perform(post("/mostViewed"))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getStatus()
        );
    }
}