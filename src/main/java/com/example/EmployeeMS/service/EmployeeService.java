package com.example.EmployeeMS.service;

import com.example.EmployeeMS.dto.EmployeeDto;
import com.example.EmployeeMS.entity.Employee;
import com.example.EmployeeMS.repo.EmployeeRepo;
import com.example.EmployeeMS.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveEmployee(EmployeeDto dto){
        if (employeeRepo.existsById(dto.getEmpId())){
            return VarList.RSP_DUPLICATED;
        }else {
            employeeRepo.save(modelMapper.map(dto, Employee.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateEmployee(EmployeeDto dto) {
        if (employeeRepo.existsById(dto.getEmpId())) {
            employeeRepo.save(modelMapper.map(dto, Employee.class));
             return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NOT_DATA_FOUND;
        }
    }

    public List<EmployeeDto> getAllEmployee(){
        List<Employee> all = employeeRepo.findAll();
        return modelMapper.map(all, new TypeToken<ArrayList<EmployeeDto>>(){}.getType());
    }

    public EmployeeDto searchEmployee(int id){
        if (employeeRepo.existsById(id)){
            Employee employee = employeeRepo.findById(id).orElse(null);
            return modelMapper.map(employee, EmployeeDto.class);
        }else{
            return null;
        }
    }

    public String deleteEmployee(int id){
        if (employeeRepo.existsById(id)){
            employeeRepo.deleteById(id);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NOT_DATA_FOUND;
        }
    }
}
