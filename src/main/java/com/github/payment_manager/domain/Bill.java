package com.github.payment_manager.domain;

import com.github.payment_manager.dto.bill.CreateBillDTO;
import com.github.payment_manager.service.utils.DateUtils;
import jakarta.persistence.*;
import lombok.Setter;

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
    String userId;

    public Bill(CreateBillDTO dto) {
        this.dueDate = DateUtils.dateFormat(dto.dueDate());
        this.paymentDate = null;
        this.value = new BigDecimal(dto.value());
        this.description = dto.description();
        this.status = BillStatus.PENDING;
        this.userId = dto.userId();
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }
}
