/**
 * Created by mitchellmohorovich on 2016-01-22.
 */

import pollers.CPUPoller;

import static spark.Spark.*;

public class Routes {
	public static void main(String[] args) {
		CPUPoller cpuPoller = new CPUPoller();
		cpuPoller.start();

		get("/cpu", (req, res) -> cpuPoller.getCpu());
		get("/cpu/list", (req, res) -> cpuPoller.getCpuList());
		get("/cpu/percentage", (req, res) -> cpuPoller.getCpuPerc());
		get("/cpu/percentage/list", (req, res) -> cpuPoller.getCpuPercList());

		get("/ram", (req, res) -> "current ram");

		get("/network", (req, res) -> "network");
		get("/network/upload", (req, res) -> "network upload");
		get("/network/download", (req, res) -> "network download");
	}
}
