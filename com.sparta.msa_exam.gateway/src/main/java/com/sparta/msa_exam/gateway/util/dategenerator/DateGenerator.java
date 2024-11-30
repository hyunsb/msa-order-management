package com.sparta.msa_exam.gateway.util.dategenerator;

import java.util.Date;

public interface DateGenerator {

    Date getCurrentDate();

    Date getExpireDate(long exp);
}
