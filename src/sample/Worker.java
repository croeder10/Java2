package sample;

import java.util.Scanner;
import javax.swing.*;
import java.io.File;
//initailize other class
//CloudDeveloper cd = new CloudDeveloper();
//super gets methods from extend class
//public double getSalary() {
//        return super.getSalary() + (0.3 * super.getSalary());
//    }
//    public int getVacationDays() {
//        return super.getVacationDays() + 4;           // 3 weeks 4 days vacation
//    }
//    public String getVacationForm() {
//        int form = 3;
//        String vf = super.getVacationForm();
//        for (int x = 1; x != form; x++) {
//            vf = vf.concat(vf);
//       }
//        return vf;
//   }
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
//create a arraylist to hold some names
//      ArrayList<String> nameList = new ArrayList<String>();
//      ArrayList<Double> gradeList = new ArrayList<Double>();
//add some names to arraylist
//      nameList.add("Bill");
//      gradeList.add(new Double(100));
//display the size of the arraylist
//     System.out.println("The ArrayList has " + nameList.size() + " objects stored in it.");
//display the items in the namelist
//     for (int index = 0; index < nameList.size(); index++)
//         System.out.println(nameList.get(index));
//display the items in namelist and their indexs
//     for (int index = 0; index < nameList.size(); index++) {
//          System.out.println("Index: " + index + " Name: " + nameList.get(index));
//      }
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public interface Worker
{
    public void hire();
    public void fire();
}
