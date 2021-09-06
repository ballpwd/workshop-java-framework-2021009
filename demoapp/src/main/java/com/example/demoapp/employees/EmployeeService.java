package com.example.demoapp.employees;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    public EmployeeResponse getById(int id){

        EmployeeResponse response = new EmployeeResponse();
        response.setId(id);
        response.setName("puwadech");
        return response ;

    }

}
