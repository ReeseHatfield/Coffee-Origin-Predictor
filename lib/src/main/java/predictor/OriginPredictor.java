package predictor;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.Arrays;
import java.util.Scanner;

public class OriginPredictor {
    private String[] attributes;
    private Instances data;

    private J48 decTree;

    private Instances train;
    private Instances test;
    public OriginPredictor(Instances trainingData, String[] attributes) throws Exception {
        this.attributes = attributes;
        this.data = trainingData;

        //set country of origin to class index
        String className = "Country.of.Origin";
        int classIdx = trainingData.attribute(className).index();
        trainingData.setClassIndex(classIdx);

        for (int i = trainingData.numAttributes() - 1; i >= 0; i --){
            String currentName = trainingData.attribute(i).name();
            if (!currentName.equals(className) && !Arrays.asList(attributes).contains(currentName)) {
                trainingData.deleteAttributeAt(i);
            }
        }
    }

    public Instance gatherUserInput() {
        Scanner scanner = new Scanner(System.in);
        Instance newInstance = new DenseInstance(this.data.numAttributes());
        newInstance.setDataset(this.data);

        for (int i = 0; i < attributes.length; i++) {
            String currentAttribute = attributes[i];

            System.out.print("Please enter the value (6.0 - 10.0) for " + currentAttribute  + ": ");
            double value = 0;
            if(scanner.hasNextDouble()){
                value = scanner.nextDouble();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number between 6.0 - 10.0");
                scanner.next();  // clear input buffer

                //restart current iteration
                i--;
                continue;
            }

            if(6.0 > value || value > 10.0){
                System.out.println("Error: value must be in range of 6.0 - 10.0");

                //restart current iteration
                i--;
                continue;
            }
            newInstance.setValue(data.attribute(currentAttribute), value);
        }

        scanner.close();
        return newInstance;

    }
    public void train() throws Exception {
        double percentToTest = 0.0;
        // this value reflects the percentage of training data instances the
        // model should test should use to test its accuracy. As it currently
        // stands, the models has ~ 81 % accuracy on given tests, regardless of test percentage

        int trainSize = (int) Math.round(this.data.numInstances() * (1 - percentToTest));
        int testSize = this.data.numInstances() - trainSize;

        this.train = new Instances(this.data, 0, trainSize);
        this.test = new Instances(this.data, trainSize, testSize);

        //create dec tree and tweak hyper parameters
        this.decTree = new J48();
        String[] options = new String[4];
        options[0] = "-C";
        options[1] = "0.20"; // confidence factor, lower values incur more pruning
        options[2] = "-M";
        options[3] = "1"; // minimum number of instances per leaf

        // apply options and build classifier tree
        this.decTree.setOptions(options);
        this.decTree.buildClassifier(train);
    }
    public void evaluate() throws Exception {
        //evaluate model
        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(decTree, test);
        System.out.println(eval.toSummaryString("\nResults\n======\n", false));
    }

    public String predict(Instance userGivenInstance) throws Exception {
        double classIndex = decTree.classifyInstance(userGivenInstance);
        String predictedClass = userGivenInstance.classAttribute().value( (int) classIndex);

        return predictedClass;
    }
}
