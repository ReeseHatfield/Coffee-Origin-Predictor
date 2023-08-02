package predictor;

import predictor.utils.SystemUtils;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main (String[] args) throws Exception {
        SystemUtils.suppressBLASandNETLIBWarnings();

        Instances data = loadCSV("lib/src/main/resources/augmented_data.csv");

        String[] attributes = {
                "Aroma","Flavor", "Aftertaste",
                "Acidity", "Body", "Balance",
                "Uniformity", "Clean.Cup", "Sweetness"};

        OriginPredictor predictor = new OriginPredictor(data, attributes);
        predictor.train();
        predictor.evaluate();
        Instance userGivenInstance = predictor.gatherUserInput();
        String predictedCountryOfOrigin = predictor.predict(userGivenInstance);

        System.out.println("Predicted Country of Origin: " + predictedCountryOfOrigin);

    }

    private static Instances loadCSV (String fileName){
        Instances data = null;

        try{
            File dataSource = new File(fileName);
            CSVLoader wekaLoader = new CSVLoader();
            wekaLoader.setSource(dataSource);
            data = wekaLoader.getDataSet();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return data;
        // This should return as if loaded as a .arff
    }


}
