package com.sparta.msa_exam.auth.application.util;

import java.util.Date;

public interface DateGenerator {

    Date getCurrentDate();

    Date getExpireDate(long exp);
}
