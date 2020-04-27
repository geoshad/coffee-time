package com.example.justjava;

public class Order {
    // attributes of Order class
    private int _id;
    private String _custName;
    private int _saleAmount; //variables always start with lower-case letter.

    public Order(){
        _id = 0;
        _custName = null; // default value of any object.
        _saleAmount = 0;
    }
    // second constructor
    public Order(String custName, int saleAmount){
        this._custName = custName;
        this._saleAmount = saleAmount;
    }
    // can use Code > Generate to create getters and setters
    public int get_id() { return _id; }
    public String get_custName() { return _custName; }
    public double get_saleAmount() { return _saleAmount; }

    public void set_id(int id) { _id = id; }
    public void set_custName(String custName) { _custName = custName; }
    public void set_saleAmount(int saleAmount) { _saleAmount = saleAmount; }

}