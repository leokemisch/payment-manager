package com.github.payment_manager.dto.bill;

public record CreateBillDTO(String dueDate,
                            String value,
                            String description) {
}
