package com.duffin22.marketingapi;

/**
 * Created by mgkan on 2016-08-17.
 */
public class Stock {
  public String symbol="";
  public String name="";
  public String exchange="";
  public int quantity=0;

  public Stock(String symbol,String name, String exchange, int quantity){
    this.symbol=symbol;
    this.name=name;
    this.exchange=exchange;
    this.quantity=quantity;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getExchange() {
    return exchange;
  }

  public void setExchange(String exchange) {
    this.exchange = exchange;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
