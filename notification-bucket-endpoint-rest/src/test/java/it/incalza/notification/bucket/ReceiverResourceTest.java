package it.incalza.notification.bucket;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReceiverResourceTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .build();
    }

    @Test
    public void receiverSystemA() throws Exception {
        mockMvc.perform(put("/receiver/systemA")
                .contentType(APPLICATION_JSON)
                .content(jsonSystemANotification(10000)))
                .andDo(log())
                .andExpect(status().isOk());
    }

    @Test
    public void receiverSystemA_when_notification_exist_then_fail_with_400_status_code() throws Exception {
        mockMvc.perform(put("/receiver/systemA")
                .contentType(APPLICATION_JSON)
                .content(jsonSystemANotification(1)))
                .andDo(log())
                .andExpect(status().isOk());

        mockMvc.perform(put("/receiver/systemA")
                .contentType(APPLICATION_JSON)
                .content(jsonSystemANotification(1)))
                .andDo(log())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void receiverSystemB() throws Exception {
        mockMvc.perform(put("/receiver/systemB")
                .contentType(APPLICATION_JSON)
                .content(jsonSystemBNotification(10000)))
                .andDo(log())
                .andExpect(status().isOk());
    }

    private String jsonSystemBNotification(int n) {
        StringBuilder items = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            items.append(format("{\"userId\":\"email%s@email.com\", \"message\":\"Hello Word %s\", \"media\":\"http://media.org/media%s.jpg\", \"mediaType\":\"picture\"}", i, i, i));
            if (i != n) items.append(",\n");
        }
        return "[\n" + items + "\n]";
    }

    private String jsonSystemANotification(int n) {
        StringBuilder items = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            items.append(format("{\"userId\":\"email%s@email.com\", \"message\":\"Hello Word %s\"}", i, i));
            if (i != n) items.append(",\n");
        }
        return "[\n" + items + "\n]";
    }

}