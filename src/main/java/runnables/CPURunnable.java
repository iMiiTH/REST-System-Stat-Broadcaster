package runnables;

import org.hyperic.sigar.SigarException;
import pollers.CPUPoller;

/**
 * Created by mitchellmohorovich on 2016-01-23.
 */
public class CPURunnable extends MetricRunnable implements Runnable {

	private CPUPoller cpuPoller;

	public CPURunnable(CPUPoller cpuPoller) {
		super();
		this.cpuPoller = cpuPoller;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			pollAllCpuMetrics();
			sleepThread();
		}
	}

	private void pollAllCpuMetrics() {
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
