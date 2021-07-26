package com.avizhen.controller;

import com.avizhen.dto.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/active")
public class ActiveMqRestController {
    private JmsTemplate jmsTemplate;

    public ActiveMqRestController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping
    public ResponseEntity<String> createActiveEmployee(@RequestBody  EmployeeDto employeeDto) {

        try {
            jmsTemplate.convertAndSend("test", employeeDto);

            return new ResponseEntity<>("Active Employee was created", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
