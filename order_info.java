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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tcp.client;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;




public class order_info extends JFrame {
	private static Point origin = new Point();//��ʼλ��
	public JFrame jf;
	private JTextField textField;
	private JTextField name_txt;
	private JTextField old_txt;
	private JTextArea illness_dis;
	
	public String pname = "";//������Ϣ
	public String pold = "";
	public String psex ="";
	public String pmedi="";
	public String pat_info="";
	public String info ="";
	
	public client cl;//�̴߳���
	
	public String[] array = new String[20];//�洢��������Ϣ�ظ���Ϣ��
	public String warning ;//��ʾ��Ϣ
	public int docnum;
	
	public order_info(client cl2,String str,int docnum) {//str����ҽ������**
		this.docnum = docnum;
		this.warning = str;
		this.jf = new JFrame();
		this.cl = cl2;
		initUI(jf);
		jf.setVisible(true);
	}

	//����һ����ʼ������ķ���  
	public void initUI(JFrame jf){  
	    jf.setSize(380, 392);//���ô���Ĵ�С  
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
		PanelNorth.setPreferredSize(new Dimension(0, 25)); // ���ô�С
		// ����رպ���С��ͼ�갴ť
		JButton jbc = new JButton(new ImageIcon("factors2/close.png"));// �ر�
		jbc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});
		jbc.setBounds(353, 0, 27, 22);
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
		jbm.setBounds(327, 0, 27, 22);
		jbm.setContentAreaFilled(false);
		jbm.setRolloverIcon(new ImageIcon("factors2/min_hover.png"));
		jbm.setPressedIcon(new ImageIcon("factors2/min_pressed.png"));
		jbm.setBorderPainted(false);
		jbm.setFocusPainted(false);
		jbm.setToolTipText("��С��");
		PanelNorth.add(jbm);
		
		JLabel title = new JLabel("\u586B\u5199\u8BA2\u5355");
		title.setFont(new Font("����Ҧ��", Font.PLAIN, 18));
		title.setForeground(new Color(230,230,230));
		title.setBounds(10, 0, 148, 26);
		PanelNorth.add(title);
		return PanelNorth;
			
	}
	public JPanel creatPanelCenter() {
			JPanel PanelCenter = new JPanel();
			PanelCenter.setPreferredSize(new Dimension(0, 80));
			PanelCenter.setBackground(new Color(230,230,230));// set background color
			PanelCenter.setLayout(null);
			
			JLabel name = new JLabel("\u59D3\u540D:");
			name.setFont(new Font("����Ҧ��", Font.PLAIN, 18));
			name.setBounds(25, 45, 44, 25);
			name.setForeground(new Color(138,138,138));
			PanelCenter.add(name);
			
			name_txt = new JTextField();
			name_txt.setBounds(83, 45, 86, 24);
			PanelCenter.add(name_txt);
			name_txt.setColumns(10);
			
			JLabel old = new JLabel("\u5E74\u9F84:");
			old.setForeground(new Color(138, 138, 138));
			old.setFont(new Font("����Ҧ��", Font.PLAIN, 18));
			old.setBounds(25, 83, 44, 25);
			PanelCenter.add(old);
			
			old_txt = new JTextField();
			old_txt.setColumns(10);
			old_txt.setBounds(83, 86, 86, 24);
			PanelCenter.add(old_txt);
			
			JLabel sex = new JLabel("\u6027\u522B:");
			sex.setForeground(new Color(138, 138, 138));
			sex.setFont(new Font("����Ҧ��", Font.PLAIN, 18));
			sex.setBounds(212, 45, 44, 25);
			PanelCenter.add(sex);
			
			JLabel medicare = new JLabel("\u533B\u4FDD:");
			medicare.setForeground(new Color(138, 138, 138));
			medicare.setFont(new Font("����Ҧ��", Font.PLAIN, 18));
			medicare.setBounds(212, 83, 44, 25);
			PanelCenter.add(medicare);
			
			JRadioButton woman = new JRadioButton("\u5973");
			woman.setBounds(266, 47, 44, 27);
			woman.setBackground(new Color(230,230,230));
			woman.setForeground(new Color(138,138,138));
			PanelCenter.add(woman);
			
			JRadioButton man = new JRadioButton("\u7537");
			man.setBounds(316, 47, 44, 27);
			man.setBackground(new Color(230,230,230));
			man.setForeground(new Color(138,138,138));
			PanelCenter.add(man);
			
			ButtonGroup group=new ButtonGroup();  
		    group.add(woman);  
		    group.add(man);  
		     
			
			JRadioButton medi_yes = new JRadioButton("\u6709");
			medi_yes.setForeground(new Color(138, 138, 138));
			medi_yes.setBackground(new Color(230, 230, 230));
			medi_yes.setBounds(266, 85, 44, 27);
			PanelCenter.add(medi_yes);
			
			JRadioButton medi_no = new JRadioButton("\u65E0");
			medi_no.setForeground(new Color(138, 138, 138));
			medi_no.setBackground(new Color(230, 230, 230));
			medi_no.setBounds(316, 85, 44, 27);
			PanelCenter.add(medi_no);
			
			ButtonGroup group1=new ButtonGroup();  
		    group1.add(woman);  
		    group1.add(man);  
			
			JLabel illness = new JLabel("\u75C5\u60C5\u63CF\u8FF0:");
			illness.setForeground(new Color(138, 138, 138));
			illness.setFont(new Font("����Ҧ��", Font.PLAIN, 18));
			illness.setBounds(25, 130, 136, 25);
			PanelCenter.add(illness);
			
			illness_dis = new JTextArea();
			illness_dis.setFont(new Font("����Ҧ��", Font.PLAIN, 16));
			illness_dis.setBounds(25, 170, 335, 132);
			illness_dis.setForeground(new Color(138,138,138));
			PanelCenter.add(illness_dis);
			illness_dis.setColumns(10);
			
			JButton order_now = new JButton(new ImageIcon("factors2/order_now.png"));
			order_now.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					pname = name_txt.getText();
					pold = old_txt.getText();
					pat_info = illness_dis.getText();
					if(woman.isSelected()) psex = "Ů";
					else if(man.isSelected()) psex = "��";
					if(medi_yes.isSelected()) pmedi ="��";
					else if(medi_no.isSelected()) pmedi ="��";
					String order_mess = ""+"ordernow"+"&"+docnum +"&"+pname +"&"+psex+"&"+pold+"&"+pmedi+"&"+pat_info;
					cl.tcpsend(order_mess);//send the orderinfo
					String num ="";
					try {
						num = cl.br.readLine();
					} catch (IOException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
					System.out.println(num);
				    array = num.split("&");
				    if((array[0]).equals("num"))
				    	JOptionPane.showMessageDialog(null, "���ĺ���Ϊ"+array[1]+"\r\n"+"���Ķ�����Ϊ" + array[2], "������ʾ", JOptionPane.ERROR_MESSAGE);
				    else if((array[0]).equals("no")) JOptionPane.showMessageDialog(null, "��ǰҽʦ�Һ�����", "������ʾ", JOptionPane.ERROR_MESSAGE);
				     //����������departok/no&��������&docnum
				    //�����رգ���ҳ����ת
				    //JOptionPane.showMessageDialog(null, " �ڶԻ�������ʾ�������Ե����� ", " ���������ִ�", JOptionPane.ERROR_MESSAGE,icon);
					jf.dispose();
				}
			});
			order_now.setBounds(245, 315, 115, 35);
			order_now.setFocusPainted(false);
			order_now.setBorder(null);
			PanelCenter.add(order_now);
			
			JLabel message = new JLabel(warning);
			message.setForeground(new Color(105, 105, 105));
			message.setFont(new Font("����Ҧ��", Font.PLAIN, 15));
			message.setBounds(25, 13, 335, 18);
			PanelCenter.add(message);
			return PanelCenter;
	}
}
