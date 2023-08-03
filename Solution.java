import java.io.*;

public class Solution {

    public static void main(String[] args) {

        if(args.length == 0) {
            throw new IllegalArgumentException("Не указаны аргументы командной строки.\n" +
                                               "Для корректной работы программы допустимы следующие " +
                                               "аргументы: " + "\n" +
                                               "1. режим сортировки -a (по возрастанию) или -d " +
                                               "(по убыванию)" + " - необязательный, по умолчанию " +
                                               "сортируем по возрастанию;\n" +
                                               "2. тип данных -s (строки, содержающие любые " +
                                               "непробельные символы) или - i(целые числа), " +
                                               "обязательный;\n" +
                                               "3. имя выходного файла, обязательное;\n" +
                                               "4. остальные параметры - имена входных файлов, " +
                                               "не менее одного.");
        }

        try {
            SorterFile sorterFile = new SorterFile(new Parser(), args);
            System.out.println();
            sorterFile.MergeSort(sorterFile.getReaders());
            System.out.println("Сортировка слиянием выполнена");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

//javac Solution.java
//java Solution -i -a out.txt int1.txt int2.txt int3.txt