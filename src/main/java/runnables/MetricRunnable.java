package runnables;

import org.hyperic.sigar.Sigar;

/**
 * Created by mitchellmohorovich on 2016-01-23.
 */
public abstract class MetricRunnable {

	protected Sigar sigar;
	protected final long sleepInterval;

	public MetricRunnable() {
		this.sigar = new Sigar();
		sleepInterval = 0;
	}

	protected void sleepThread() {
		try {
			Thread.sleep(sleepInterval);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
