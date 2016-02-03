package com.mohorovich.mitchell.rssb.pollers;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuPerc;
import com.mohorovich.mitchell.rssb.runnables.CPURunnable;

/**
 * Created by mitchellmohorovich on 2016-01-23.
 * A MetricPoller whose metric is CPU Data.
 */
public class CPUPoller extends MetricPoller {

	private volatile Cpu cpu;
	private volatile Cpu[] cpuList;
	private volatile CpuPerc cpuPerc;
	private volatile CpuPerc[] cpuPercList;

	public CPUPoller() {
		super();
		this.thread = new Thread(new CPURunnable(this));
	}

	public Cpu getCpu() {
		return cpu;
	}

	public void setCpu(Cpu cpu) {
		this.cpu = cpu;
	}

	public Cpu[] getCpuList() {
		return cpuList;
	}

	public void setCpuList(Cpu[] cpuList) {
		this.cpuList = cpuList;
	}

	public CpuPerc getCpuPerc() {
		return cpuPerc;
	}

	public void setCpuPerc(CpuPerc cpuPerc) {
		this.cpuPerc = cpuPerc;
	}

	public CpuPerc[] getCpuPercList() {
		return cpuPercList;
	}

	public void setCpuPercList(CpuPerc[] cpuPercList) {
		this.cpuPercList = cpuPercList;
	}
}
