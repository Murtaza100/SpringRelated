package com.springcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
        EmailService emailService = applicationContext.getBean(EmailService.class);
        String[] res = emailService.sendEmail();
        for (String mails:res) {
            System.out.println( mails);
        }

    }
}
