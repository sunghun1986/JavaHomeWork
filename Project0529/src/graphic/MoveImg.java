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
캔버스는 다른 컴포넌트와는 달리, 어떤 그림을 그릴지에 대한 결정을
개발자가 주도하에 할수있다.. (텅빈 도화지) 원하는 그림을 그리라고
준것이다... 

자바 분야에서는 그림의 주체가 컴포넌트 스스로 이기 때문에, 자기 자신을
그린다. 이때 그리는 행동은 paint() 메서드로 동작한다!! 따라서 우리의 경우
캔버스가 스스로 그렸을때 아무것도 없기 때문에, 이번엔 개발자가 주체가되어
그림을 뺏어야 한다.. 따라서 Canvas의 paint 메서드를 오버라이딩!
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
		bt_left = new JButton("좌");
		bt_right = new JButton("우");
		kit = Toolkit.getDefaultToolkit();
		// 1) Toolkit의 자식을 new 하는 방법도 있지만
		// 2) 위처럼 자체적으로 인스턴스를 얻게끔 해주는 static 메서드를 지원
		img = kit.getImage("D:/web_app/java_workspace/Project0511/res/plane.png");

		p_north.add(bt_left);
		p_north.add(bt_right);
		add(p_north, BorderLayout.NORTH);

		// 별도의 클래스로 Canvas를 존재시키면, 개발의 편의성, 효율성이
		// 저하되므로 이 클래스 안에 두겠다!! 무조건 그러면 안되고, 상황에 맞게
		// 현재의 프로젝트의 경우 Canvas의 재활용성이 없기때문에 이렇게함.

		// 내부익명 클래스(Annoymous Inner Class)는 바깥쪽 외부 클래스의
		// 멤버를 내것처럼 접근할 수 있다.
		can = new Canvas() {
			//개발자가 호출해도 안되고, 호출할수도 없음
			//개발자는 오직 아래의 paint메서드가 다시 호출되기를 요청만할수있다
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





























