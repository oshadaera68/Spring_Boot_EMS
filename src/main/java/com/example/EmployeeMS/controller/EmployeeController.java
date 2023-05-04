package com.example.EmployeeMS.controller;

import com.example.EmployeeMS.dto.EmployeeDto;
import com.example.EmployeeMS.dto.ResponseDto;
import com.example.EmployeeMS.service.EmployeeService;
import com.example.EmployeeMS.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ResponseDto responseDto;

    @PostMapping(value = "/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDto dto) {
        try {
            String response = employeeService.saveEmployee(dto);
            if (response.equals("00")) {
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(dto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            } else if (response.equals("06")) {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Employee Registered");
                responseDto.setContent(dto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            } else {
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("Error !");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDto dto) {
        try {
            String response = employeeService.updateEmployee(dto);
            if (response.equals("00")) {
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(dto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            } else if (response.equals("01")) {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Not a Registered Employee");
                responseDto.setContent(dto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            } else {
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("Error !");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity getAllEmployees() {
        try {
            List<EmployeeDto> allEmployee = employeeService.getAllEmployee();
            responseDto.setCode(VarList.RSP_SUCCESS);
            responseDto.setMessage("Success");
            responseDto.setContent(allEmployee);
            return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchEmployee/{empId}")
    public ResponseEntity searchEmployee(@PathVariable int empId) {
        try{
            EmployeeDto employeeDto = employeeService.searchEmployee(empId);
            if (employeeDto!=null){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else{
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("No Employee");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/searchEmployee/{empId}")
    public ResponseEntity deleteEmployee(@PathVariable int empId) {
        try{
            String res = employeeService.deleteEmployee(empId);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else{
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("No Employee Available for this id");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
