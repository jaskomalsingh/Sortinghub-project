package se2203.assignment1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class SortingHubController {

    private int[] intArray;

    private SortingStrategy sortingMethod;

    @FXML
    private ComboBox<String> ComboBox;

    @FXML
    private Button Resetbtn;


    @FXML
    private Button Sortbtn;


    @FXML
    private Slider slider;

    @FXML
    private Label emptylabel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    void Detected(MouseEvent event){
        emptylabel.setText(String.format("%.0f",slider.getValue()));
        intArray =  Arrayrandomizer((int)slider.getValue());
        updateGraph();
    }
    @FXML
    void setSortingStrategy() {
        String method = ComboBox.getValue();
        if(method == "Merge Sort"){
            sortingMethod = new MergeSort(intArray,this);
        }
        else if (method == "Selection Sort"){
            sortingMethod = new SelectionSort(intArray, this);
        }
    }


    public void initialize() {
        ComboBox.getItems().clear();
        String[] strArray = {"Merge Sort", "Selection Sort"};


        ObservableList<String> strList = FXCollections.observableArrayList(strArray);

        ComboBox.getItems().addAll(strList);
        ComboBox.getSelectionModel().selectFirst();
        intArray =  Arrayrandomizer((int)slider.getValue());
        emptylabel.setText(String.format("%.0f",slider.getValue()));
        updateGraph();



    }

    public void updateGraph(){
        anchorPane.getChildren().clear();
        int gapWidth = 1;
        //intArray =  Arrayrandomizer((int)slider.getValue());
        emptylabel.setText(String.format("%.0f",slider.getValue()));
        double width = (anchorPane.getPrefWidth() - intArray.length * gapWidth)/intArray.length;
        double height = anchorPane.getPrefHeight();
        int maximum = Arrays.stream(intArray).max().getAsInt();
        for(int i = 0; i < intArray.length; i++){
            Rectangle rectangle = new Rectangle(width, (intArray[i]/(double)maximum * height) -1);
            rectangle.setX(i * (width + gapWidth) + gapWidth);
            rectangle.setY(height - rectangle.getHeight());
            rectangle.setFill(Color.RED);
            anchorPane.getChildren().add(rectangle);
        }

    }
    public int[] Arrayrandomizer(int value){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i <= value; i++){
            list.add(i);
        }
        Collections.shuffle(list, new Random());
        intArray = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            intArray[i] = list.get(i);
        }
        return intArray;

    }
    public void run(){
        setSortingStrategy();
        Thread thread1 = new Thread(sortingMethod);
        thread1.start();
    }

}

