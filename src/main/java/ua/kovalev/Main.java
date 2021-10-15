package ua.kovalev;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ServiceCopyFile serviceCopyFile = new ServiceCopyFile(new File("folder1"), new File("folder2"), 4);
        serviceCopyFile.go();
        System.out.println((System.currentTimeMillis()-start)/1000D);
    }
}
