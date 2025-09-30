package com.example.cardatabase4.test11;




public class EBook extends Book{
    private String fileSize;

    public EBook(String title, String author, String fileSize) {
        super(title, author);
        this.fileSize = fileSize;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public void displayInfo() {
        System.out.println();
        super.displayInfo();
        System.out.print(", 파일 크기 :" + getFileSize());
    }
}
