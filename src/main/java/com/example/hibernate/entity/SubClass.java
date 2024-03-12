package com.example.hibernate.entity;

import com.example.hibernate.Hibernate;

public interface SubClass {
    default double calculateArea(double radius) {
        return calculateCircleArea(radius);
    }

    default double calculateVolume(double radius, double height) {
        return calculateCircleArea(radius) * height;
    }

    private double calculateCircleArea(double radius) {
        return Math.PI * radius * radius;
    }
}
