package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    private final String PATH = "src\\resources\\example.xml";

    public List<String> read(){

        List<String> data = new ArrayList<>();
        File file = new File(PATH);

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {// ошибка кодирования, продумаю, что случится при негативном сценарии (когда исключение случится)
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(fileReader);

        try {
            String line = reader.readLine();
            while (line != null) {
                if(!line.isEmpty())
                    data.add(line.substring(line.indexOf("<")));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return data;
    }
}
