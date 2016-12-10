package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import sql.doc_data;
import tcp.client;

import javax.swing.Icon;
import javax.swing.JCheckBox;




public class querybyillness extends JFrame {
	private static Point origin = new Point();//��ʼλ��
	public JFrame jf;
	private JTextPane textPane;
	public String illness = "";
	public client cl;
	public String[] array;
	
	public querybyillness(client cl2) {
		this.cl = cl2;
		this.jf = new JFrame();
		initUI(jf);
		jf.setVisible(true);
		

	}

	//����һ����ʼ������ķ���  
	public void initUI(JFrame jf){  
	    jf.setSize(800, 297);//���ô���Ĵ�С  
	    jf.setLocationRelativeTo(null);
	    jf.setDefaultCloseOperation(3);//���ùر�ʱ�˳�����  
	    jf.setResizable(true);//���ò��ܵ������ڴ�С 
	    jf.setUndecorated(true);//�����߿�
	    //addBackgroundImage(jf);  
	    jf.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// ��¼����ʼ�����
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		jf.addMouseMotionListener(new MouseMotionAdapter() {
			// ��¼�ɿ�λ��
			public void mouseDragged(MouseEvent e) {
				
				Point p = jf.getLocation();
				jf.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
			}
		});
	      
	    //ʵ����һ���߿򲼾�  
	    BorderLayout bl = new BorderLayout();  
	    //���ô���Ĳ���Ϊ�߿򲼾�  
	    jf.getContentPane().setLayout(bl);  
	      
	    /*********���ߵ��������*************/  
	    JPanel PanelNorth = creatPanelNorth();
	    jf.getContentPane().add(PanelNorth,bl.NORTH);  
		     
	      
	    /*********�м���������*************/  
	    JPanel PanelCenter = creatPanelCenter();  
	    jf.getContentPane().add(PanelCenter,bl.CENTER);  
	      
	}  
	
	// �����Сͼ��
	public JPanel creatPanelNorth() {

		JPanel PanelNorth = new JPanel();
		PanelNorth.setLayout(null);
		PanelNorth.setBackground(new Color(198, 47, 47));// ���ñ�����ɫ
		PanelNorth.setPreferredSize(new Dimension(0, 35)); // ���ô�С
		// ����رպ���С��ͼ�갴ť
		JButton jbc = new JButton(new ImageIcon("factors2/close.png"));// �ر�
		jbc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				System.exit(0);
			}
		});
		jbc.setBounds(773, 5, 27, 22);
		jbc.setContentAreaFilled(false); // ���ð�ť͸��
		jbc.setRolloverIcon(new ImageIcon("factors2/close_hover.png"));// ������ʱͼ�������ɫ
		jbc.setPressedIcon(new ImageIcon("factors2/close_pressed.png")); // �����ʱ������ɫ
		jbc.setBorderPainted(false);
		jbc.setFocusPainted(false);
		jbc.setToolTipText("�ر�");
		PanelNorth.add(jbc);
		// ��С����ť
		JButton jbm = new JButton(new ImageIcon("factors2/min.png"));
		jbm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.setExtendedState(jf.ICONIFIED);// ������С����������
			}
		});
		jbm.setBounds(747, 5, 27, 22);
		jbm.setContentAreaFilled(false);
		jbm.setRolloverIcon(new ImageIcon("factors2/min_hover.png"));
		jbm.setPressedIcon(new ImageIcon("factors2/min_pressed.png"));
		jbm.setBorderPainted(false);
		jbm.setFocusPainted(false);
		jbm.setToolTipText("��С��");
		PanelNorth.add(jbm);
		
		JLabel logo = new JLabel("�������ѯϵͳ");
		logo.setFont(new Font("����Ҧ��", Font.PLAIN, 22));
		logo.setBounds(14, -5, 196, 45);
		logo.setForeground(new Color(230,230,230));
		PanelNorth.add(logo);
		return PanelNorth;
			
	}
	public JPanel creatPanelCenter() {
			JPanel PanelCenter = new JPanel();
			
			PanelCenter.setBackground(new Color(230, 230, 230));// ���ñ�����ɫ
			PanelCenter.setLayout(null);
			
			textPane = new JTextPane();
			textPane.setBounds(49, 105, 716, 32);
			//textPane.setBackground(new Color(230,230,230);
			PanelCenter.add(textPane);
			
			JButton query = new JButton(new ImageIcon("factors2/query.png"));
			query.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String ill = textPane.getText();
					if(ill == "") 
						JOptionPane.showMessageDialog(null, "�����벡��", "������ʾ", JOptionPane.ERROR_MESSAGE);
					else cl.tcpsend("querybyillness&" + ill);
					String department = "";
					try {
						department = cl.br.readLine();
					} catch (IOException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
					array = department.split("&");
					//��ѯ�����Ƽ�����
					//������ת���ң�������������
					if((array[0]).equals("departok"))JOptionPane.showMessageDialog(null, "�Ƽ�����Ϊ"+array[1], "������ʾ", JOptionPane.ERROR_MESSAGE);
					//���޴˲����¼
					else if((array[0]).equals("departno"))	JOptionPane.showMessageDialog(null, array[1], "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			});
			query.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			query.setBounds(685, 198, 80, 32);
			query.setFocusPainted(false);
			query.setBorder(null);
			PanelCenter.add(query);
			
			JLabel assignment = new JLabel("\u8BF7\u8F93\u5165\u60A8\u60F3\u8981\u67E5\u8BE2\u7684\u75C5\u60C5\uFF1A");
			assignment.setFont(new Font("����Ҧ��", Font.PLAIN, 25));
			assignment.setBounds(14, 42, 431, 45);
			assignment.setForeground(new Color(138,138,138));
			PanelCenter.add(assignment);
			
			JButton jb1 = new JButton("\u624B\u8DB3\u53E3\u75C5|");
			jb1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					textPane.setText("����ڲ�");
				}
			});
			jb1.setFont(new Font("����ϸ��", Font.ITALIC, 15));
			jb1.setBounds(160, 150, 80, 27);
			jb1.setBackground(new Color(230,230,230));
			jb1.setBorder(null);
			jb1.setForeground(new Color(138,138,138));
			jb1.setContentAreaFilled(false);
			PanelCenter.add(jb1);
			
			JLabel assign = new JLabel("\u60A8\u53EF\u80FD\u60F3\u67E5\u8BE2\uFF1A");
			assign.setFont(new Font("����ϸ��", Font.ITALIC, 15));
			assign.setBounds(49, 150, 158, 27);
			assign.setForeground(new Color(138,138,138));
			PanelCenter.add(assign);
			
			JButton jb5 = new JButton("\u80BA\u7ED3\u6838");
			jb5.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					textPane.setText("�ν��");
				}
			});
			jb5.setForeground(new Color(138, 138, 138));
			jb5.setFont(new Font("����ϸ��", Font.ITALIC, 15));
			jb5.setContentAreaFilled(false);
			jb5.setBorder(null);
			jb5.setBackground(new Color(230, 230, 230));
			jb5.setBounds(397, 150, 62, 27);
			PanelCenter.add(jb5);
			
			JButton jb3 = new JButton("\u9752\u5149\u773C|");
			jb3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					textPane.setText("�����");
				}
			});
			jb3.setForeground(new Color(138, 138, 138));
			jb3.setFont(new Font("����ϸ��", Font.ITALIC, 15));
			jb3.setContentAreaFilled(false);
			jb3.setBorder(null);
			jb3.setBackground(new Color(230, 230, 230));
			jb3.setBounds(285, 150, 55, 27);
			PanelCenter.add(jb3);
			
			JButton jb4 = new JButton("\u5B50\u5BAB\u6536\u7F29|");
			jb4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					textPane.setText("�ӹ�����");
				}
			});
			jb4.setForeground(new Color(138, 138, 138));
			jb4.setFont(new Font("����ϸ��", Font.ITALIC, 15));
			jb4.setContentAreaFilled(false);
			jb4.setBorder(null);
			jb4.setBackground(new Color(230, 230, 230));
			jb4.setBounds(333, 150, 80, 27);
			PanelCenter.add(jb4);
			
			JButton jb2 = new JButton("\u80A9\u5468\u708E|");
			jb2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					textPane.setText("������");
				}
			});
			jb2.setForeground(new Color(138, 138, 138));
			jb2.setFont(new Font("����ϸ��", Font.ITALIC, 15));
			jb2.setContentAreaFilled(false);
			jb2.setBorder(null);
			jb2.setBackground(new Color(230, 230, 230));
			jb2.setBounds(227, 150, 67, 27);
			PanelCenter.add(jb2);
			return PanelCenter;
	}
}
