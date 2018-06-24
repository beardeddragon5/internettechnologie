/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.matthias_ramsauer.itt;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 *
 * @author matthias
 */
public class DateMatcher {
    
    private static final Pattern PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Predicate<String> MATCHER = PATTERN.asPredicate();
    
    public static String requireValidDate(String date) {
        if(!MATCHER.test(Objects.requireNonNull(date))) {
            throw new IllegalArgumentException("date is not in right format 'JJJJ-MM-TT'");
        }
        return date;
    }
}
