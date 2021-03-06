package com.fm.internal.controllers;

import com.fm.internal.models.*;
import com.fm.internal.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class InitController {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private OutcomeTypeService outcomeTypeService;
    @Autowired
    private OutcomeService outcomeService;
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private CurrencyService currencyService;

    @RequestMapping("/init")
    public ModelAndView init() {
        User user = new User("user@email", "password", new UserInfo("name", "lastname", currencyService.findCurrencyByCharCode("RUB")));
        userService.createUser(user);

        Account[] accounts = {
                new Account("Visa", new BigDecimal(29999), null, user, currencyService.findCurrencyByCharCode("KZT")),
                new Account("Mastercard", new BigDecimal(3000), null, user, currencyService.findCurrencyByCharCode("USD")),
                new Account("Альфа", new BigDecimal(5752), null, user, currencyService.findCurrencyByCharCode("EUR"))
        };
        for (Account account : accounts) {
            accountService.createAccount(account);
        }

        OutcomeType[] types = {
                new OutcomeType("Еда вне дома", new BigDecimal(87000), user),
                new OutcomeType("Бензин", new BigDecimal(141000), user),
                new OutcomeType("Кварплата", new BigDecimal(13000), user)
        };
        for (OutcomeType type : types) {
            outcomeTypeService.addOutcomeType(type);
        }

        Outcome[] outcomes = {
                new Outcome(new BigDecimal(2122), currencyService.getOutcomeAmountForDefaultCurrency(accounts[0],
                        new BigDecimal(2122)), LocalDate.now(), LocalTime.now(), accounts[0], types[0]),
                new Outcome(new BigDecimal(332), currencyService.getOutcomeAmountForDefaultCurrency(accounts[2],
                        new BigDecimal(332)), LocalDate.now(), LocalTime.now(), accounts[2], types[0]),
                new Outcome(new BigDecimal(4144), currencyService.getOutcomeAmountForDefaultCurrency(accounts[0],
                        new BigDecimal(4144)), LocalDate.now(), LocalTime.now(), accounts[0], types[1]),
                new Outcome(new BigDecimal(9999), currencyService.getOutcomeAmountForDefaultCurrency(accounts[1],
                        new BigDecimal(9999)), LocalDate.now(), LocalTime.now(), accounts[1], types[1]),
                new Outcome(new BigDecimal(50), currencyService.getOutcomeAmountForDefaultCurrency(accounts[2],
                        new BigDecimal(50)), LocalDate.now(), LocalTime.now(), accounts[2], types[2]),
                new Outcome(new BigDecimal(1234), currencyService.getOutcomeAmountForDefaultCurrency(accounts[1],
                        new BigDecimal(1234)), LocalDate.now(), LocalTime.now(), accounts[1], types[2]),
                new Outcome(new BigDecimal(2222), currencyService.getOutcomeAmountForDefaultCurrency(accounts[2],
                        new BigDecimal(2222)), LocalDate.now(), LocalTime.now(), accounts[2], types[0]),
                new Outcome(new BigDecimal(3999), currencyService.getOutcomeAmountForDefaultCurrency(accounts[0],
                        new BigDecimal(3999)), LocalDate.now(), LocalTime.now(), accounts[0], types[2]),
        };
        for (int i = 0; i < 10; i++) {
            for (Outcome outcome : outcomes) {
                outcomeService.addOutcome(outcome);
            }
        }

        Income[] incomes = {
                new Income(BigDecimal.valueOf(13423), LocalDate.now(), LocalTime.now(), accounts[0]),
                new Income(BigDecimal.valueOf(4324), LocalDate.now(), LocalTime.now(), accounts[1]),
                new Income(BigDecimal.valueOf(13242), LocalDate.now(), LocalTime.now(), accounts[1]),
                new Income(BigDecimal.valueOf(1345435), LocalDate.now(), LocalTime.now(), accounts[0]),
                new Income(BigDecimal.valueOf(234234), LocalDate.now(), LocalTime.now(), accounts[2]),
                new Income(BigDecimal.valueOf(6346), LocalDate.now(), LocalTime.now(), accounts[2]),
                new Income(BigDecimal.valueOf(2356), LocalDate.now(), LocalTime.now(), accounts[0]),
        };

        for (Income income : incomes) {
            incomeService.addIncome(income);
        }
        return new ModelAndView("index");
    }
}
