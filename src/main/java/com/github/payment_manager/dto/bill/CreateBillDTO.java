package com.github.payment_manager.dto.bill;

import java.util.Date;

public record CreateBillDTO(String dueDate,
                            String value,
                            String description,
                            String userId) {
}
