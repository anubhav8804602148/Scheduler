abstract class Monitor {
    private int granularity;

    Monitor() throws GranularityZeroException,InterruptedException{
        start();
        while(true){
            try{
                run();
            }
            catch(BreakMonitorRunException breakMonitorRunException){
               break;
            }
            Thread.sleep(1000);
        }
        stop();

    }

    public void setGranularity(int time_gap){
        this.granularity = time_gap;
    }

    abstract void start();
    abstract void stop();
    abstract void run() throws BreakMonitorRunException;
}
