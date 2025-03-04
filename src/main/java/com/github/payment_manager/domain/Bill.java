package com.github.payment_manager.domain;

import com.github.payment_manager.dto.bill.CreateBillDTO;
import com.github.payment_manager.service.utils.DateUtils;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "bill")

public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    Date due;
    Date payment;
    BigDecimal value;
    String description;
    BillStatus status;


    public Bill(CreateBillDTO dto) {
        this.due = DateUtils.dateFormat(dto.dueDate());
        this.payment = null;
        this.value = new BigDecimal(dto.value());
        this.description = dto.description();
        this.status = BillStatus.PENDING;
    }

    public Bill() {
    }

    public void setDue(Date dueDate) {
        this.due = dueDate;
    }

    public void setPayment(Date paymentDate) {
        this.payment = paymentDate;
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

    public String getId() {
        return id;
    }

    public Date getDue() {
        return due;
    }

    public Date getPayment() {
        return payment;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public BillStatus getStatus() {
        return status;
    }
}
