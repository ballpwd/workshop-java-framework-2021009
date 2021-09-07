package com.example.demoapp.employees;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerServiceTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean EmployeeRepository employeeRepository ;

    @Test
    @DisplayName("Success case")
    public void case01(){
        int id = 1 ;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("Mock name");
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee)) ;

        EmployeeResponse result = restTemplate.getForObject("/employees/"+ id, EmployeeResponse.class);

        assertEquals(id,result.getId());
        assertEquals("Mock name",result.getName());
    }

    @Test
    @DisplayName("Failure case  :: Employee not found id = 100")
    public void case02(){
        int id = 100 ;

        when(employeeRepository.findById(id)).thenThrow(new EmployeeNotFoundException("Employee not found id=" + id)) ;

        ErrorResponse result = restTemplate.getForObject("/employees/"+id, ErrorResponse.class);

        assertEquals(404,result.getCode()) ;
        assertEquals("Employee not found id="+id, result.getDetail());


    }
}
