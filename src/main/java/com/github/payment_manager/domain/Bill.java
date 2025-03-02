package com.github.payment_manager.domain;

import com.github.payment_manager.dto.CreateBillDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    Date dueDate;
    Date paymentDate;
    BigDecimal value;
    String description;
    BillStatus status;
    String user;

    public Bill(CreateBillDTO dto, String user) {
        this.dueDate = dto.dueDate();
        this.paymentDate = dto.paymentDate();
        this.value = new BigDecimal(dto.value());
        this.description = dto.description();
        this.status = BillStatus.PENDING;
        this.user = user;
    }
}
