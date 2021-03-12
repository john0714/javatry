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
 * @author jflute
 * @author Kim
 */

public enum TicketType {
    ONE(1, 7400), TWO(2, 13200), THREE(3, 17800), FOUR(4, 22400);

    final private int days;
    final private int price;

    // constructor
    TicketType(int days, int price) {
        this.days = days;
        this.price = price;
    }

    public int getDays() {
        return days;
    }

    public int getPrice() {
        return price;
    }
}
