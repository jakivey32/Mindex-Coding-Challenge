package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ReportingStructure read(String employeeID) {
        LOG.debug("Showing ReportStructure for employeeID: " + employeeID);

        Employee employee = employeeService.read(employeeID);
        int numberOfRecords = employeeService.getNumberOfRecords(employeeID);

        ReportingStructure result = new ReportingStructure(employee, numberOfRecords);
        return result;
    }
}
