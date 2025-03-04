package com.github.payment_manager.repository;

import com.github.payment_manager.domain.Bill;
import com.github.payment_manager.dto.bill.CreateBillDTO;
import com.github.payment_manager.service.utils.DateUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
class BillRepositoryTest {

    @Autowired
    BillRepository billRepository;

    @Test
    @DisplayName("Should return the correct bill")
    public void testFindById() {

        CreateBillDTO billDTO1 = new CreateBillDTO("10/10/2090", "120.00", "no description");

        Bill bill = billRepository.save(new Bill(billDTO1));

        assertNotNull(billRepository.findById(bill.getId()));
    }

    @Test
    @DisplayName("Should return bills inside the date range")
    void list() {

        CreateBillDTO billDTO1 = new CreateBillDTO("10/10/2026", "10.00", "coca cola");
        CreateBillDTO billDTO2 = new CreateBillDTO("10/10/2027", "200.00", "market");
        CreateBillDTO billDTO3 = new CreateBillDTO("10/10/2037", "20000.00", "car");

        billRepository.save(new Bill(billDTO1));
        billRepository.save(new Bill(billDTO2));
        billRepository.save(new Bill(billDTO3));

        List<Bill> bills = billRepository.list(DateUtils.dateFormat("01/01/2025"),
                DateUtils.dateFormat("12/12/2035"),
                null,
                PageRequest.of(0, 5));

        assertNotNull(bills);
        assertThat(bills.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Should return the correct value of sum")
    void sumValuesBetweenPeriods() {
        CreateBillDTO billDTO1 = new CreateBillDTO("10/10/2030", "1300.00", "travel to japan");
        CreateBillDTO billDTO2 = new CreateBillDTO("10/10/2025", "2000.00", "credit card");

        Bill bill1 = billRepository.save(new Bill(billDTO1));
        Bill bill2 = billRepository.save(new Bill(billDTO2));

        bill1.setPayment(DateUtils.dateFormat("02/03/2025"));
        bill2.setPayment(DateUtils.dateFormat("02/03/2025"));

        billRepository.save(bill1);
        billRepository.save(bill2);

        Double sum = billRepository.sumValuesBetweenPeriods(DateUtils.dateFormat("01/01/2025"), DateUtils.dateFormat("01/01/2026"));
        assertNotNull(sum);
        assertThat(sum).isEqualTo(3300.0);
    }

}