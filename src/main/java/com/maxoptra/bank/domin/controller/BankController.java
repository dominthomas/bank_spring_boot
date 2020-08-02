package com.maxoptra.bank.domin.controller;

import com.maxoptra.bank.domin.bank.Bank;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
//TODO remove before production
//@CrossOrigin("*")
public class BankController {

    private Set<Bank> customerBanks;

    public BankController() {
        this.customerBanks = new TreeSet<>(Collections.reverseOrder());
        Bank bank = new Bank("HSBC", 12345678910L, new Date(1028121834000L));
        Bank bank2 = new Bank("HSBC", 12345678910L, new Date(1028121834000L));
        Bank bank3 = new Bank("Santander", 1234123238241234L, new Date(1596201834000L));
        Bank bank4 = new Bank("Barclays", 12345678910L, new Date(1028121834000L));
        Bank bank5 = new Bank("UBS", 12345678910L, new Date(1596201834000L));
        customerBanks.add(bank3);
        customerBanks.add(bank);
        customerBanks.add(bank2);
        customerBanks.add(bank4);
        customerBanks.add(bank5);

    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/getCustomerBanks")
    Collection<Bank> customerBanks() {
        return customerBanks;
    }

    @PostMapping("/addCustomerBank")
    Collection<Bank> newCustomerBank(@RequestBody Bank newCustomerBank) {
        customerBanks.add(newCustomerBank);
        return customerBanks;
    }

    @PostMapping("/addCustomerBanksMultiple")
    Collection<Bank> allCustomerBanks(@RequestBody List<Bank> banks) {
        customerBanks.addAll(banks);
        return customerBanks;
    }
}
