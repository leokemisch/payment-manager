package com.github.payment_manager.controller;

import com.github.payment_manager.dto.bill.*;
import com.github.payment_manager.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("bill")
public class BillController {

    @Autowired
    private BillService service;

    @PostMapping
    public ResponseEntity create(@RequestBody CreateBillDTO data) {
        return ResponseEntity.ok(new GetBillDTO(service.createBill(data)));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody UpdateBillDTO data, @PathVariable String id) {
        return ResponseEntity.ok(new GetBillDTO(service.updateBill(data, id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable String id) {
        return ResponseEntity.ok(new GetBillDTO(service.findById(id)));
    }

    @GetMapping("/list")
    public ResponseEntity list(@RequestBody FilterBillDTO data, @RequestParam int page, @RequestParam int items) {
        return ResponseEntity.ok(service.list(data, page, items));
    }

    @GetMapping("/sumValue")
    public ResponseEntity sumValue(@RequestBody SumValuePeriodDTO data) {
        return ResponseEntity.ok(service.sumValueFromPeriod(data));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty CSV file");
        }

        try {
            service.saveBillsFromCsv(file);
            return ResponseEntity.status(HttpStatus.OK).body("CSV file processed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while processing CSV file");
        }
    }
}