package com.example.demoapp.employees;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private EmployeeRepository employeeRepository;

//    @BeforeEach
//    public void initialDataForTest(){
//        Employee employee100 = new Employee();
//        employee100.setName("puwadech");
//        employeeRepository.save(employee100);
//    }

    @AfterEach
    public void deleteDataForTest(){
        employeeRepository.deleteAll();
    }

    @Test
    public void getEmployeeById() {

        int id = 1;
        Employee employee100 = new Employee();
        employee100.setName("puwadech");
        employeeRepository.save(employee100);

        EmployeeResponse result = restTemplate.getForObject("/employees/" + id, EmployeeResponse.class);

        assertEquals(id, result.getId());
        assertEquals("puwadech", result.getName());
    }

    @Test
    public void listEmployee() {
        // Act
        EmployeeResponse[] results
                = restTemplate.getForObject("/employees", EmployeeResponse[].class);
        // Assert
        assertEquals(2, results.length);
        assertEquals(1, results[0].getId());
        assertEquals("puwadech", results[0].getName());
    }
}