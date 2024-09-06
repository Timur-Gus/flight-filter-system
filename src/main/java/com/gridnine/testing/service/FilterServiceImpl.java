package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.impl.FilterRuleImpl;
import com.gridnine.testing.service.impl.FilterService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterServiceImpl implements FilterService {
    private final List<FilterRuleImpl> filterRules;

    public FilterServiceImpl(final FilterRuleImpl... filterRules) {
        this.filterRules = Arrays.stream(filterRules).toList();
    }

    /**
     * Performs filtering of flights in accordance with the established rules
     * @param flights Flight list
     * @return Filtered flight list
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>(flights);
        for (FilterRuleImpl filterRule : filterRules) {
            filteredFlights = filteredFlights.stream()
                    .filter(flight -> filterRule.filter(flight))
                    .collect(Collectors.toList());
        }
        return filteredFlights;
    }
}
