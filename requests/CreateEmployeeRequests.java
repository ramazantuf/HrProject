package com.project.hr.requests;

import lombok.Data;

@Data
public class CreateEmployeeRequests {

    Long id;
    String userName;
    String password;
    Long adminId;
}
