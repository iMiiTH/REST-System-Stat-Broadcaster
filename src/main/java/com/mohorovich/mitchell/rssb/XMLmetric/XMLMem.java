package com.mohorovich.mitchell.rssb.XMLmetric;

import com.mohorovich.mitchell.rssb.pollers.MemPoller;
import org.hyperic.sigar.Mem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by mitchellmohorovich on 2016-02-07.
 * This class formats Mem data from the SIGAR api into an XML string.
 */
public class XMLMem extends XMLMetric {

	private static final String MEM_PERCENT_ELEMENT = "percentages";
	private static final String MEM_ACTUAL_ELEMENT = "actuals";

	private static final String ACTUAL_FREE = "actualFree";
	private static final String ACTUAL_USED = "actualUsed";
	private static final String TOTAL_FREE = "totalFree";
	private static final String FREE_PERCENT = "free";
	private static final String TOTAL_RAM = "totalRAM";
	private static final String TOTAL_SYSTEM_MEMORY = "totalSystemMemory";
	private static final String TOTAL_USED = "totalUsed";
	private static final String USED_PERCENT = "used";

	private MemPoller memPoller;

	public XMLMem(MemPoller memPoller) {
		super(memPoller);
		this.memPoller = memPoller;
	}

	public String getXMLMemPercentages() {
		Document document;
		Mem mem = memPoller.getMem();

		try {
			document = bootstrapDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return getErrorXMLString();
		}

		Element rootElement = document.createElement(ROOT_TAG);
		document.appendChild(rootElement);

		Element percentagesElement = document.createElement(MEM_PERCENT_ELEMENT);
		rootElement.appendChild(percentagesElement);
		appendMemoryPercentageElements(percentagesElement, mem);

		return stringFromDocument(document);
	}

	public String getXMLMemActual() {
		Document document;
		Mem mem = memPoller.getMem();

		try {
			document = bootstrapDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return getErrorXMLString();
		}

		Element rootElement = document.createElement(ROOT_TAG);
		document.appendChild(rootElement);

		Element actualsElement = document.createElement(MEM_ACTUAL_ELEMENT);
		rootElement.appendChild(actualsElement);
		appendMemoryActualElements(actualsElement, mem);

		return stringFromDocument(document);
	}

	private void appendMemoryPercentageElements(Element element, Mem mem) {
		Document elementOwnerDocument = element.getOwnerDocument();
		element.appendChild(newElementWithText(elementOwnerDocument, FREE_PERCENT, String.valueOf(mem.getFreePercent())));
		element.appendChild(newElementWithText(elementOwnerDocument, USED_PERCENT, String.valueOf(mem.getUsedPercent())));
	}

	private void appendMemoryActualElements(Element element, Mem mem) {
		Document elementOwnerDocument = element.getOwnerDocument();
		element.appendChild(newElementWithText(elementOwnerDocument, ACTUAL_FREE, String.valueOf(mem.getActualFree())));
		element.appendChild(newElementWithText(elementOwnerDocument, ACTUAL_USED, String.valueOf(mem.getActualUsed())));
		element.appendChild(newElementWithText(elementOwnerDocument, TOTAL_FREE, String.valueOf(mem.getTotal())));
		element.appendChild(newElementWithText(elementOwnerDocument, TOTAL_USED, String.valueOf(mem.getUsed())));
		element.appendChild(newElementWithText(elementOwnerDocument, TOTAL_RAM, String.valueOf(mem.getRam())));
		element.appendChild(newElementWithText(elementOwnerDocument, TOTAL_SYSTEM_MEMORY, String.valueOf(mem.getTotal())));
	}
}
