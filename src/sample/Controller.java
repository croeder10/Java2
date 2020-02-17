package sample;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.UUID;

public class Controller implements Initializable
{
    @FXML
    private ListView<Employee> employeeListView;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private CheckBox isActiveCheckBox;
    @FXML
    private Button btnDeleteSelected;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnAddNew;
    @FXML
    JFXButton createdbbutton;
    @FXML
    JFXButton deletetablebutton;
    @FXML
    JFXButton loaddatabutton;




    final String hostname = "java2-db.clq42kmvfexe.us-east-1.rds.amazonaws.com";
    final String dbName = "java2db";
    final String port = "3306";
    final String userName = "croeder10";
    final String password = "must10ANG";
    final String AWS_URL = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
    //final String AWS_URL = "jdbc:mysql://java2-db.clq42kmvfexe.us-east-1.rds.amazonaws.com:3306/java2db?user=croeder10&password=must10ANG";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        createdbbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try{

                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("driver loaded");
                    Connection con = DriverManager.getConnection(AWS_URL);

                    Statement stmt = con.createStatement();

                    try
                    {
                        stmt.execute("CREATE TABLE Employee (" +
                                "FirstName CHAR(25), " +
                                "LastName CHAR(25), " +
                                "Id VARCHAR(36), " +
                                "IsActive BOOLEAN )");

                        System.out.println("TABLE CREATED");
                    }
                    catch (Exception ex)
                    {
                        System.out.println("TABLE ALREADY EXISTS, NOT CREATED");
                    }

                    UUID id = UUID.randomUUID();
                    String idString = id.toString();

                    String sql = "INSERT INTO Employee VALUES" +
                            "('Bruce', 'Wayne', '" + idString+"', TRUE)";
                    stmt.executeUpdate(sql);

                    id = UUID.randomUUID();
                    idString = id.toString();

                    sql = "INSERT INTO Employee VALUES" +
                            "('Clark', 'Kent', '" + idString+"', TRUE)";
                    stmt.executeUpdate(sql);

                    System.out.println("TABLE FILLED");

                    stmt.close();
                    con.close();
                }
                catch (Exception ex)
                {
                    var msg = ex.getMessage();
                    System.out.println(msg);
                }
            }
        });

        deletetablebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try{
                    Connection conn = DriverManager.getConnection(AWS_URL);

                    Statement stmt = conn.createStatement();
                    stmt.execute("DROP TABLE Employee");
                    stmt.close();
                    conn.close();
                    System.out.println("TABLE DROPPED");
                }
                catch (Exception ex)
                {
                    var msg = ex.getMessage();
                    System.out.println("TABLE NOT DROPPED");
                    System.out.println(msg);
                }
            }
        });

        loaddatabutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try{
                    Connection conn = DriverManager.getConnection(AWS_URL);

                    Statement stmt = conn.createStatement();

                    String sqlStatement = "SELECT FirstName, LastName, Id, IsActive FROM Employee";
                    ResultSet result = stmt.executeQuery(sqlStatement);
                    ObservableList<Employee> dbEmployeeList = FXCollections.observableArrayList();
                    while (result.next())
                    {
                        Employee employee = new Employee();
                        employee.id = UUID.fromString(result.getString("Id"));
                        employee.firstName = result.getString("FirstName");
                        employee.lastName = result.getString("LastName");
                        employee.isActive = result.getBoolean("IsActive");

                        dbEmployeeList.add(employee);
                    }
                    employeeListView.setItems(dbEmployeeList);

                    System.out.println("DATA LOADED");

                    stmt.close();
                    conn.close();
                }
                catch (Exception ex)
                {
                    var msg = ex.getMessage();
                    System.out.println("DATA NOT LOADED");
                    System.out.println(msg);
                }
            }
        });


        ObservableList<Employee> items = employeeListView.getItems();
        //This gets selected item from list
        employeeListView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue < ? extends Worker> ov, Worker old_val, Worker new_val)->
                {

                    Worker selectedItem = employeeListView.getSelectionModel().getSelectedItem();
                    firstNameTextField.setText(((Employee)selectedItem).firstName);
                    lastNameTextField.setText(((Employee)selectedItem).lastName);
                    isActiveCheckBox.setSelected(((Employee)selectedItem).isActive);
                    btnAddNew.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Employee employee3 = new Employee();
                            employee3.firstName = firstNameTextField.getText();
                            employee3.lastName = lastNameTextField.getText();
                            employee3.isActive =isActiveCheckBox.isSelected();
                            items.add(employee3);
                        }
                    });
                    btnDeleteSelected.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            final int selectedIdx = employeeListView.getSelectionModel().getSelectedIndex();
                            if(selectedIdx != -1)
                            {
                                Employee items = employeeListView.getSelectionModel().getSelectedItem();

                                final int newSelectedIdx = (selectedIdx == employeeListView.getItems().size() - 1)
                                        ? selectedIdx - 1
                                        : selectedIdx;

                                employeeListView.getItems().remove(selectedIdx);
                                employeeListView.getSelectionModel().select(newSelectedIdx);
                            }
                        }
                    });
                    btnClear.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            firstNameTextField.clear();
                            lastNameTextField.clear();
                            isActiveCheckBox.setSelected(false);
                        }
                    });
                });

        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        employee1.firstName = "Robert";
        employee1.lastName = "Smith";
        employee2.firstName = "Lisa";
        employee2.lastName = "Smith";

        items.add(employee1);
        items.add(employee2);

        for(int i = 0; i < 10; i++)
        {
            Employee employee = new Employee();
            employee.firstName = "Generic" + i;
            employee.lastName = "Employee" + " " + i;
            employee.hire();
            items.add(employee);
        }

        Staff staff1 = new Staff();
        staff1.firstName = "StaffPerson";
        staff1.lastName = "GoodWorker";

        Faculty faculty1 = new Faculty();
        faculty1.firstName = "FacultyPerson";
        faculty1.lastName = "TerribleWorker";

        items.add(staff1);
        items.add(faculty1);
    }
}
