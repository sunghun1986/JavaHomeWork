package graphic;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
ĵ������ �ٸ� ������Ʈ�ʹ� �޸�, � �׸��� �׸����� ���� ������
�����ڰ� �ֵ��Ͽ� �Ҽ��ִ�.. (�ֺ� ��ȭ��) ���ϴ� �׸��� �׸����
�ذ��̴�... 

�ڹ� �о߿����� �׸��� ��ü�� ������Ʈ ������ �̱� ������, �ڱ� �ڽ���
�׸���. �̶� �׸��� �ൿ�� paint() �޼���� �����Ѵ�!! ���� �츮�� ���
ĵ������ ������ �׷����� �ƹ��͵� ���� ������, �̹��� �����ڰ� ��ü���Ǿ�
�׸��� ����� �Ѵ�.. ���� Canvas�� paint �޼��带 �������̵�!
*/
public class MoveImg extends JFrame {

	Canvas can;
	JPanel p_north;
	JButton bt_left, bt_right;
	int x = 0;
	int velX = 0;
	Toolkit kit;
	Image img;

	public MoveImg() {

		p_north = new JPanel();
		bt_left = new JButton("��");
		bt_right = new JButton("��");
		kit = Toolkit.getDefaultToolkit();
		// 1) Toolkit�� �ڽ��� new �ϴ� ����� ������
		// 2) ��ó�� ��ü������ �ν��Ͻ��� ��Բ� ���ִ� static �޼��带 ����
		img = kit.getImage("D:/web_app/java_workspace/Project0511/res/plane.png");

		p_north.add(bt_left);
		p_north.add(bt_right);
		add(p_north, BorderLayout.NORTH);

		// ������ Ŭ������ Canvas�� �����Ű��, ������ ���Ǽ�, ȿ������
		// ���ϵǹǷ� �� Ŭ���� �ȿ� �ΰڴ�!! ������ �׷��� �ȵǰ�, ��Ȳ�� �°�
		// ������ ������Ʈ�� ��� Canvas�� ��Ȱ�뼺�� ���⶧���� �̷�����.

		// �����͸� Ŭ����(Annoymous Inner Class)�� �ٱ��� �ܺ� Ŭ������
		// ����� ����ó�� ������ �� �ִ�.
		can = new Canvas() {
			//�����ڰ� ȣ���ص� �ȵǰ�, ȣ���Ҽ��� ����
			//�����ڴ� ���� �Ʒ��� paint�޼��尡 �ٽ� ȣ��Ǳ⸦ ��û���Ҽ��ִ�
			//repaint() --> update() --> paint()
			@Override
			public void paint(Graphics g) {
				g.drawImage(img, x, 150, 50, 50, this);
			}
		};
		
		can.setBackground(Color.WHITE);
		add(can);
		setSize(600,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bt_left.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				velX = -5;
				tick();
				render();
			}
		});
		
		bt_right.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				velX = +5;
				tick();
				render();
			}
		});
	}
	
	public void tick() {
		x += velX;
	}
	
	public void render() {
		can.repaint();
	}	

	public static void main(String[] args) {
		new MoveImg();
	}

}





























