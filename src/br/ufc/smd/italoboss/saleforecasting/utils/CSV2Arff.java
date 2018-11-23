package br.ufc.smd.italoboss.saleforecasting.utils;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author italoboss
 */
public class CSV2Arff {

//  public static void main(String[] args) throws Exception {
//      ByteInputStream output = CSV2Arff.convert(new File("C:\\Users\\italo_000\\Desktop\\test.csv"));
//      System.out.println(output);
//  }
    public static ByteInputStream[] convert(File csv) throws IOException {
        // load CSV
        CSVLoader loader = new CSVLoader();
        loader.setSource(csv);
        Instances data = loader.getDataSet();
        
        int trainSize = data.numInstances() * 95/100; // 95%
        int testSize = data.numInstances() - trainSize;  // 5%
        data.randomize(new java.util.Random());
        
        Instances train = new Instances(data, 0, trainSize);
        Instances test = new Instances(data, trainSize, testSize);
        
        DataSets dataSets = new DataSets();
        
        // Save ARFF to Train
        ArffSaver saver = new ArffSaver();
        saver.setInstances(train);
        saver.setDestination(dataSets.trainDataSet);
        saver.writeBatch();
        // Save ARFF to Test
        saver = new ArffSaver();
        saver.setInstances(test);
        saver.setDestination(dataSets.testDataSet);
        saver.writeBatch();
        
        ByteInputStream[] response = { 
            dataSets.trainDataSet.newInputStream(),
            dataSets.testDataSet.newInputStream()
        };
        return response;
    }
    
    public static class DataSets {
        private ByteOutputStream trainDataSet;
        private ByteOutputStream testDataSet;

        public DataSets() {
            trainDataSet = new ByteOutputStream();
            testDataSet = new ByteOutputStream();
        }
    }
    
}
