package sample;

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

import javax.swing.text.View;
import java.net.URL;
import java.util.ResourceBundle;

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




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
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
