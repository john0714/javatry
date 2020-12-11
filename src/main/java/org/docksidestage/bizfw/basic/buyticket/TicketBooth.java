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
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;

    private static final int ONE_DAY_PRICE = 7400;
    private static final int TWO_DAY_PRICE = 13200;
    private static final int FOUR_DAY_PRICE = 22400;

    // ===================================================================================
    //                                                                           Attribute
    //                                          
    private Integer salesProceeds;
    private final Quantity oneDayQuantity = new Quantity(MAX_QUANTITY);
    private final Quantity twoDayQuantity = new Quantity(MAX_QUANTITY);
    private final Quantity fourDayQuantity = new Quantity(MAX_QUANTITY);

    private final Ticket oneDayTicket = new OneDayTicket(ONE_DAY_PRICE, Ticket.ONE_DAY_TYPE);
    private final Ticket twoDayTicket = new MultiDayTicket(TWO_DAY_PRICE, Ticket.TWO_DAY_TYPE);
    private final Ticket fourDayTicket = new MultiDayTicket(FOUR_DAY_PRICE, Ticket.FOUR_DAY_TYPE);

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    public Ticket buyOneDayPassport(int handedMoney) {
        buyPassportCalc(handedMoney, ONE_DAY_PRICE, oneDayQuantity);

        return oneDayTicket;
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        buyPassportCalc(handedMoney, TWO_DAY_PRICE, twoDayQuantity);
        TicketBuyResult twoDayResult = new TicketBuyResult(twoDayTicket, handedMoney - TWO_DAY_PRICE);

        return twoDayResult;
    }

    public TicketBuyResult buyFourDayPassport(int handedMoney) {
        buyPassportCalc(handedMoney, FOUR_DAY_PRICE, fourDayQuantity);
        TicketBuyResult fourDayResult = new TicketBuyResult(fourDayTicket, handedMoney - FOUR_DAY_PRICE);

        return fourDayResult;
    }

    private int buyPassportCalc(int handedMoney, int price, Quantity quantity) {
        if (quantity.getQuantity() <= 0) {
            throw new TicketSoldOutException("Sold out");
        }

        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }

        quantity.decreaseQuantity();

        if (salesProceeds != null) {
            salesProceeds = salesProceeds + price;
        } else {
            salesProceeds = price;
        }

        return quantity.getQuantity();
    }

    // ===================================================================================
    //                                                                            Exception
    //                                                                            ========
    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getOneDayQuantity() {
        return oneDayQuantity.getQuantity();
    }

    public int getTwoDayQuantity() {
        return twoDayQuantity.getQuantity();
    }

    public int getFourDayQuantity() {
        return fourDayQuantity.getQuantity();
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
