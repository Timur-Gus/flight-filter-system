package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.DepartureBeforeCurrentTimeFilter;
import com.gridnine.testing.service.FilterServiceImpl;
import com.gridnine.testing.service.SegmentDateBeforeDepartureFilter;
import com.gridnine.testing.service.TimeOnEarthExceedsTwoHoursFilter;
import com.gridnine.testing.service.impl.FilterService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("Список всех перелетов: ");
        System.out.println(flights);

        FilterService filterService = new FilterServiceImpl(new DepartureBeforeCurrentTimeFilter());
        System.out.println("Список перелетов с датой вылета до текущей даты: ");
        System.out.println(filterService.filter(flights));

        filterService = new FilterServiceImpl(new SegmentDateBeforeDepartureFilter());
        System.out.println("Список перелетов с сегментами с датой прилёта раньше даты вылета: ");
        System.out.println(filterService.filter(flights));

        filterService = new FilterServiceImpl(new TimeOnEarthExceedsTwoHoursFilter());
        System.out.println("Перелеты, где общее время, проведённое на земле, превышает два часа: ");
        System.out.println(filterService.filter(flights));
    }

}