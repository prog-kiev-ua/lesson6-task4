package ua.kovalev;

import java.io.*;

public class TaskCopyRunnable implements Runnable {
    private File fileSrc;
    private File folderDest;
    private ServiceCopyFile serviceCopyFile;

    public TaskCopyRunnable(File file, File folderDest, ServiceCopyFile serviceCopyFile) {
        this.fileSrc = file;
        this.folderDest = folderDest;
        this.serviceCopyFile = serviceCopyFile;
    }

    @Override
    public void run() {
        File fileDest = new File(folderDest, fileSrc.getName());
        copyFile(fileSrc, fileDest);
        serviceCopyFile.stopWork();
    }

    private static void copyFile(File fileSrc, File fileDest) {
        try (InputStream is = new FileInputStream(fileSrc);
             OutputStream os = new FileOutputStream(fileDest)) {
            is.transferTo(os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
