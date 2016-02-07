package com.mohorovich.mitchell.rssb.runnables;

import org.hyperic.sigar.SigarException;
import com.mohorovich.mitchell.rssb.pollers.MemPoller;

/**
 * Created by mitchellmohorovich on 2016-01-24.
 * A runnable subclass that polls for memory metrics.
 */
public class MemRunnable extends MetricRunnable {

	private MemPoller memPoller;

	public MemRunnable(MemPoller memPoller) {
		super(memPoller);
		this.memPoller = memPoller;
	}

	@Override
	protected void pollAllMetrics() {
		pollMem();
	}

	private void pollMem() {
		try {
			this.memPoller.setMem(this.sigar.getMem());
		} catch (SigarException e) {
			e.printStackTrace();
		}
	}

}
