package org.example.creation;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "storage.setting",havingValue = "car")
public class CarStorage implements Storage, InitializingBean {
    public void afterPropertiesSet() throws Exception {
        System.out.println("CarStorage set");
    }
}
