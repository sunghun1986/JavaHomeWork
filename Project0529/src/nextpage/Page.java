package nextpage;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Page extends JPanel{
	
	ShopApp shopApp;// ���� ��ư��� ������ �����
	String title;
	Color color;
	int width;
	int height;

	boolean showFlag;
	
	public Page(ShopApp shopApp , String title , Color color , int width , int height , boolean showFlag ) {
		
		this.shopApp = shopApp;
		this.title = title;
		this.color = color;
		this.width = width;
		this.height = height;
		this.showFlag = showFlag;
		
		this.setVisible(showFlag);
		this.setBackground(color);
		this.setPreferredSize(new Dimension(width, height));
		
		
		
	}

}
