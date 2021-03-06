package com.shiros.inventory.controller;

import com.shiros.inventory.entity.Supplier;
import com.shiros.inventory.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {

    private SupplierService supplierService;

    @Autowired
    public void setInjectionBean(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @RequestMapping(value = "/supplier/{id}", method = RequestMethod.GET)
    public Supplier getSupplierById(@PathVariable("id") Long id) {
        return supplierService.getSupplierById(id);
    }

    @RequestMapping(value = "/supplier", method = RequestMethod.GET)
    public List<Supplier> getSuppliers(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        if (page == null || size == null) {
            return supplierService.getAllSuppliers();
        }
        return supplierService.getSuppliers(page, size);
    }

    @RequestMapping(value = "/supplier_count", method = RequestMethod.GET)
    public Long getSupplierCount() {
        return supplierService.getSupplierCount();
    }

    @Transactional
    @RequestMapping(value = "/supplier", method = RequestMethod.POST)
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierService.createSupplier(supplier);
    }

    @Transactional
    @RequestMapping(value = "/supplier/{id}", method = RequestMethod.PUT)
    public Supplier updateSupplier(@PathVariable("id") Long id, @RequestBody Supplier supplier) {
        supplier.setId(id);
        return supplierService.updateSupplier(supplier);
    }

}
