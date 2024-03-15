package com.example.mongatest;

import com.example.mongatest.controller.Controller;
import com.example.mongatest.model.Client;
import com.example.mongatest.service.MyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ClientControllerMockTest {
    private MockMvc mvc;
    @MockBean
    private MyService srvc;
    private List<Client> getAllMock() {
        List<Client> mockList = new ArrayList<>();
        for (int i = 0; i<5;i++){
            Client client = new Client(String.valueOf(i),"2", "3", "4");
            mockList.add(client);
        }
        return mockList;
    }
    @BeforeEach
    void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(new Controller(srvc)).build();
    }
    @Test
    public void createTest() throws Exception{
        Client client = new Client("1", "2", "3", "4");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = writer.writeValueAsString(client);
        mvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isCreated());
    }
    @Test
    public void getAllTest() throws Exception{
        Mockito.when(this.srvc.findAll()).thenReturn(getAllMock());
        mvc.perform(get("/clients")).andExpectAll(status().isOk());
    }
    @Test
    public void getTest() throws Exception{
        String id = "1";
        String name = "2";
        String email = "3";
        String phone = "4";
        Optional<Client> client = Optional.of(new Client(id, name, email, phone));
        Mockito.when(this.srvc.find(id)).thenReturn(client);
        mvc.perform(get("/clients/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.phone").value(phone));
    }
}
