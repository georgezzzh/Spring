package com.test.ans;

import java.time.LocalDate;

public class Stock {
    private int s_id;
    private String stock_code;
    private LocalDate trading_date;
    private double close_price;
    private boolean isValid;

    @Override
    public String toString() {
        return "Stock{" +
                "s_id=" + s_id +
                ", stock_code='" + stock_code + '\'' +
                ", trading_date=" + trading_date +
                ", close_price=" + close_price +
                ", isValid=" + isValid +
                '}';
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public void setTrading_date(LocalDate trading_date) {
        this.trading_date = trading_date;
    }

    public void setClose_price(double close_price) {
        this.close_price = close_price;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public int getS_id() {
        return s_id;
    }

    public String getStock_code() {
        return stock_code;
    }

    public LocalDate getTrading_date() {
        return trading_date;
    }

    public double getClose_price() {
        return close_price;
    }

    public boolean isValid() {
        return isValid;
    }
}
