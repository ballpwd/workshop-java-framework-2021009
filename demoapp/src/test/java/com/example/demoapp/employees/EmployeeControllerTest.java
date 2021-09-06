package com.example.demoapp.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate ;

    @Test
    void listEmployee() {

        EmployeeResponse[] results = restTemplate.getForObject("/employees", EmployeeResponse[].class);

        assertEquals(2, results.length);
        assertEquals(1,results[0].getId());
        assertEquals("puwadech 2", results[0].getName());
    }

}