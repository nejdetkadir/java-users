/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nejdetkadirr
 */
public class User {
    private String name;
    private String surname;
    private String gender;
    private int age;
    private double length;
    private double weight;

    public User(String name, String surname, String gender, int age, double length, double weight) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        this.length = length;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getLength() {
        return length;
    }

    public double getWeight() {
        return weight;
    }
    
    public double getBMI() {
        return getWeight() / (getLength() * getLength());
    }
    
    public String getBMIStatus() {
        String info = "";
        if (getBMI() > 40) {
            info = "Overly Obese";
        } else if (getBMI() > 30) {
            info = "Obese";
        } else if (getBMI() > 25) {
            info = "Overweight";
        } else if (getBMI() > 18.5) {
            info = "Normal";
        } else {
            info = "Underweight";
        }
        return info;
    }
    
}
