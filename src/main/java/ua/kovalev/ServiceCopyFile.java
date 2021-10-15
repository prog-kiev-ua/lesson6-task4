package ua.kovalev;

import java.io.File;

public class ServiceCopyFile {
    private int countThreads;
    private int runningWorkers;
    private File folderSrc;
    private File folderDest;

    public ServiceCopyFile() {
        super();
    }

    public ServiceCopyFile(File folderSrc, File folderDest, int countThreads) {
        super();
        this.countThreads = countThreads;
        this.folderSrc = folderSrc;
        this.folderDest = folderDest;
    }

    public int getCountThreads() {
        return countThreads;
    }

    public void setCountThreads(int countThreads) {
        this.countThreads = countThreads;
    }

    public File getFolderSrc() {
        return folderSrc;
    }

    public void setFolderSrc(File folderSrc) {
        this.folderSrc = folderSrc;
    }

    public void go() {
        File files[] = folderSrc.listFiles();
        for (int i = 0; i < files.length; i++) {
            for (; runningWorkers >= countThreads; ) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            runningWorkers++;
            new Thread(new TaskCopyRunnable(files[i], folderDest, this)).start();
        }
    }

    synchronized public void stopWork() {
        runningWorkers--;
    }
}
