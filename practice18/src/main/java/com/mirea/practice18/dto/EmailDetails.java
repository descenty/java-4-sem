package com.mirea.practice18.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailDetails {
    private String recipient;
    private String subject;
    private String msgBody;
}
