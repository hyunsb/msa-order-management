package com.sparta.msa_exam.auth.application.util;

import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenDateGenerator implements DateGenerator {

    @Override
    public Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    @Override
    public Date getExpireDate(long exp) {
        long currentTime = getCurrentDate().getTime();
        return new Date(currentTime + exp);
    }
}
