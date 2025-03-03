package com.github.payment_manager.dto.bill;

public record UpdateBillDTO(String id, String dueDate, String value, String description) {
}
