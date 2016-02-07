package com.mohorovich.mitchell.rssb.runnables;

import com.mohorovich.mitchell.rssb.pollers.MetricPoller;
import org.hyperic.sigar.Sigar;

import java.util.Date;

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
	protected MetricPoller metricPoller;

	public MetricRunnable(MetricPoller metricPoller) {
		this.sigar = new Sigar();
		this.sleepInterval = 1000;
		this.metricPoller = metricPoller;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			pollAllMetrics();
			updateRetrieveTime();
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

	protected void updateRetrieveTime() {
		this.metricPoller.setRetrieveTime(new Date());
	}

}
