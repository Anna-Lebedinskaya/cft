import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class SorterFile {
    private Parser parser;
    private Writer writer;
    private List<ReaderFile> readers = new ArrayList<>();

    public SorterFile(Parser parser, String[] args) throws IOException {
        this.parser = parser;
        parser.parse(args);
        writer = new FileWriter(parser.getFileNames().get(0));

        List<String> fileNames = parser.getFileNames().subList(1, parser.getFileNames().size());

        for (String fileName : fileNames) {
            if (parser.isStringSorting()) {
                readers.add(new ReaderFileString(fileName, parser.isDescSorting()));
            } else {
                readers.add(new ReaderFileInt(fileName, parser.isDescSorting()));
            }
        }
    }

    public void MergeSort(List<ReaderFile> readers) throws IOException {
        while (!readers.isEmpty()) {
            int indexFileMinElement = 0;
            for (int i = 1; i < readers.size() && indexFileMinElement < readers.size(); i++) {
                if (readers.get(indexFileMinElement).
                        isSortedLinesOtherReader(readers.get(i))) {
                    indexFileMinElement = i;
                }
            }
            String str = readers.get(indexFileMinElement).getCurrent() + "\n";
            writer.write(str);

            readers.get(indexFileMinElement).setNext();

            if (readers.get(indexFileMinElement).isEndOfFile()) {
                readers.remove(indexFileMinElement);
            }

        }
        writer.close();
    }

    public List<ReaderFile> getReaders() {
        return readers;
    }
}
