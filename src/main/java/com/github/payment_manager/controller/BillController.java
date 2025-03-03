package com.github.payment_manager.controller;

import com.github.payment_manager.dto.bill.CreateBillDTO;
import com.github.payment_manager.dto.bill.UpdateBillDTO;
import com.github.payment_manager.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bill")
public class BillController {

    @Autowired
    BillService service;

    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody CreateBillDTO data) {
        return ResponseEntity.ok(service.createBill(data));
    }

    public ResponseEntity update(@RequestBody UpdateBillDTO data) {
        return ResponseEntity.ok(service.updateBill(data));
    }
}
