package com.example.TimeTableGenerator.Controllers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DayOfWeekComparator implements Comparator<String> {

    private static final List<String> DAYS_OF_WEEK_ORDER = Arrays.asList(
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
    );

    @Override
    public int compare(String day1, String day2) {
        return Integer.compare(DAYS_OF_WEEK_ORDER.indexOf(day1), DAYS_OF_WEEK_ORDER.indexOf(day2));
    }
}
