import java.util.ArrayList;
import java.util.List;

public class Parser {
    private boolean isDescSorting = false;
    private boolean isStringSorting = false;
    private List<String> fileNames = new ArrayList<>();

    public List<String> parse(String[] args) {

        int countOrderSorting = 0;
        int countTypeSorting = 0;

        for (String arg : args) {
            if (arg.charAt(0) == '-') {
                switch (arg) {
                    case ("-a") -> countOrderSorting++;
                    case ("-d") -> {
                        countOrderSorting++;
                        isDescSorting = true;
                    }
                    case ("-s") -> {
                        countTypeSorting++;
                        isStringSorting = true;
                    }
                    case ("-i") -> countTypeSorting++;
                    default ->
                            throw new IllegalArgumentException("Неизвестный аргумент командной строки: " + arg + "\n" +
                                                               "Для корректной работы программы допустимы следующие " +
                                                               "аргументы: " + "\n" +
                                                               "1. режим сортировки -a (по возрастанию) или -d " +
                                                               "(по убыванию)" + " - необязательный, по умолчанию " +
                                                               "сортируем по возрастанию;\n" +
                                                               "2. тип данных -s (строки, содержающие любые " +
                                                               "непробельные символы) или - i(целые числа), " +
                                                               "обязательный;\n " +
                                                               "3. имя выходного файла, обязательное;\n" +
                                                               "4. остальные параметры – имена входных файлов, " +
                                                               "не менее одного.");
                }
            } else {
//                if (arg.matches("[A-Za-z0-9-_]*[/.][a-z]*")) {
                    fileNames.add(arg);
//                } else {
//                    System.out.println("Документ с именем " + arg + " не распознан и не будет обработан!");
//                }
            }
        }

            if(fileNames.size() < 2) {
                throw new IllegalArgumentException("Не указано имя входного и/или выходного файла");
            }

            if(countOrderSorting > 1) {
                throw new IllegalArgumentException("Не указан корректный режим сортировки.\n" +
                                                   "Укажите: -a (по возрастанию) или -d (по убыванию) - аргумент " +
                                                   "не обязательный, по умолчанию сортируем по возрастанию");
            }

            if(countTypeSorting != 1) {
                throw new IllegalArgumentException("Не указан корректный тип данных.\n" +
                                                   "Укажите: -s (строки, содержающие любые непробельные символы) " +
                                                   "или - i(целые числа)");
            }

        return fileNames;

    }

    public boolean isDescSorting() {
        return isDescSorting;
    }

    public boolean isStringSorting() {
        return isStringSorting;
    }

    public List<String> getFileNames() {
        return fileNames;
    }
}
//javac Solution.java
//java Solution -s -a out.txt int1.txt int2.txt int3.txt