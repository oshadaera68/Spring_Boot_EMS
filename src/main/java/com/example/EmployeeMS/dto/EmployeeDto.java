package com.example.EmployeeMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto {
    private int empId;
    private String empName;
    private String empAddress;
    private String empNumber;
}
