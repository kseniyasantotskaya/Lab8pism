package patterns;

import entities.Record;

public abstract class RecordProcessingTemplate {
    public final void processRecord(Record record) {
        checkRecord(record);
        performProcessing(record);
        finalizeProcessing(record);
    }

    protected abstract void performProcessing(Record record);

    protected void finalizeProcessing(Record record) {
        // Общие операции по завершению обработки записи
    }

    private void checkRecord(Record record) {
        // Проверка записи перед обработкой
    }
}