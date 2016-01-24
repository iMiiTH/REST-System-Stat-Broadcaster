package pollers;

import org.hyperic.sigar.Sigar;

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

	public MetricPoller() {
		this.sigar = new Sigar();
	}

}

