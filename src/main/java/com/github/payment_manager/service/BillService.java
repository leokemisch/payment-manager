package com.github.payment_manager.service;

import com.github.payment_manager.domain.Bill;
import com.github.payment_manager.dto.bill.CreateBillDTO;
import com.github.payment_manager.dto.bill.UpdateBillDTO;
import com.github.payment_manager.repository.BillRepository;
import com.github.payment_manager.service.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BillService {

    @Autowired
    BillRepository repository;

    public Bill createBill(CreateBillDTO dto) {
        return repository.save(new Bill(dto));
    }

    public Bill updateBill(UpdateBillDTO dto) {
        Bill bill = repository.findById(dto.id());

        if(dto.dueDate() != null)
            bill.setDueDate(DateUtils.dateFormat(dto.dueDate()));
        if(dto.value() != null)
            bill.setValue(new BigDecimal(dto.value()));
        if(dto.description() != null)
            bill.setDescription(dto.description());

        return repository.save(bill);
    }
}
