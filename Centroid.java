package GUI;

import java.util.ArrayList;
import java.util.List;

public class Centroid {

    List<Double> centroidAttributesList;
    int clusterNumber;

    public Centroid(int clusterNumber, List<Double> centroidAttributesList) {
        this.clusterNumber = clusterNumber;
        this.centroidAttributesList = centroidAttributesList;
    }

    public List<Double> getCentroidAttributesList() {
        return centroidAttributesList;
    }

    public void setCentroidAttributesList(List<Double> centroidAttributesList) {
        this.centroidAttributesList = centroidAttributesList;
    }

    public int getClusterNumber() {
        return clusterNumber;
    }

    public void setClusterNumber(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }

    @Override
    public String toString() {
        return "Centroid{" +
                "centroidAttributesList=" + centroidAttributesList +
                ", clusterNumber=" + clusterNumber +
                '}';
    }

    public double calculateDistance(Record record){
        double distanceSquare = 0;
        for (int i = 0; i < centroidAttributesList.size(); i++){
            distanceSquare += Math.pow(centroidAttributesList.get(i) - record.attributesList.get(i), 2);
        }
        return Math.sqrt(distanceSquare);
    }

    public void updateCentroid(Record record){
        for (int i = 0; i < centroidAttributesList.size(); i++){
            centroidAttributesList.set(i,(centroidAttributesList.get(i) + record.attributesList.get(i))/2);
        }
    }
}