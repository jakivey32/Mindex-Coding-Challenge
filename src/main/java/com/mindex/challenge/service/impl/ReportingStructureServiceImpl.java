package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ReportingStructure read(String employeeID) {
        Employee employee = employeeService.read(employeeID);
        int numberOfRecords = employeeService.getNumberOfRecords(employeeID);

        ReportingStructure result = new ReportingStructure(employee, numberOfRecords);
        return result;
    }
}
