package com.hanbinggan.lesson1;

import java.io.File;

/**
 * Created by hello on 2016/4/20.
 */
public class FileSearch implements Runnable {
    private String initPath;
    private String fileName;

    public FileSearch(String initPath,String fileName) {
        this.fileName = fileName;
        this.initPath = initPath;
    }

    @Override
    public void run() {
        File file=new File(initPath);
        if(file.isDirectory()){
            try{
                directoryProcess(file);
            }catch (InterruptedException e){
                System.out.printf("%s: The search has been interrupted.\n",Thread.currentThread().getName());
            }
        }
    }
    private void directoryProcess(File file)throws InterruptedException{
        File[] lists=file.listFiles();
        if(lists !=null){
            for(File f:lists){
//                System.out.println(f.getName());
                if(f.isDirectory()){
                    directoryProcess(f);
                }else{
                    fileProcess(f);
                }
            }
        }
        if(Thread.interrupted()){
            throw new InterruptedException();
        }
    }
    private void fileProcess(File file) throws InterruptedException{
        if(file.getName().equals(file)){
            System.out.printf("%s : %s\n",Thread.currentThread().getName(),file.getAbsoluteFile());
        }
        if(Thread.interrupted()){
            throw new InterruptedException();
        }
    }
}
