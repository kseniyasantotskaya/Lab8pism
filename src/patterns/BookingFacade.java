package patterns;

import entities.Record;
import models.Client;
import models.User;

public class BookingFacade {
    public static Record makeReservation(Client client, String recordTime, String pointA, String pointB) {
        RecordBuilder builder = new RecordBuilder();
        Record record = builder.setClient(client)
                .setRecordTime(recordTime).setRecordPointA(pointA).setRecordPointB(pointB)
                .build();
        // Дополнительные операции по бронированию

        return record;
    }

    public static void payOnline(Record record, String promoCode) {
        // Оплата билета онлайн с применением промокода
    }

    public static void cancelReservation(User user, Record record) {
        // Отмена бронирования
    }
}