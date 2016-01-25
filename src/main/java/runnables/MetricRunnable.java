package runnables;

import org.hyperic.sigar.Sigar;

/**
 * Created by mitchellmohorovich on 2016-01-23.
 * The MetricRunnable class allows the polling of system stat data
 * to be separate from incoming requests. Each metric is run in its own
 * thread, and requests access the cached data that's updated every
 * sleepInterval.
 */
public abstract class MetricRunnable implements Runnable {

	protected Sigar sigar;
	protected long sleepInterval;

	public MetricRunnable() {
		this.sigar = new Sigar();
		this.sleepInterval = 1000;
	}

	public MetricRunnable(long sleepInterval) {
		this();
		this.sleepInterval = sleepInterval;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			pollAllMetrics();
			sleepThread();
		}
	}

	protected void sleepThread() {
		try {
			Thread.sleep(sleepInterval);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected abstract void pollAllMetrics();

}
