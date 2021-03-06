package com.shiros.inventory.controller;

import com.shiros.inventory.entity.RestockForm;
import com.shiros.inventory.service.RestockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestockController {

    private RestockService restockService;

    @Autowired
    public void setInjectedBean(RestockService restockService) {
        this.restockService = restockService;
    }

    @RequestMapping(value = "/restock", method = RequestMethod.GET)
    public List<RestockForm> getRestocks(@RequestParam int page, @RequestParam int size) {
        return restockService.getRestockForms(page, size);
    }

    @RequestMapping(value = "/restock_count", method = RequestMethod.GET)
    public Long getRestockCount() {
        return restockService.getRestockFormCount();
    }

    @RequestMapping(value = "/restock/{id}", method = RequestMethod.GET)
    public RestockForm getRestockFormById(@PathVariable("id") long id) {
        return restockService.getRestockFormById(id);
    }

    @Transactional
    @RequestMapping(value = "/restock", method = RequestMethod.POST)
    public RestockForm createRestock(@RequestBody RestockForm restockForm) {
        return restockService.createRestockForm(restockForm);
    }

    @Transactional
    @RequestMapping(value = "/restock/{id}", method = RequestMethod.PUT)
    public RestockForm updateRestock(@PathVariable("id") long id, @RequestBody RestockForm restockForm) {
        restockForm.setId(id);
        return restockService.updateRestockForm(restockForm);
    }

}
