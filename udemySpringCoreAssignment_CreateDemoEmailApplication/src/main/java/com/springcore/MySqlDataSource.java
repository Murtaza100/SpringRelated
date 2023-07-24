package com.springcore;

import org.springframework.stereotype.Component;

@Component
public class MySqlDataSource implements DataSource{
    @Override
    public String[] getEmail() {
        return new String[]{"abc@email.com", "pqr@email.com"};
    }
}
