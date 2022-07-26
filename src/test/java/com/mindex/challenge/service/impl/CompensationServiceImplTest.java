package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String createCompensationUrl;
    private String readCompensationUrl;
    private String employeeUrl;

    @Autowired
    private CompensationService compensationService;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup(){
        createCompensationUrl = "http://localhost:" + port + "/compensation";
        readCompensationUrl = "http://localhost:" + port + "/compensation/{id}";
        employeeUrl = "http://localhost:" + port + "/employee";
    }

    @Test
    public void testCreateReadUpdate(){
        Employee testEmployee = employeeService.read("c0c2293d-16bd-4603-8e08-638a9d18b22c");

        Compensation testComp = new Compensation(testEmployee, 130000, LocalDate.now());

        // Create Checks
        Compensation createdCompensation = restTemplate.postForEntity(createCompensationUrl, testComp, Compensation.class).getBody();
        assertNotNull(createdCompensation);
        assertCompensationEquivalence(createdCompensation, testComp);

        // Read checks
        Compensation readCompensation = restTemplate.getForEntity(readCompensationUrl, Compensation.class, createdCompensation.getEmployee().getEmployeeId()).getBody();
        assertNotNull(readCompensation);
        assertCompensationEquivalence(readCompensation, createdCompensation);
    }

    private static void assertCompensationEquivalence(Compensation expectedCompensation, Compensation actualCompensation){
        Employee expectedEmployee = expectedCompensation.getEmployee();
        Employee actualEmployee = actualCompensation.getEmployee();

        assertEquals(expectedEmployee.getFirstName(), actualEmployee.getFirstName());
        assertEquals(expectedEmployee.getLastName(), actualEmployee.getLastName());
        assertEquals(expectedEmployee.getDepartment(), actualEmployee.getDepartment());
        assertEquals(expectedEmployee.getPosition(), actualEmployee.getPosition());

        assertEquals(expectedCompensation.getSalary(), actualCompensation.getSalary());
        assertEquals(expectedCompensation.getEffectiveDate(), actualCompensation.getEffectiveDate());
    }
}
