package com.mohorovich.mitchell.rssb.pollers;

import com.mohorovich.mitchell.rssb.runnables.NetRunnable;

/**
 * Created by mitchellmohorovich on 2016-02-06.
 */
public class NetPoller extends MetricPoller {
	public NetPoller() {
		super();
		this.thread = new Thread(new NetRunnable(this));
	}
}
