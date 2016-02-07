package com.mohorovich.mitchell.rssb.runnables;

import com.mohorovich.mitchell.rssb.pollers.MetricPoller;
import com.mohorovich.mitchell.rssb.pollers.NetPoller;

/**
 * Created by mitchellmohorovich on 2016-02-06.
 */
public class NetRunnable extends MetricRunnable {

	private NetPoller netPoller;

	public NetRunnable(NetPoller netPoller) {
		super(netPoller);
		this.netPoller = netPoller;
	}

	@Override
	protected void pollAllMetrics() {
	}

}
