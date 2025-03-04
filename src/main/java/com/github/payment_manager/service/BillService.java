package com.github.payment_manager.service;

import com.github.payment_manager.domain.Bill;
import com.github.payment_manager.domain.BillStatus;
import com.github.payment_manager.dto.bill.*;
import com.github.payment_manager.repository.BillRepository;
import com.github.payment_manager.service.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BillService {

    @Autowired
    BillRepository repository;

    public Bill createBill(CreateBillDTO dto) {
        return repository.save(new Bill(dto));
    }

    public Bill findById(String id) {
        return repository.findById(id);
    }

    public Bill updateBill(UpdateBillDTO dto, String id) {
        Bill bill = repository.findById(id);

        if (bill != null) {
            if (dto.dueDate() != null)
                bill.setDue(DateUtils.dateFormat(dto.dueDate()));
            if (dto.value() != null)
                bill.setValue(new BigDecimal(dto.value()));
            if (dto.description() != null)
                bill.setDescription(dto.description());
            if (dto.paymentDate() != null)
                bill.setPayment(DateUtils.dateFormat(dto.paymentDate()));
            if (dto.status() != null)
                bill.setStatus(BillStatus.valueOf(dto.status()));
        }

        return repository.save(bill);
    }

    public List<GetBillDTO> list(FilterBillDTO dto, int page, int items) {
        List<GetBillDTO> listBill = new ArrayList<>();

        repository.list(validateInitialPeriod(dto.initialPeriod()),
                validFinalPeriod(dto.finalPeriod()),
                dto.description(),
                PageRequest.of(page, items)).forEach(b -> {

            GetBillDTO gbt = new GetBillDTO(b);
            listBill.add(gbt);
        });

        return listBill;
    }

    public Double sumValueFromPeriod(SumValuePeriodDTO dto) {

        return repository.sumValuesBetweenPeriods(validateInitialPeriod(dto.initialPeriod()), validFinalPeriod(dto.finalPeriod()));
    }

    public void saveBillsFromCsv(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            List<CreateBillDTO> bills = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                CreateBillDTO bill = new CreateBillDTO(data[0], data[1], data[2]);
                bills.add(bill);

                createBill(bill);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while processing CSV file");
        }
    }

    public Date validateInitialPeriod(Date initialPeriod) {
        if (initialPeriod != null) {
            return initialPeriod;
        } else {
            return Date.from(LocalDate.now().minusWeeks(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }

    public Date validFinalPeriod(Date finalPeriod) {
        if (finalPeriod != null) {
            return finalPeriod;
        } else {
            return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }

}
