package com.springcore;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PostegreSqlDataSource implements DataSource{
    @Override
    public String[] getEmail() {
        return new String[] {"123@gmail.com","456@gmail.com"};
    }
}
