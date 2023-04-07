package com.springsecurity.model;

import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Builder @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Student {

    private Integer studentId;
    private String studentName;
}
