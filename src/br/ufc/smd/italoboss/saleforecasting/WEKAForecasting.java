package br.ufc.smd.italoboss.saleforecasting;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import java.util.Arrays;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author italoboss
 */
public class WEKAForecasting {
    private static final String BASES_PATH = "src/br/ufc/smd/italoboss/saleforecasting/bases/";
    
    private Instances trainInstances;
    private Instances testInstances;
    
    private J48 j48;
    private NaiveBayes naiveBayes;
    private SimpleLogistic logistic;

    public void prepareTrainInstances() throws Exception {
        DataSource source = new DataSource(WEKAForecasting.BASES_PATH + "database.arff");
        Instances data = source.getDataSet();
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }
        this.trainInstances = data;
//        System.out.println(" -> Train base:\n" + this.trainInstances);
    }
    
    public void prepareTrainInstances(ByteInputStream database) throws Exception {
        DataSource source = new DataSource(database);
        Instances data = source.getDataSet();
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }
        this.trainInstances = data;
        System.out.println(" -> Train base:\n" + this.trainInstances);
    }
    
    public void prepareTestInstances() throws Exception {
        DataSource source = new DataSource(WEKAForecasting.BASES_PATH + "testbase.arff");
        Instances data = source.getDataSet();
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }
        this.testInstances = data;
//        System.out.println(" -> Teset base:\n" + this.testInstances);
    }
    
    public void prepareTestInstances(ByteInputStream database) throws Exception {
        DataSource source = new DataSource(database);
        Instances data = source.getDataSet();
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }
        this.testInstances = data;
//        System.out.println(" -> Teset base:\n" + this.testInstances);
    }
    
    public void buildJ48() throws Exception {
        this.j48 = new J48();
        this.j48.buildClassifier(this.trainInstances);
    }

    public Evaluation evaluateJ48() throws Exception {
        Evaluation eval = new Evaluation(this.trainInstances);
        eval.evaluateModel(this.j48, this.testInstances);
        System.out.println(eval.toSummaryString("\nResults [J48]\n============\n", false));
        return eval;
    }

    public double[] predictJ48(SFInstance instanceToPredict) throws Exception {
        Instance newInstance = new DenseInstance(9);
        newInstance.setDataset(this.trainInstances);
        newInstance.setValue(0, instanceToPredict.getPaymentMethod());
        newInstance.setValue(1, instanceToPredict.getType());
        newInstance.setValue(2, instanceToPredict.getDiscountValue());
        newInstance.setValue(3, instanceToPredict.getDiscountType());
        newInstance.setValue(4, instanceToPredict.getClientType());
        newInstance.setValue(5, instanceToPredict.getClientProfile());
        newInstance.setValue(6, instanceToPredict.getClientRegisterDate());
        newInstance.setValue(7, instanceToPredict.getTotalCost());
        
        double results[] = this.j48.distributionForInstance(newInstance);
        System.out.println(" - J48: ");
        System.out.println(Arrays.toString(results));
        return results;
    }
    
    public void buildNaiveBayes() throws Exception {
        this.naiveBayes = new NaiveBayes();
        this.naiveBayes.buildClassifier(this.trainInstances);
    }
    
    public Evaluation evaluateNaiveBayes() throws Exception {
        Evaluation eval = new Evaluation(this.trainInstances);
        eval.evaluateModel(this.naiveBayes, this.testInstances);
        System.out.println(eval.toSummaryString("\nResults [Naive Bayes]\n============\n", false));
        return eval;
    }

    public double[] predictNaiveBayes(SFInstance instanceToPredict) throws Exception {
        Instance newInstance = new DenseInstance(9);
        newInstance.setDataset(this.trainInstances);
        newInstance.setValue(0, instanceToPredict.getPaymentMethod());
        newInstance.setValue(1, instanceToPredict.getType());
        newInstance.setValue(2, instanceToPredict.getDiscountValue());
        newInstance.setValue(3, instanceToPredict.getDiscountType());
        newInstance.setValue(4, instanceToPredict.getClientType());
        newInstance.setValue(5, instanceToPredict.getClientProfile());
        newInstance.setValue(6, instanceToPredict.getClientRegisterDate());
        newInstance.setValue(7, instanceToPredict.getTotalCost());
        
        double results[] = this.naiveBayes.distributionForInstance(newInstance);
        System.out.println(" - Bayes: ");
        System.out.println(Arrays.toString(results));
        return results;
    }

    public void buildRL() throws Exception {
        this.logistic = new SimpleLogistic();
        this.logistic.buildClassifier(this.trainInstances);
    }

    public Evaluation evaluateRL() throws Exception {
        Evaluation eval = new Evaluation(this.trainInstances);
        eval.evaluateModel(this.logistic, this.testInstances);
        System.out.println(eval.toSummaryString("\nResults [RL]\n============\n", false));
        return eval;
    }

    public double[] predictRL(SFInstance instanceToPredict) throws Exception {
        Instance newInstance = new DenseInstance(9);
        newInstance.setDataset(this.trainInstances);
        newInstance.setValue(0, instanceToPredict.getPaymentMethod());
        newInstance.setValue(1, instanceToPredict.getType());
        newInstance.setValue(2, instanceToPredict.getDiscountValue());
        newInstance.setValue(3, instanceToPredict.getDiscountType());
        newInstance.setValue(4, instanceToPredict.getClientType());
        newInstance.setValue(5, instanceToPredict.getClientProfile());
        newInstance.setValue(6, instanceToPredict.getClientRegisterDate());
        newInstance.setValue(7, instanceToPredict.getTotalCost());
        
        double results[] = this.logistic.distributionForInstance(newInstance);
        System.out.println(" - RL: ");
        System.out.println(Arrays.toString(results));
        return results;
    }
    
    public Instances getTrainInstances() {
        return trainInstances;
    }

    public Instances getTestInstances() {
        return testInstances;
    }

    public J48 getJ48() {
        return j48;
    }

    public NaiveBayes getNaiveBayes() {
        return naiveBayes;
    }

    public SimpleLogistic getLogistic() {
        return logistic;
    }

    public static String getBASES_PATH() {
        return BASES_PATH;
    }
    
}
