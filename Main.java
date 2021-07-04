import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main {

    static List<Centroid> clusters = new ArrayList<>();
    static List<Record> data = new ArrayList<>();
    static Map<Centroid, List<Record>> clusterRecords = new HashMap<>();
    static double e = 0;
    static double e2 = 0;

    public static void main(String[] args) {

        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);
        String line;

        String parameter = JOptionPane.showInputDialog(jFrame, "Podaj wartość parametru k");
        int k = Integer.parseInt(parameter);
        String clusterData = JOptionPane.showInputDialog(jFrame, "Podaj nazwę pliku, zawierającego dane do grupowania");

        try {
            FileReader fileReader = new FileReader(clusterData);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine())!= null && (!line.equals(""))){
                generateRecord(line);
            }

        } catch (FileNotFoundException ex) {
            System.err.println("Nie znaleziono takiego pliku");
        } catch (IOException ex) {
            System.err.println("Problem wejścia/wyjścia");
        }

        initializeClusterAndCentroid(k);
        printRecordInformation();
        printClusterInformation();
    }

    static public void generateRecord(String line){
        String [] splitted = line.split(";");
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < splitted.length; i++){
            list.add(Double.parseDouble(splitted[i]));
        }
        Record record = new Record(list);
        data.add(record);
    }

    private static void initializeCluster(int clusterNumber, Record record) {
        Centroid centroid = new Centroid(clusterNumber, record.getAttributesList());
        clusters.add(centroid);
        List<Record> clusterRecord = new ArrayList<>();
        clusterRecord.add(record);
        clusterRecords.put(centroid, clusterRecord);
    }

    static void initializeClusterAndCentroid(int clusterNumber){


        int counter = 1;
        Iterator<Record> iterator = data.iterator();
        Record record;

        while (iterator.hasNext()){
            record = iterator.next();
            if (counter <= clusterNumber){
                record.setClusterNumber(counter);
                initializeCluster(counter, record);
                counter++;
            }else{
                System.out.println(record);
                System.out.println("-=- Cluster information -=-");
                for (Centroid centroid: clusters){
                    System.out.println(centroid);
                }
                System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                System.out.println();

                do{
                    e = e2;
                    e2 = 0;
                    double minDistance = Double.MAX_VALUE;
                    Centroid whichCluster = null;

                    for (Centroid centroid : clusters) {
                        double distance = centroid.calculateDistance(record);
                        if (minDistance > distance) {
                            minDistance = distance;
                            whichCluster = centroid;
                            e2 += distance;
                        }
                    }
                    record.setClusterNumber(whichCluster.getClusterNumber());
                    whichCluster.updateCentroid(record);
                    clusterRecords.get(whichCluster).add(record);
                }while (e != e2);
            }

            System.out.println("-=- Cluster information -=-");
            for (Centroid centroid: clusters){
                System.out.println(centroid);
            }
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println();
        }
    }

    static void printRecordInformation(){
        System.out.println("-=- Each record information -=-");
        for(Record record: data){
            System.out.println(record);
        }
        System.out.println();
    }

    static void printClusterInformation(){
        System.out.println("-=- Final cluster information -=-");
        for (Map.Entry<Centroid, List<Record>> entry:clusterRecords.entrySet()){
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
}
