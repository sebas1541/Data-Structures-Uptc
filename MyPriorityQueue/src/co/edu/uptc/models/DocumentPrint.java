package co.edu.uptc.models;

public class DocumentPrint {
    private int numOfPages;
    private String name;
    private String extension;

    public DocumentPrint(int numOfPages, String name, String extension) {
        this.numOfPages = numOfPages;
        this.name = name;
        this.extension = extension;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "DocumentToPrint{" +
                "numOfPages=" + numOfPages +
                ", nameOfFile='" + name + '\'' +
                ", fileExtension='" + extension + '\'' +
                '}';
    }
}
