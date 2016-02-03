package com.mohorovich.mitchell.rssb; /**
 * Created by mitchellmohorovich on 2016-01-22.
 * The main class that defines routing for the Spark framework.
 */

import com.mohorovich.mitchell.rssb.pollers.CPUPoller;
import com.mohorovich.mitchell.rssb.pollers.MemPoller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static spark.Spark.*;

public class Main {
	public static void main(String[] args) throws Exception {
		writeSigarLibToJavaLibPath();

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

	public static void writeSigarLibToJavaLibPath() throws Exception {
		File libraryDirectory = new File(System.getProperty("user.dir").concat("/lib"));
		if(!libraryDirectory.exists()) {
			if(!libraryDirectory.mkdir()) {
				throw new Exception("Could not make lib directory.");
			}
		}
		System.setProperty("user.dir", System.getProperty("user.dir").concat("/lib"));
		System.setProperty("java.library.path", System.getProperty("user.dir"));

		InputStream in = Main.class.getResourceAsStream("/sigar-lib.zip");
		ZipInputStream zin = new ZipInputStream(in);
		ZipEntry ze;
		while ((ze = zin.getNextEntry()) != null) {
			System.out.println("Unzipping " + ze.getName());
			FileOutputStream fOut = new FileOutputStream(System.getProperty("java.library.path") + "/" + ze.getName());
			for (int c = zin.read(); c != -1; c = zin.read()) {
				fOut.write(c);
			}
			zin.closeEntry();
			fOut.close();
		}
		zin.close();
	}

}
