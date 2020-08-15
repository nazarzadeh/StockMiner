package com.stockminer.stockminer;

import com.stockminer.stockminer.controller.MinerController;
import com.stockminer.stockminer.controller.RuleHolder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        MinerController controller = new MinerController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void addEmployee() throws Exception {
    String json = "{\n" +
                "  \"rslist\" : [ {\n" +
                "    \"@class\" : \"RsiRuleScript\",\n" +
                "    \"barCount\" : 14,\n" +
                "    \"ohlc\" : \"CLOSE\"\n" +
                "  }, {\n" +
                "    \"@class\" : \"RsiRuleScript\",\n" +
                "    \"barCount\" : 28,\n" +
                "    \"ohlc\" : \"HIGH\"\n" +
                "  }, {\n" +
                "    \"@class\" : \"CrossedValueRuleScript\",\n" +
                "    \"indicatorName\" : \"Amir\"\n" +
                "  } ]\n" +
                "}";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/rule")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}