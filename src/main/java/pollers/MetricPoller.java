package pollers;

import org.hyperic.sigar.Sigar;

/**
 * Created by mitchellmohorovich on 2016-01-23.
 */
public abstract class MetricPoller {
	protected Sigar sigar;
	MetricPoller() {
		sigar = new Sigar();
	}
}
