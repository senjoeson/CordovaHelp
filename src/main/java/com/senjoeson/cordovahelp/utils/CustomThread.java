package com.senjoeson.cordovahelp.utils;

/**
 * @author MyPC
 * @date 2018/7/19
 */

public abstract class CustomThread extends Thread {
    private boolean pause = false;

    @Override
    public void run() {
        if (!pause) {
            reallyRun();
        }
    }

    protected abstract void reallyRun();

    public void stopTask() {
        pause = true;
    }

}
