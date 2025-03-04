package com.github.payment_manager.service;

import com.github.payment_manager.domain.Bill;
import com.github.payment_manager.dto.bill.*;
import com.github.payment_manager.repository.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BillServiceTest {

    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private BillService billService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBill() {
        CreateBillDTO dto = new CreateBillDTO("10/10/2030", "30.0", "Sample description");
        Bill bill = new Bill(dto);

        when(billRepository.save(any(Bill.class))).thenReturn(bill);

        Bill result = billService.createBill(dto);

        assertNotNull(result);
        assertEquals("Sample description", result.getDescription());
        verify(billRepository, times(1)).save(any(Bill.class));
    }

    @Test
    public void testUpdateBill() {
        UpdateBillDTO dto = new UpdateBillDTO("10/10/2030", "150.0", "Updated description", "PAID", "10/02/2025");
        Bill bill = new Bill();
        bill.setId("1");

        when(billRepository.findById("1")).thenReturn(bill);
        when(billRepository.save(any(Bill.class))).thenReturn(bill);

        Bill result = billService.updateBill(dto, "1");

        assertNotNull(result);
        assertEquals("Updated description", bill.getDescription());
        assertEquals(new BigDecimal("150.0"), bill.getValue());
        verify(billRepository, times(1)).findById("1");
        verify(billRepository, times(1)).save(bill);
    }

    @Test
    public void testList() {
        FilterBillDTO filter = new FilterBillDTO(null, null, "Test description");
        when(billRepository.list(any(), any(), eq("Test description"), any(PageRequest.class)))
                .thenReturn(List.of(new Bill()));

        List<GetBillDTO> result = billService.list(filter, 0, 10);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(billRepository, times(1)).list(any(), any(), eq("Test description"), any(PageRequest.class));
    }

    @Test
    public void testSumValueFromPeriod() {
        SumValuePeriodDTO dto = new SumValuePeriodDTO(new Date(), new Date());

        when(billRepository.sumValuesBetweenPeriods(any(Date.class), any(Date.class))).thenReturn(250.0);

        Double result = billService.sumValueFromPeriod(dto);

        assertNotNull(result);
        assertEquals(250.0, result);
        verify(billRepository, times(1)).sumValuesBetweenPeriods(any(Date.class), any(Date.class));
    }

}