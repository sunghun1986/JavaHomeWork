package imagegallery;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JPanelLogin extends JFrame implements ActionListener{
	
	JPanel panel1,panel2;
	JTextField t_id;
	JPasswordField t_pw;
	JButton bt_ok,bt_cancle;
	JLabel label1,label2;
	
	public JPanelLogin() {
	
		panel1 = new JPanel();
		panel2 = new JPanel();
		t_id = new JTextField(10);
		t_pw = new JPasswordField(10);
		label1 = new JLabel("���̵�");
		label2 = new JLabel("��й�ȣ");
		bt_ok = new JButton("Ȯ��");
		bt_cancle = new JButton("���");
		
		//��Ÿ��
		panel1.setLayout(new GridLayout(2,2));
		
		
		//����
		panel1.add(label1);
		panel1.add(t_id);
		t_id.setToolTipText("ID�� �Է��ϼ���"); // ���콺�� �÷������� �˸�â ����
		
		panel1.add(label2);
		panel1.add(t_pw);
		t_pw.setToolTipText("��й�ȣ�� �Է��ϼ���"); // ���콺�� �÷������� �˸�â ����
		
		panel2.add(bt_ok);
		panel2.add(bt_cancle);
		
		getContentPane().add(panel1,"Center");
		getContentPane().add(panel2,"South");		
		
		setVisible(true);
		setSize(300,150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		bt_ok.addActionListener(this);
		bt_cancle.addActionListener(this);
		
	}
	
	@SuppressWarnings("all")
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		String db_id = "1234";
		String db_pw = "1234";		
		
		if(obj == bt_ok) {
			if(db_id.equals(t_id.getText().trim()) && db_pw.equals(new String(t_pw.getPassword()).trim())) {
				//getPassword�� char �� ��ȯ���ֱ⶧���� String ���� ����ȯ ������Ѵ�.
				JOptionPane.showMessageDialog(this, "�α��μ���");
				t_id.setText("");
				t_pw.setText("");
			}else {
				JOptionPane.showMessageDialog(this, "�α��ν���");
				t_id.setText("");
				t_pw.setText("");
			}
			
		}else if(obj == bt_cancle) {
			t_id.setText("");
			t_pw.setText("");
			t_id.requestFocus();//Ŀ�� �ٽ� �÷�����
		}
	}
	
	public static void main(String[] args) {
		new JPanelLogin();
	}


}




















