package ru.danilenko.neoflex.service;

import org.springframework.stereotype.Service;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

@Service
public class HolidayPaymentService {


    public String calculatePayment(int salary,int dayOfHoliday, LocalDate start, LocalDate end) {
        String answer = "Incorrect input data";
        int dayOfHolidayLocal;
        if(start!=null && end!=null) {
            dayOfHolidayLocal = (int) start.datesUntil(end)
                    .map(LocalDate::getDayOfWeek)
                    .filter(day -> !Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(day))
                    .count();
        }
        else
            dayOfHolidayLocal = dayOfHoliday;
        if(salary*dayOfHolidayLocal > 0)
            answer = String.valueOf( salary * dayOfHolidayLocal);

        return answer;

    }
}
