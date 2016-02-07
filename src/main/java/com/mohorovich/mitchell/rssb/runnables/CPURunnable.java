package com.mohorovich.mitchell.rssb.runnables;

import org.hyperic.sigar.SigarException;
import com.mohorovich.mitchell.rssb.pollers.CPUPoller;

import java.util.Date;

/**
 * Created by mitchellmohorovich on 2016-01-23.
 * A Runnable sub class that polls for all CPU data.
 */
public class CPURunnable extends MetricRunnable {

	private CPUPoller cpuPoller;

	public CPURunnable(CPUPoller cpuPoller) {
		super(cpuPoller);
		this.cpuPoller = cpuPoller;
	}

	protected void pollAllMetrics() {
		pollCpu();
		pollCpuList();
		pollCpuPerc();
		pollCpuPercList();
	}

	private void pollCpu() {
		try {
			this.cpuPoller.setCpu(sigar.getCpu());
		} catch (SigarException e) {
			this.cpuPoller.setCpuPerc(null);
		}
	}

	private void pollCpuList() {
		try {
			this.cpuPoller.setCpuList(sigar.getCpuList());
		} catch (SigarException e) {
			e.printStackTrace();
		}
	}

	private void pollCpuPerc() {
		try {
			this.cpuPoller.setCpuPerc(sigar.getCpuPerc());
		} catch (SigarException e) {
			this.cpuPoller.setCpuPerc(null);
		}
	}

	private void pollCpuPercList() {
		try {
			this.cpuPoller.setCpuPercList(sigar.getCpuPercList());
		} catch (SigarException e) {
			e.printStackTrace();
		}
	}

}
