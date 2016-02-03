package com.mohorovich.mitchell.rssb; /**
 * Created by mitchellmohorovich on 2016-01-22.
 * The main class that defines routing for the Spark framework.
 */

import com.mohorovich.mitchell.rssb.pollers.CPUPoller;
import com.mohorovich.mitchell.rssb.pollers.MemPoller;

import java.util.Arrays;

import static spark.Spark.*;

public class Main {
	public static void main(String[] args) {
		CPUPoller cpuPoller = new CPUPoller();
		cpuPoller.start();
		get("/cpu", (req, res) -> cpuPoller.getCpu());
		get("/cpu/list", (req, res) -> Arrays.deepToString(cpuPoller.getCpuList()));
		get("/cpu/percentage", (req, res) -> cpuPoller.getCpuPerc());
		get("/cpu/percentage/list", (req, res) -> Arrays.deepToString(cpuPoller.getCpuPercList()));


		MemPoller memPoller = new MemPoller();
		memPoller.start();
		get("/mem", (req, res) -> memPoller.getMem());


		get("/network", (req, res) -> "network");
		get("/network/upload", (req, res) -> "network upload");
		get("/network/download", (req, res) -> "network download");
	}
}
