package GUI;

import java.util.ArrayList;
import java.util.List;

public class Record {

    List<Double> attributesList = new ArrayList<>();
    int clusterNumber;

    public Record(List<Double> attributesList) {
        this.attributesList = attributesList;
    }

    public List<Double> getAttributesList() {
        return attributesList;
    }

    public void setAttributesList(List<Double> attributesList) {
        this.attributesList = attributesList;
    }

    public int getClusterNumber() {
        return clusterNumber;
    }

    public void setClusterNumber(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }

    @Override
    public String toString() {
        return "Record{" +
                "attributesList=" + attributesList +
                ", clusterNumber=" + clusterNumber +
                '}';
    }
}