package org.example.creation;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(SportStorage.class)
public class SportCreated implements InitializingBean {
    public void afterPropertiesSet() throws Exception {
        System.out.println("There is SportStorage");
    }
}
