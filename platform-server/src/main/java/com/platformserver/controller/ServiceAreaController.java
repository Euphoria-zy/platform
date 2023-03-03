package com.platformserver.controller;

import com.platformserver.service.impl.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/platform")
public class ServiceAreaController {

    @Autowired
    private PeopleService peopleService;


}