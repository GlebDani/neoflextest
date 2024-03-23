package ru.danilenko.neoflex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.danilenko.neoflex.service.HolidayPaymentService;

import java.time.LocalDate;

@Controller
public class HolidayPaymentCalculator {

    private HolidayPaymentService holidayPaymentService;


    public HolidayPaymentCalculator(HolidayPaymentService holidayPaymentService) {
        this.holidayPaymentService = holidayPaymentService;
    }


    @GetMapping("/calculate")
    @ResponseBody
    public String getCalculation(@RequestParam(value = "salary") int salary,
                               @RequestParam(value = "dayOfHolidays", required = false, defaultValue = "0") int dayOfHolidays,
                               @RequestParam(value = "dayOfStart", required = false) LocalDate dayOfStart,
                               @RequestParam(value = "dayOfEnd", required = false) LocalDate dayOfEnd) {
        return holidayPaymentService.calculatePayment(salary,dayOfHolidays,dayOfStart,dayOfEnd);
    }
}
