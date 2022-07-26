package com.mindex.challenge.service.impl;

import com.mindex.challenge.controller.CompensationController;
import com.mindex.challenge.controller.EmployeeController;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    CompensationRepository compensationRepository;
    @Autowired
    EmployeeService employeeService;


    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating Compensation: [{}]", compensation);
        // This way checks to make sure employee is present in Employee table
        Employee employee = employeeService.read(compensation.getEmployee().getEmployeeId());
        //Fills in any missing data from employee
        compensation.setEmployee(employee);

        compensation.setEffectiveDate(LocalDate.now());

        compensationRepository.insert(compensation);

        return compensation;
    }

    @Override
    public Compensation read(String employeeId) {
        LOG.debug("Showing data for Compensation of employee id [{}]", employeeId);
        Compensation compensation = compensationRepository.findByEmployeeEmployeeId(employeeId);

        if (compensation == null) {
            throw new RuntimeException("Invalid Compensation: " + employeeId);
        }

        return compensation;
    }
}
