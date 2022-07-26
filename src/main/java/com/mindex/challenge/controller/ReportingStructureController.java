package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingStructureController {

    @Autowired
    ReportingStructureService reportingStructureService;

    @GetMapping("/reportingStructure/{id}")
    ReportingStructure read(@PathVariable String id){
        return reportingStructureService.read(id);
    }
}
