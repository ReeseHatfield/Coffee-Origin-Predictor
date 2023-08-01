package predictor;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.CSVLoader;


import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main (String[] args) throws Exception{
        Instances data = loadCSV("lib/src/main/resources/augmented_data.csv");

        // Set class index to 'Country.of.Origin'
        String className = "Country.of.Origin";
        int classIdx = data.attribute(className).index();
        data.setClassIndex(classIdx);

        // find attributes to model
        String[] attributes = {"Aroma","Flavor","Aftertaste","Acidity","Body",
                "Balance","Uniformity","Clean.Cup",
                "Sweetness","Cupper.Points","Total.Cup.Points","Moisture"};
        for(int i = data.numAttributes() - 1; i >= 0; i --){
            String currentName = data.attribute(i).name();
            if (!currentName.equals(className) && !java.util.Arrays.asList(attributes).contains(currentName)) {
                data.deleteAttributeAt(i);
            }
        }

        // Split data into training and test sets
        int trainSize = (int) Math.round(data.numInstances() * 0.80);
        int testSize = data.numInstances() - trainSize;
        Instances train = new Instances(data, 0, trainSize);
        Instances test = new Instances(data, trainSize, testSize);

        // Create a new J48 classifier
        J48 decTree = new J48();
        String[] options = new String[4];
        options[0] = "-C";
        options[1] = "0.20"; // confidence factor, lower values incur more pruning
        options[2] = "-M";
        options[3] = "1"; // minimum number of instances per leaf

        // Train the classifier
        decTree.setOptions(options);
        decTree.buildClassifier(train);

        // Evaluate the classifier
        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(decTree, test);
        System.out.println(eval.toSummaryString("\nResults\n======\n", false));

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
        // This should return as if loaded as a .arfff
    }
}
