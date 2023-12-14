package patterns;

import entities.Record;
import models.Client;

public class RecordBuilder {
    private Record record = new Record();

    public RecordBuilder setClient(Client client) {
        record.setClient(client);
        return this;
    }

    public RecordBuilder setRecordTime(String time) {
        record.setRecordTime(time);
        return this;
    }

    public RecordBuilder setRecordPointA(String pointA) {
        record.setRecordPointA(pointA);
        return this;
    }

    public RecordBuilder setRecordPointB(String pointB) {
        record.setRecordPointB(pointB);
        return this;
    }


    public Record build() {
        return record;
    }
}