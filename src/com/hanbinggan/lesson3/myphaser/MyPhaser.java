package com.hanbinggan.lesson3.myphaser;

import java.util.concurrent.Phaser;

/**
 * Created by hello on 2016/4/24.
 * onAdvance() 方法 需传入两个 int 型传入参数：当前的阶段数和注册的参与者数量，返回boolean值
 * 返回 false 则 phaser 继续执行，返回true 则表示 phaser 已经完成执行并且进入了终止态
 * phaser 对象进行阶段的时候在所有在 arriveAndAwaitAdvance() 方法里休眠的线程被唤醒之前，
 * onAdvance()方法将被自动调用
 * 如果返回值是 true，则 phaser 仍然唤醒等待的线程，但状态已经变成终止态了，继续调用 phaser 的方法将立即返回
 */
public class MyPhaser extends Phaser {
    @Override
    protected boolean onAdvance(int phase,int registerdParties){
        switch (phase){
            case 0:
                return studentsArrived();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecondExercise();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }
    private boolean studentsArrived(){
        System.out.printf("Phaser: The exam are going to start. The students are ready.\n");
        System.out.printf("Phaser: We have %d students.\n",getRegisteredParties());
        return false;
    }
    private boolean finishFirstExercise(){
        System.out.printf("Phaser: All the student have finished the first exercise.\n");
        System.out.printf("Phaser: It's time for the second one.\n");
        return false;
    }
    private boolean finishSecondExercise(){
        System.out.printf("Phaser:All the student have finished the second exercise.\n");
        System.out.printf("Phaser: It's time for the third one.\n");
        return false;
    }
    private boolean finishExam(){
        System.out.printf("Phaser: All the student have finished the exam.\n");
        System.out.printf("Phaser: Thank you for your time.\n");
        return true;
    }
}
