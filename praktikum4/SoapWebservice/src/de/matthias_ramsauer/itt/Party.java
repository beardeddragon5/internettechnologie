/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.matthias_ramsauer.itt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author matthias
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Party {
    
    private static final Logger LOGGER = Logger.getLogger(Party.class.getName());
    
    private final List<String> guestlist;
    private final int max;
    private final String date;
    private final String dj;

    public Party(String date, String dj, int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("max must be greater then zero");
        }
        this.guestlist = new ArrayList<>(max);
        this.max = max;
        this.date = DateMatcher.requireValidDate(date);
        this.dj = Objects.requireNonNull(dj, "dj must be set");
    }

    public String getDate() {
        return date;
    }

    public String getDj() {
        return dj;
    }
    
    public int amountOfGuests() {
        return this.guestlist.size();
    }
    
    public String[] getGuestList() {
        return this.guestlist.toArray(new String[this.guestlist.size()]);
    }
    
    public boolean invite(String guest) {
        Objects.requireNonNull(guest);
        final boolean canCome = this.amountOfGuests() < this.max;
        LOGGER.log(Level.INFO, "invite {0} accepted?: {1}", new Object[]{ guest, canCome });
        if (canCome) {
            this.guestlist.add(guest);
        }
        return canCome;
    }
}
