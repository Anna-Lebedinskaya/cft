public interface ReaderFile {
    public void setNext();
    public String getCurrent();
    public boolean isSortedLinesOtherReader(ReaderFile other);

    public boolean isEndOfFile();
}
