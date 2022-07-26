package com.mindex.challenge.data;

public class ReportingStructure {
    private Employee employee;
    private int numberOfRecords;

    public ReportingStructure(Employee employee, int numberOfRecords) {
        this.employee = employee;
        this.numberOfRecords = numberOfRecords;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    public void setNumberOfRecords(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }
}
