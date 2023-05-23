package com.example.practice.model;

public class Instrument {
    private Long id;
    private String title; //наименование
    private String unit_of_measurement; //еденица измерения
    private int division_price; //цена деления
    private double sensitivity; //чувствительность
    private int maximum_value; //максимальное значение
    private int number_of_box; //номер ящика

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit_of_measurement() {
        return unit_of_measurement;
    }

    public void setUnit_of_measurement(String unit_of_measurement) {
        this.unit_of_measurement = unit_of_measurement;
    }

    public int getDivision_price() {
        return division_price;
    }

    public void setDivision_price(int division_price) {
        this.division_price = division_price;
    }

    public double getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(double sensitivity) {
        this.sensitivity = sensitivity;
    }

    public int getMaximum_value() {
        return maximum_value;
    }

    public void setMaximum_value(int maximum_value) {
        this.maximum_value = maximum_value;
    }
    public int getNumber_of_box() {
        return number_of_box;
    }

    public void setNumber_of_box(int number_of_box) {
        this.number_of_box = number_of_box;
    }

    public Instrument(Long id, String title, String unit_of_measurement, int division_price, double sensitivity, int maximum_value, int number_of_box) {
        this.id = id;
        this.title = title;
        this.unit_of_measurement = unit_of_measurement;
        this.division_price = division_price;
        this.sensitivity = sensitivity;
        this.maximum_value = maximum_value;
        this.number_of_box = number_of_box;
    }
}

