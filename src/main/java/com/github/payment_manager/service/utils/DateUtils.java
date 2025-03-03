package com.github.payment_manager.service.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date dateFormat(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            return format.parse(date);
        } catch (
                Exception e
        ) {
            e.printStackTrace();
            return null;
        }
    }
}
