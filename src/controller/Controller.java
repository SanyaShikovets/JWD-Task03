package controller;

import model.Element;

import java.util.List;

public class Controller {

    DataReader dataReader = new DataReader();
    List<String> data = dataReader.read();

    DataFilter dataFilter = new DataFilter();

    public static void main(String[] args){
        Controller controller = new Controller();
        Element rootElement = controller.dataFilter.filter(controller.data);
        System.out.println(rootElement);
    }
}
