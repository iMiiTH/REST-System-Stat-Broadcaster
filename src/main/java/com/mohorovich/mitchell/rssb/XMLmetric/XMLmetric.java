package com.mohorovich.mitchell.rssb.XMLmetric;

import com.mohorovich.mitchell.rssb.pollers.MetricPoller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Created by mitchellmohorovich on 2016-02-07.
 * XMLMetric is an abstract class that contains functions common to the formatting
 * of Metric data into XML format.
 */
public abstract class XMLMetric {

	public final String ROOT_TAG = "statistics";

	private MetricPoller metricPoller;

	public XMLMetric(MetricPoller metricPoller) {
		this.metricPoller = metricPoller;
	}

	public Document bootstrapDocument() throws ParserConfigurationException {
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		return documentBuilder.newDocument();
	}

	public String stringFromDocument(Document document) {
		DOMSource domSource = new DOMSource(document);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		try {
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
		} catch(Exception e) {
			e.printStackTrace();
			return getErrorXMLString();
		}
		return writer.toString();
	}

	public String getErrorXMLString() {
		return "<error>An error occurred during processing.</error>";
	}

	protected Element newElementWithText(Document document, String name, String text) {
		Element element = document.createElement(name);
		element.setTextContent(text);
		return element;
	}

}
