public abstract class CustomThread extends Thread {
    private boolean pause = false;

    @Override
    public void run() {
        if (!pause) {
            reallyRun();
        }
    }

    protected abstract void reallyRun();

    private void stopTask() {
        pause = true;
    }


}
