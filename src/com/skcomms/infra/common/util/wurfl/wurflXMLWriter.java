package com.skcomms.infra.common.util.wurfl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class wurflXMLWriter {

	public String createXMLDocument(Map<String, String> deviceInfo, String rootElement, String subElement) {
		Document document = DocumentHelper.createDocument();
        Element root = document.addElement( rootElement );
        Element wurfl = root.addElement(subElement);
        for (Map.Entry<String, String> el : deviceInfo.entrySet()) {
        	wurfl.addElement(el.getKey()).addText(el.getValue());
		}
        
        StringWriter sw = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(sw, format);
        try {
			writer.write(document);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return sw.toString();
	}
}
