package com.mohorovich.mitchell.rssb.pollers;

import org.hyperic.sigar.Sigar;

import java.util.Date;

/**
 * Created by mitchellmohorovich on 2016-01-23.
 * A MetricPoller is an abstract class that defines variables that are used
 * by all subclasses.
 *
 * A MetricPoller is just a wrapper class around a sigar, and uses a thread
 * so that data polling is not tied to incoming requests.
 */
public abstract class MetricPoller {

	protected Thread thread;
	protected Sigar sigar;
	protected Date retrieveTime;

	public MetricPoller() {
		this.sigar = new Sigar();
	}

	public void start() {
		thread.start();
	}

	public void setRetrieveTime(Date retrieveTime) {
		this.retrieveTime = retrieveTime;
	}

	public Date getRetrieveTime() {
		return retrieveTime;
	}
}

