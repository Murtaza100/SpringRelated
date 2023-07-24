package com.springcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    DataSource dataSource;
    @Autowired
    public EmailService(DataSource dataSource){
        this.dataSource=dataSource;
    }
    public String[] sendEmail(){
        return dataSource.getEmail();
    }
}
