package galleryimage;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImageGallery extends JFrame implements ActionListener{
	
	BufferedImage img = null;
	
	public ImageGallery() {
		
		setTitle("배경화면테슷트");
		
		//배경 패널
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setSize(480, 640);
		layeredPane.setLayout(null);
		
		try {
			img = ImageIO.read(new File("D:/web_app/js_workspace/images/trip/trip2.jpg"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "불러오기실패");
			e.printStackTrace();
		}
		
		//이미지삽입
		myPanel panel = new myPanel();
		panel.setSize(480, 640);
		layeredPane.add(panel);
		
		setLayout(null);
		add(layeredPane);
		
		setBounds(720, 220, 480, 640);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
				
	}
	
	class myPanel extends JPanel{
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		
	}
	
	public static void main(String[] args) {
		new ImageGallery();
	}
}

















