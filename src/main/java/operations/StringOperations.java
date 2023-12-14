package operations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.*;
import operations.StringListWrapper;
public class StringOperations {

    private static final int STATIC_COLLECTION_SIZE = 5;

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("apple");
        stringList.add("banana");
        stringList.add("orange");

        // 1. Добавление и удаление объектов
        stringList.add("grape");
        stringList.remove("banana");

        // 2. Поиск одинаковых элементов с подсчетом совпадений
        String searchString = "apple";
        int count = Collections.frequency(stringList, searchString);
        System.out.println("Occurrences of \"" + searchString + "\": " + count);

        // 3. Выгрузка в xml-файл
        exportToXml(stringList, "output.xml");

        // 4. Реверс всех строк, входящих в коллекцию
        Collections.reverse(stringList);
        System.out.println("Reversed List: " + stringList);

        // 5. Статистика по всем символам, содержащимся в строках коллекции
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (String str : stringList) {
            for (char c : str.toCharArray()) {
                charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            }
        }
        System.out.println("Character statistics: " + charCountMap);

        // 6. Поиск подстроки в строках коллекции
        String subString = "app";
        List<String> matchingStrings = new ArrayList<>();
        for (String str : stringList) {
            if (str.contains(subString)) {
                matchingStrings.add(str);
            }
        }
        System.out.println("Strings containing \"" + subString + "\": " + matchingStrings);

        // 7. Инициализация листа по текстовому файлу и вывод содержимого коллекции на экран
        List<String> fileLines = initializeListFromFile("input.txt");
        System.out.println("Contents of the collection from file: " + fileLines);

        // 8. Расширить функциональность класса ArrayList методом compareInnerObjects(int firstIndex, int secondIndex)
        compareInnerObjects(stringList, 0, 2);

        // 9. Посчитать длины строк входящих в коллекцию, и вывести результат в упорядоченном виде
        List<Integer> stringLengths = new ArrayList<>();
        for (String str : stringList) {
            stringLengths.add(str.length());
        }
        Collections.sort(stringLengths);
        System.out.println("Sorted string lengths: " + stringLengths);

        // 10. Реализовать возможность добавления в динамическую коллекцию
        dynamicCollectionExample();
    }

    private static void compareInnerObjects(List<String> list, int firstIndex, int secondIndex) {
        String firstString = list.get(firstIndex);
        String secondString = list.get(secondIndex);

        // Пример сравнения строк по длине
        int result = Integer.compare(firstString.length(), secondString.length());

        if (result < 0) {
            System.out.println(firstString + " is shorter than " + secondString);
        } else if (result > 0) {
            System.out.println(firstString + " is longer than " + secondString);
        } else {
            System.out.println(firstString + " has the same length as " + secondString);
        }
    }

    private static void dynamicCollectionExample() {
        List<String> dynamicCollection = new ArrayList<>();
        int currentSize = 0;

        while (currentSize < STATIC_COLLECTION_SIZE) {
            dynamicCollection.add("Item" + currentSize);
            currentSize++;

            if (currentSize == STATIC_COLLECTION_SIZE) {
                // При достижении порогового значения, добавление нового элемента вызывает удаление первого элемента коллекции
                dynamicCollection.remove(0);
            }
        }

        System.out.println("Dynamic Collection: " + dynamicCollection);
    }
    private static List<String> initializeListFromFile(String fileName) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    private static void exportToXml(List<String> stringList, String fileName) {
        try {
            // Создаем объект JAXBContext для работы с классом StringListWrapper
            JAXBContext context = JAXBContext.newInstance(StringListWrapper.class);

            // Создаем объект Marshaller для преобразования объектов в XML
            Marshaller marshaller = context.createMarshaller();

            // Устанавливаем форматирование для удобного чтения XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Создаем экземпляр StringListWrapper и устанавливаем в него список перед маршаллингом
            StringListWrapper wrapper = new StringListWrapper(stringList);

            // Маршаллинг (преобразование) StringListWrapper в XML и сохранение в файл
            marshaller.marshal(wrapper, new File(fileName));

            System.out.println("Data exported to XML file: " + fileName);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}