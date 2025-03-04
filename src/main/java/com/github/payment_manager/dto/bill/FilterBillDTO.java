package com.github.payment_manager.dto.bill;

import java.util.Date;

public record FilterBillDTO(Date initialPeriod, Date finalPeriod, String description) {
}
