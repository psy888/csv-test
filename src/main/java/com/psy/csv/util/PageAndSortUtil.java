package com.psy.csv.util;

import org.springframework.data.domain.Sort;

public class PageAndSortUtil {
    /**
     * Sort helper method from string
     *
     * @param sortBy - field name
     * @param order  - order "asc" or "desc"
     * @return Sort object
     */
    public static Sort getSorting(String sortBy, String order) {

        Sort s = Sort.by(sortBy);
        if ("desc".contentEquals(order)) {
            return s.descending();
        }
        if ("asc".contentEquals(order)) {
            return s.ascending();
        }
        return s;
    }
}
