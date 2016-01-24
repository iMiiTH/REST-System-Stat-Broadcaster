package pollers;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuPerc;
import runnables.CPURunnable;

/**
 * Created by mitchellmohorovich on 2016-01-23.
 */
public class CPUPoller extends MetricPoller {
	private final Thread thread;

	private volatile Cpu cpu;
	private volatile Cpu[] cpuList;
	private volatile CpuPerc cpuPerc;
	private volatile CpuPerc[] cpuPercList;

	public CPUPoller() {
		super();
		thread = new Thread(new CPURunnable(this));
	}

	/**
	 * Start the threads that poll sigar for data every 1 second.
	 */
	public void start() {
		thread.start();
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
