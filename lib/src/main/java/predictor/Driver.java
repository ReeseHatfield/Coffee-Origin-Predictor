package predictor;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main (String[] args){
        Instances data = loadCSV("lib/src/main/resources/arabica_cleaned.csv");

        //System.out.println("Data class index: " + data.classIndex());


    }

    private static Instances loadCSV (String fileName){
        Instances data = null;

        try{
            File dataSource = new File(fileName);
            CSVLoader wekaLoader = new CSVLoader();
            wekaLoader.setSource(dataSource);
            data = wekaLoader.getDataSet();
        } catch (IOException ioException){
            ioException.printStackTrace();
        }

        return data;
        // This should return as if loaded as a .arfff
    }
}
