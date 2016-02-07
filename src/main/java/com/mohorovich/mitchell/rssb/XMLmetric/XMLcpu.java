package com.mohorovich.mitchell.rssb.XMLmetric;

import com.mohorovich.mitchell.rssb.pollers.CPUPoller;
import org.hyperic.sigar.CpuPerc;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by mitchellmohorovich on 2016-02-07.
 * This class formats CPU data retrieved through the SIGAR API into
 * XML format.
 */
public class XMLCPU extends XMLMetric {

	private static final String CORES_TAG_NAME = "cores";
	private static final String PERCENTAGES_TAG_NAME = "percentages";
	private static final String CORE_TAG_NAME = "core";
	private static final String CORES_NUMBER_ATTRIBUTE = "amount";
	private static final String PERCENTAGE_COMBINED = "combined";
	private static final String PERCENTAGE_IDLE = "idle";
	private static final String PERCENTAGE_SYSTEM = "system";
	private static final String PERCENTAGE_USER = "user";

	private CPUPoller cpuPoller;

	public XMLCPU(CPUPoller cpuPoller) {
		super(cpuPoller);
		this.cpuPoller = cpuPoller;
	}

	public String getXMLCPUPercentages() {
		CpuPerc cpuPerc = cpuPoller.getCpuPerc();
		Document document;
		try {
			document = bootstrapDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return  getErrorXMLString();
		}

		Element rootElement = document.createElement(ROOT_TAG);
		document.appendChild(rootElement);

		Element percentages = document.createElement(PERCENTAGES_TAG_NAME);
		rootElement.appendChild(percentages);

		appendCPUPercentageElements(percentages, cpuPerc);

		return stringFromDocument(document);
	}

	public String getXMLCPUCorePercentages() {
		CpuPerc[] cpuPercs = cpuPoller.getCpuPercList();
		Document document;
		try {
			document = bootstrapDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return getErrorXMLString();
		}

		Element rootElement = document.createElement(ROOT_TAG);
		document.appendChild(rootElement);

		Element cores = document.createElement(CORES_TAG_NAME);
		rootElement.appendChild(cores);
		for(CpuPerc cpuPerc : cpuPercs) {
			cores.appendChild(createPercentageCoreElement(document, cpuPerc));
		}

		return stringFromDocument(document);
	}

	private Element createPercentageCoreElement(Document document, CpuPerc cpuPerc) {
		Element coreElement = document.createElement(CORE_TAG_NAME);
		appendCPUPercentageElements(coreElement, cpuPerc);
		return coreElement;
	}

	private void appendCPUPercentageElements(Element element, CpuPerc cpuPerc) {
		Document elementOwnerDocument = element.getOwnerDocument();
		element.appendChild(newElementWithText(elementOwnerDocument, PERCENTAGE_SYSTEM, String.valueOf(cpuPerc.getSys())));
		element.appendChild(newElementWithText(elementOwnerDocument, PERCENTAGE_USER, String.valueOf(cpuPerc.getUser())));
		element.appendChild(newElementWithText(elementOwnerDocument, PERCENTAGE_COMBINED, String.valueOf(cpuPerc.getCombined())));
		element.appendChild(newElementWithText(elementOwnerDocument, PERCENTAGE_IDLE, String.valueOf(cpuPerc.getIdle())));
	}


}
