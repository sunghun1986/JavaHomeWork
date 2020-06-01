package nextpage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ShopApp extends JFrame implements ActionListener {

	JPanel p_south, p_center;
	JButton bt_open, bt_save, bt_left, bt_right;
	JTextArea area;
	JScrollPane scroll;
	int index = 0;
	
	Page[] pages = new Page[5];

	public ShopApp() {

		p_south = new JPanel();
		p_center = new JPanel();
		bt_open = new JButton("열기");
		bt_save = new JButton("저장");
		bt_left = new JButton("◀");
		bt_right = new JButton("▶");

		pages[0] = new Login(this, "로그인", Color.PINK, 500, 500, true);
		pages[1] = new Shopping(this, "쇼핑메인", Color.RED, 500, 500, false);
		pages[2] = new ProductManage(this, "상품관리", Color.YELLOW, 500, 500, false);
		pages[3] = new Chatting(this, "1:1채팅문의", Color.DARK_GRAY, 500, 500, false);		
		pages[4] = new MyShopping(this, "마이페이지", Color.GREEN, 500, 500, false);

		p_south.add(bt_left);
		p_south.add(bt_open);
		p_south.add(bt_save);
		p_south.add(bt_right);

		add(p_south, BorderLayout.SOUTH);

//		p_center.setBackground(Color.CYAN);
		add(p_center);
		p_center.add(pages[0]);
		p_center.add(pages[1]);
		p_center.add(pages[2]);
		p_center.add(pages[3]);
		p_center.add(pages[4]);

		setSize(500, 500);		
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		bt_left.addActionListener(this);
		bt_open.addActionListener(this);
		bt_save.addActionListener(this);
		bt_right.addActionListener(this);

	}
	//순서.. 0: pink , 1: red , 2 Yellow , 3: gray , 4: green
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == bt_left) {
			index--;
			if(index < 0) {
				index++;
				JOptionPane.showMessageDialog(this, "페이지가 없습니다.");
				return;
			}
			showPage(index);
			System.out.println("현재페이지 : " + index);
			return;
			
		}else if(obj == bt_right) {
			index++;
			if(index > 4) {
				index--;
				JOptionPane.showMessageDialog(this, "페이지가 없습니다.");
				return;
			}
			showPage(index);
			System.out.println("현재페이지 : " + index);
			return;
		}		
	}

	public void showPage(int page) {
		for(int i = 0; i < pages.length; i++) {
			if(i == page) {
				pages[i].setVisible(true);					
			}else {
				pages[i].setVisible(false);
			}
		}
//		this.setTitle(pages[page].title);
	}

	public static void main(String[] args) {
		new ShopApp();
	}

}
