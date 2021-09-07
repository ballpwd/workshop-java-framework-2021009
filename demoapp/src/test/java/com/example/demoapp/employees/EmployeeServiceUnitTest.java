package com.example.demoapp.employees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceUnitTest {

    @Mock
    private EmployeeRepository employeeRepository ;

    @Test
    public void success_case(){

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Mock name");
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee)) ;


        EmployeeService employeeService = new EmployeeService(employeeRepository);

        EmployeeResponse result = employeeService.getById(1);

        assertEquals(1,result.getId());
        assertEquals("Mock name",result.getName());
    }

    @Test
    public void employee_not_found_case() {
        // Arrange
        when(employeeRepository.findById(1)).thenReturn(Optional.empty());
        // Act
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        try {
            employeeService.getById(1);
            fail();
        } catch (EmployeeNotFoundException e) {
            // Pass
            if(!"Employee not found id=1".equals(e.getMessage())) {
                fail("Message fail with=" + e.getMessage());
            }
        }
    }

    @Test
    public void employee_not_found_case_with_junit5() {
        int id = 100 ;

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        EmployeeService employeeService = new EmployeeService(employeeRepository);

        Exception exception = assertThrows(EmployeeNotFoundException.class ,() -> {
            EmployeeResponse result = employeeService.getById(id);
        });

        assertEquals("Employee not found id="+id,exception.getMessage());
    }

}
