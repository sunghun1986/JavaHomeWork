package excel.exchange;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//�±׸� �Ľ��ϴ� ��������, �̺�Ʈ���� �޼��带
//ȣ���ϴ� ��ü!!

public class MyXMLHandler extends DefaultHandler{
	
	ArrayList<Lang> list;
	
	//����ΰ� ��� ����ġ�� �ִ��� üũ���ִ� ����
	
	boolean isName;
	boolean isVersion;
	boolean isVendor;
	
	Lang lang; // VO����!!
	
	@Override
	public void startDocument() throws SAXException {
		list = new ArrayList<Lang>();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.print("<"+qName+">");
		
		if(qName.equals("lang")) {//emp vo
			lang = new Lang();
		}else if(qName.equals("name")) {
			isName = true;
		}else if(qName.equals("version")) {
			isVersion = true;
		}else if(qName.equals("vendor")) {
			isVendor = true;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {	
		String data = new String(ch,start,length);
		System.out.print(data);
		
		if(isName) {//name�±׸� ����ġ�� �ִٸ�...
			lang.setName(data);
		}else if(isVersion) {
			lang.setVersion(data);
		}else if(isVendor) {
			lang.setVendor(data);
		}
	}
	
	
	
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("</"+qName+">");
		
		if(qName.equals("lang")) {//emp vo
			list.add(lang);
		}else if(qName.equals("name")) {
			isName = false;
		}else if(qName.equals("version")) {
			isVersion = false;
		}else if(qName.equals("vendor")) {
			isVendor = false;
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println("����� ����� ���� " + list.size());
	}
}




