package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PatternsTestSuite {
    // Паттерн поведения: Посетитель (Visitor)
    @Test
    void visitorPatternTest() {
        patterns.Visitor visitor = new patterns.ReviewVisitor();
        models.User user = new models.Client("Марина", "Иванова", 123456789, "marinka123", "123456");

        assertDoesNotThrow(() -> user.accept(visitor));
    }

    // Структурный паттерн: Фасад (Facade)
    @Test
    void facadePatternTest() {
        entities.Application.registerUser("client1", "client1Pass", false, false);
        models.User client = entities.Application.getUser("client1");

        entities.Record record = patterns.BookingFacade.makeReservation((models.Client) client, "2023-01-01 10:00", "Борисов", "Брест");

        assertNotNull(record);
    }

    // Паттерн порождающий: Фабрика (Factory)
    @Test
    void factoryPatternTest() {
        models.User admin = patterns.UserFactory.createAdministrator("admin", "adminPass");

        assertNotNull(admin);
        assertTrue(admin.isAdmin());
    }

    // Параметризированный тест
    @ParameterizedTest
    @ValueSource(strings = {"PROMO123", "DISCOUNT456"})
    void facadePatternWithPromoCodeTest(String promoCode) {
        entities.Application.registerUser("client2", "client2Pass", false, false);
        models.User client = entities.Application.getUser("client2");

        entities.Record record = patterns.BookingFacade.makeReservation((models.Client) client, "2023-01-01 12:00", "Минск", "Лида");
        assertNotNull(record);

        assertDoesNotThrow(() -> patterns.BookingFacade.payOnline(record, promoCode));
    }

    // Запускается по умолчанию
    @Test
    void recordBuilderPatternTest() {
        patterns.RecordBuilder builder = new patterns.RecordBuilder();
        entities.Record record = builder.setClient(new models.Client("Алиса", "Петрова", 987654321, "alice", "clientPass"))
                .setRecordTime("2023-01-02 15:30")
                .build();

        assertNotNull(record);
    }

    // Игнорируется
    @Test
    @org.junit.jupiter.api.Disabled
    void disabledTest() {
        // Тест, который игнорируется
    }

    // Заваливается
    @Test
    void failingTest() {
        fail("Это заведомо провальный тест.");
    }

    // С обработкой исключений
    @Test
    void exceptionHandlingTest() {
        entities.Application.registerUser("client3", "client3Pass", false, false);
        models.User client = entities.Application.getUser("client3");

        // Добавим резервацию через метод reserveRecord
        entities.Record record = patterns.BookingFacade.makeReservation((models.Client) client, "2023-01-01 08:00", "Минск", "Лида");
        ((models.Client) client).reserveRecord(record);

        // Попытка повторного бронирования на то же время
        assertThrows(IllegalStateException.class, () -> ((models.Client) client).reserveRecord(record));
    }
}