package com.github.payment_manager.dto.bill;

public record UpdateBillDTO(String dueDate, String value, String description, String status, String paymentDate) {
}
