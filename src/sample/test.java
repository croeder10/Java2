package sample;

import java.util.Scanner;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.ArrayList;


public class test {
    public static void main(String[] args) throws Exception
    {
        Employee myEmployee = new Employee();
        myEmployee.firstName = "Robert";
        myEmployee.birthYear = 1955;

        System.out.println("The Employee's birth year is: " + myEmployee.birthYear);
        changeEmployee(myEmployee);
        System.out.println("The Employee's birth year is: " + myEmployee.birthYear);

        int myNumber = 10;
        System.out.println("The number is: " + myNumber);
        changeNumber(myNumber);
        System.out.println("The number is: " + myNumber);

        String myString = "Hello";
        System.out.println("The string says: " + myString);
        changeString(myString);
        System.out.println("The string says: " + myString);
    }

    public static void changeEmployee(Employee emp)
    {
        Employee emp2 = new Employee();
        emp2.firstName = "Jack";
        emp.birthYear = 2001;
    }

    public static void changeNumber(int x)
    {
        x += 10;
    }

    public static void changeString(String str)
    {
        str += " World";
    }
}
