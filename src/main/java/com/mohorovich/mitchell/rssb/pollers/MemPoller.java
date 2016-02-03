package com.mohorovich.mitchell.rssb.pollers;

import org.hyperic.sigar.Mem;
import com.mohorovich.mitchell.rssb.runnables.MemRunnable;

/**
 * Created by mitchellmohorovich on 2016-01-23.
 * A MetricPoller whose metric is Memory information.
 */
public class MemPoller extends MetricPoller {

	private volatile Mem mem;

	public MemPoller() {
		super();
		this.thread = new Thread(new MemRunnable(this));
	}

	public void setMem(Mem mem) {
		this.mem = mem;
	}

	public Mem getMem() {
		return this.mem;
	}
}
