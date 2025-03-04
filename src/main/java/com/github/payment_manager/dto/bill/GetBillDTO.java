package com.github.payment_manager.dto.bill;

import com.github.payment_manager.domain.Bill;
import com.github.payment_manager.domain.BillStatus;

import java.math.BigDecimal;
import java.util.Date;

public record GetBillDTO(String id, Date dueDate, Date paymentDate, BigDecimal value, String description,
                         String status) {
    public GetBillDTO(Bill bill) {
        this(bill.getId(),
                bill.getDue(),
                bill.getPayment(),
                bill.getValue(),
                bill.getDescription(),
                bill.getStatus() != null ? bill.getStatus().toString() : BillStatus.PENDING.toString());
    }
}
