package sample;

import java.util.Scanner;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.UUID;

public class Employee implements Worker
{
    public String firstName;
    public String lastName;
    public int birthYear;
    public UUID id;
    public boolean isActive;


    @Override
    public String toString()
    {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public void hire()
    {
        isActive = true;
    }

    @Override
    public void fire()
    {
        isActive = false;
    }
}
