package com.hanbinggan.lesson5.asyn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by hello on 2016/5/1.
 * 也可以使用 get()方法完成 等待任务 获取结果
 * get() 如果 ForkJoinTask 类执行结束，或者一直等到结束，get() 方法的版本将有 compute() 方法返回的结果
 * get(long timeout,TimeUnit unit) 指定时间内返回
 * join() 方法不能被中断，否则抛出 InterruptException 异常
 * 如果任务抛出任何异常 get() 方法将抛出 ExecutionException 异常 ，但是join() 方法将 返回 RuntimeException 异常
 */
public class FolderProcessor extends RecursiveTask<List<String>> {
    private static final long serialVersionUID=1L;
    private String path;
    private String extension;

    public FolderProcessor(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    @Override
    protected List<String> compute() {
        List<String>list=new ArrayList<>();
        List<FolderProcessor>tasks=new ArrayList<>();
        File file=new File(path);
        File content[]=file.listFiles();
        if(content!=null){
            for(int i=0;i<content.length;i++){
                if(content[i].isDirectory()){
                    FolderProcessor task=new FolderProcessor(content[i].getAbsolutePath(),extension);
                    /**
                     * 调用 fork() 方法把新对象发送到线程池中。fork() 方法发送任务到线程池时，如果线程池有空闲的工作者线程
                     * 或者创建一个新的线程，开始执行这个任务
                     * fork() 任务将立即返回
                     * 主任务将继续处理文件夹中的其他内容
                     * 一旦主任务处理完指定文件夹的所有内容，将调用 join() 方法等待发送到线程池中的所有子任务执行完成
                     * join() 方法在主任务中被调用，然后等待任务执行结束，并 通过 compute() 方法返回值
                     *
                     * */
                    task.fork();
                    tasks.add(task);
                }else{
                    if(checkFile(content[i].getName())){
                        list.add(content[i].getAbsolutePath());
                    }
                }
            }
        }
        if(tasks.size()>50){
            System.out.printf("%s: %d tasks ran.\n",file.getAbsoluteFile(),tasks.size());
        }
        addResultsFromTasks(list,tasks);
        return list;
    }

    private void addResultsFromTasks(List<String>list,List<FolderProcessor>tasks){
        for(FolderProcessor item:tasks){
            list.addAll(item.join());
        }
    }

    private boolean checkFile(String name){
        return name.endsWith(extension);
    }
}
