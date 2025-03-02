package com.github.payment_manager.dto;

import java.util.Date;
public record CreateBillDTO(Date dueDate,
                            Date paymentDate,
                            String value,
                            String description) {
}
