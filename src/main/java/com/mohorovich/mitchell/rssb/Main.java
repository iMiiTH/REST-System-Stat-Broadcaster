package com.mohorovich.mitchell.rssb; /**
 * Created by mitchellmohorovich on 2016-01-22.
 * The main class that defines routing for the Spark framework.
 */

import com.mohorovich.mitchell.rssb.XMLmetric.XMLCPU;
import com.mohorovich.mitchell.rssb.XMLmetric.XMLMem;
import com.mohorovich.mitchell.rssb.pollers.CPUPoller;
import com.mohorovich.mitchell.rssb.pollers.MemPoller;

import java.io.*;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static spark.Spark.*;

public class Main {
	public static void main(String[] args) throws Exception {
		writeSigarLibToJavaLibPath();

		CPUPoller cpuPoller = new CPUPoller();
		cpuPoller.start();
		XMLCPU xmlcpu = new XMLCPU(cpuPoller);
		get("/cpu", (req, res) -> cpuPoller.getCpu());
		get("/cpu/list", (req, res) -> Arrays.deepToString(cpuPoller.getCpuList()));
		get("/cpu/percentage", (req, res) -> {
			res.type("text/xml");
			return xmlcpu.getXMLCPUPercentages();
		});
		get("/cpu/percentage/list", (req, res) -> {
			res.type("text/xml");
			return xmlcpu.getXMLCPUCorePercentages();
		});


		MemPoller memPoller = new MemPoller();
		memPoller.start();
		XMLMem xmlMem = new XMLMem(memPoller);
		get("/mem", (req, res) -> {
			res.type("text/xml");
			return xmlMem.getXMLMemActual();
		});
		get("/mem/percentage", (request, response) -> {
			response.type("text/xml");
			return xmlMem.getXMLMemPercentages();
		});


		get("/network", (req, res) -> "network");
		get("/network/upload", (req, res) -> "network upload");
		get("/network/download", (req, res) -> "network download");
	}

	public static void writeSigarLibToJavaLibPath() throws Exception {
		File libraryDirectory = new File(System.getProperty("user.dir").concat("/lib"));
		if (libraryDirectory.exists()) {
			System.out.println("Using existing lib directory");
		} else {
			System.out.println("Creating new lib directory.");
			if (!libraryDirectory.mkdir()) {
				throw new Exception("Could not make lib directory.");
			}
		}
		System.out.print("Writing sigar lib files...");
		System.setProperty("user.dir", System.getProperty("user.dir").concat("/lib"));
		System.setProperty("java.library.path", System.getProperty("user.dir"));

		InputStream in = Main.class.getResourceAsStream("/sigar-lib.zip");
		ZipInputStream zin = new ZipInputStream(in);
		ZipEntry ze;
		while ((ze = zin.getNextEntry()) != null) {
			FileOutputStream fOut = new FileOutputStream(System.getProperty("java.library.path") + "/" + ze.getName());
			BufferedOutputStream bos = new BufferedOutputStream(fOut);
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = zin.read(buffer)) > 0) {
				bos.write(buffer, 0, bytesRead);
			}
			bos.flush();
			zin.closeEntry();
			fOut.close();
		}
		zin.close();
		System.out.println(" Done.");
	}

}
