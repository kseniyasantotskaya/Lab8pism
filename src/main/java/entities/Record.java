package entities;
import models.Client;

public class Record {
    private static int nextRecordID = 1;

    private int recordID;
    private Client client;
    private String recordTime;
    private String pointA;
    private String pointB;

    public Record() {
        this.recordID = nextRecordID++;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public int getRecordID() {
        return recordID;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public void setRecordPointA(String pointA) {
        this.pointA = pointA;
    }

    public void setRecordPointB(String pointB) {
        this.pointB = pointB;
    }

    public String getPointA() {
        return pointA;
    }

    public String getPointB() {
        return pointB;
    }
}