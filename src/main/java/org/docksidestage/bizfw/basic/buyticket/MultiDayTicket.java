/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author Kim
 */

public class MultiDayTicket implements Ticket {

    // TODO 次回は早めにレビューをもらう予定
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice;
    private final TicketType type;
    private boolean alreadyIn;
    private int entrance;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public MultiDayTicket(int displayPrice, TicketType type) {
        this.displayPrice = displayPrice;
        this.type = type;
        this.entrance = type.getDays();
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }

        if (entrance == 0) {
            throw new IllegalStateException("entrance count is done by this ticket: displayedPrice=" + displayPrice);
        }

        entrance--;
        alreadyIn = true;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }

    public TicketType getType() {
        return type;
    }

    public int getEntrance() {
        return entrance;
    }
}