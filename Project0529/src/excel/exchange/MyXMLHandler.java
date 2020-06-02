package excel.exchange;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//태그를 파싱하는 과정에서, 이벤트마다 메서드를
//호출하는 객체!!

public class MyXMLHandler extends DefaultHandler{
	
	
	@Override
	public void startDocument() throws SAXException {
		
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.print("<"+qName+">");
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {	
		String data = new String(ch,start,length);
		System.out.print(data);
	}
	
	@Override
	public void endDocument() throws SAXException {
		
	}
	
	
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("</"+qName+">");
	}
	
}
