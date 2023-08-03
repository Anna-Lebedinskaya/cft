import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReaderFileString implements ReaderFile {
    private final String fileName;
    private String previous = null;
    private String current = null;
    Scanner scanner;
    boolean isDescSorting = false;
    boolean endOfFile = false;

    public ReaderFileString(String fileName, boolean isDescSorting) throws FileNotFoundException {
        this.fileName = fileName;
        this.scanner = new Scanner(new File(fileName));
        this.isDescSorting = isDescSorting;
        setNext();
    }

    public void setNext() {
        previous = current;
        boolean getNext = false;
        while (!getNext && !endOfFile) {
            if (scanner.hasNextLine()) {
                current = scanner.nextLine();
                if (current.contains("\t") || current.contains("\r") || current.contains("\f")) {
                    System.out.println("Файл: " + fileName + ", строка: " + current + " - пропущена, " +
                                       "т.к. содержит непробельный символ");
                } else if (previous != null && !isSorted(current, previous)) {
                    System.out.println("Файл: " + fileName + ", строка: " + current + " - пропущена, " +
                                       "т.к. нарушен порядок сортировки");
                } else {
                    getNext = true;
                }
            } else {
                endOfFile = true;
            }
        }
    }

    public boolean isSorted(String current, String previous) {
        if (isDescSorting) {
            return current.compareTo(previous) < 0;
        } else {
            return current.compareTo(previous) > 0;
        }
    }

    public boolean isSortedLinesOtherReader(ReaderFile other) {
        if (isDescSorting) {
            return current.compareTo(other.getCurrent()) < 0;
        } else {
            return current.compareTo(other.getCurrent()) > 0;
        }
    }

    public String getCurrent() {
        return current;
    }

    public boolean isEndOfFile() {
        return endOfFile;
    }
}
