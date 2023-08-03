import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReaderFileInt implements ReaderFile{
    private final String fileName;
    private String previous = null;
    private String current = null;
    private Scanner scanner;
    private boolean isDescSorting = false;
    private boolean endOfFile = false;
    public ReaderFileInt(String fileName, boolean isDescSorting) throws FileNotFoundException {
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
                try {
                    Integer.parseInt(current);
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
                if (previous != null && !isSorted(Integer.parseInt(current), Integer.parseInt(previous))) {
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

    public boolean isSorted(Integer current, Integer previous) {
        if (isDescSorting) {
            return previous > current;
        } else {
            return previous < current;
        }
    }

    public boolean isSortedLinesOtherReader(ReaderFile other) {
        if (isDescSorting) {
            return Integer.parseInt(current) < Integer.parseInt(other.getCurrent());
        } else {
            return Integer.parseInt(current) > Integer.parseInt(other.getCurrent());
        }
    }

    @Override
    public String getCurrent() {
        return "" + current;
    }

    public boolean isEndOfFile() {
        return endOfFile;
    }
}
