package org.example.creation;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(SportStorage.class)
public class SportMissing implements InitializingBean {
    public void afterPropertiesSet() throws Exception {
        System.out.println("There is no SportStorage");
    }
}
