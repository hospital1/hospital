package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.Icon;




public class client_gui extends JFrame {
	private static Point origin = new Point();//初始位点
	public JFrame jf;
	private JTextField textField;
	private JPanel PanelCenter1;
	private CardLayout cards = new CardLayout();// 设置卡片布局
	public client_gui() {
		
		this.jf = new JFrame();
		initUI(jf);
		jf.setVisible(true);
	}
	//定义一个添加背景的方法
	public void addBackgroundImage(JFrame jf){
				
			//实例化一个ImageIcon图标类的对象
			ImageIcon image = new ImageIcon("factors2/board1.png");
			//实例化一个标签类的对象
			JLabel background = new JLabel(image); 
			//设置标签显示的位置和大小
			background.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
			//将标签添加到窗体的第二层面板上
			jf.getLayeredPane().add(background,new Integer(Integer.MIN_VALUE));
			//获取窗体的第一层板对象
			JPanel contentPanel = (JPanel)jf.getContentPane();
			//设置第一层面板为透明
			contentPanel.setOpaque(false);
			
	}
	//定义一个初始化界面的方法  
	public void initUI(JFrame jf){  
	    jf.setSize(800, 600);//设置窗体的大小  
	    jf.setLocationRelativeTo(null);
	    jf.setDefaultCloseOperation(3);//设置关闭时退出程序  
	    jf.setResizable(true);//设置不能调整窗口大小 
	    jf.setUndecorated(true);//消除边框
	    //addBackgroundImage(jf);  
	    jf.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// 记录鼠标初始点击点
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		jf.addMouseMotionListener(new MouseMotionAdapter() {
			// 记录松开位点
			public void mouseDragged(MouseEvent e) {
				
				Point p = jf.getLocation();
				jf.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
			}
		});
	      
	    //实例化一个边框布局  
	    BorderLayout bl = new BorderLayout();  
	    //设置窗体的布局为边框布局  
	    jf.getContentPane().setLayout(bl);  
	      
	    /*********北边的面板容器*************/  
	    JPanel PanelNorth = creatPanelNorth();
	    jf.getContentPane().add(PanelNorth,bl.NORTH);  
		     
	      
	    /*********中间的面板容器*************/  
	    JPanel PanelCenter = creatPanelCenter();  
	    jf.getContentPane().add(PanelCenter,bl.CENTER);  
	    
	   addBackgroundImage(jf);
	      
	}  
	
	// 最大最小图标
	public JPanel creatPanelNorth() {

		JPanel PanelNorth = new JPanel();
		PanelNorth.setLayout(null);
		PanelNorth.setBackground(new Color(198, 47, 47));// 设置背景颜色
		PanelNorth.setPreferredSize(new Dimension(0, 45)); // 设置大小
		// 加入关闭和最小化图标按钮
		JButton jbc = new JButton(new ImageIcon("factors2/close.png"));// 关闭
		jbc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				System.exit(0);
			}
		});
		jbc.setBounds(773, 5, 27, 22);
		jbc.setContentAreaFilled(false); // 设置按钮透明
		jbc.setRolloverIcon(new ImageIcon("factors2/close_hover.png"));// 鼠标进入时图标更换颜色
		jbc.setPressedIcon(new ImageIcon("factors2/close_pressed.png")); // 鼠标点击时更换颜色
		jbc.setBorderPainted(false);
		jbc.setFocusPainted(false);
		jbc.setToolTipText("关闭");
		PanelNorth.add(jbc);
		// 最小化按钮
		JButton jbm = new JButton(new ImageIcon("factors2/min.png"));
		jbm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.setExtendedState(jf.ICONIFIED);// 窗口最小化到任务栏
			}
		});
		jbm.setBounds(746, 5, 27, 22);
		jbm.setContentAreaFilled(false);
		jbm.setRolloverIcon(new ImageIcon("factors2/min_hover.png"));
		jbm.setPressedIcon(new ImageIcon("factors2/min_pressed.png"));
		jbm.setBorderPainted(false);
		jbm.setFocusPainted(false);
		jbm.setToolTipText("最小化");
		PanelNorth.add(jbm);
		
		JLabel lblNewLabel_1 = new JLabel("自助挂号系统");
		lblNewLabel_1.setForeground(new Color(230,230,230));
		lblNewLabel_1.setFont(new Font("方正姚体", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(14, 0, 196, 45);
		PanelNorth.add(lblNewLabel_1);
		
		return PanelNorth;
			
	}
	public JPanel creatPanelCenter() {
			PanelCenter1 = new JPanel();
			PanelCenter1.setPreferredSize(new Dimension(0, 565));
			PanelCenter1.setLayout(cards);//卡片布局
			
			JPanel home = new JPanel();
			JPanel PanelCenter = new JPanel();
			home.setLayout(null);
			PanelCenter.setLayout(null);
			home.setPreferredSize(new Dimension(0, 565));
			PanelCenter.setPreferredSize(new Dimension(0, 565));//卡片panel初始化
			
			JButton jb1 = new JButton(new ImageIcon("factors2/will.png"));//home
			jb1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cards.show(PanelCenter1, "p2");
				}
			});
			jb1.setBorder(null);
			jb1.setFocusPainted(false);
			jb1.setBounds(74, 200, 297, 183);
			home.add(jb1);
			JButton button_8 = new JButton(new ImageIcon("factors2/already.png"));
			button_8.setFocusPainted(false);
			button_8.setBorder(null);
			button_8.setBounds(422, 200, 297, 183);
			home.add(button_8);
			home.setVisible(true);//默认显示home页面
			
			
			JButton btnNewButton = new JButton(new ImageIcon("factors2/waike.png"));//科室信息页面
			btnNewButton.setBorder(null);
			btnNewButton.setFocusPainted(false);
			btnNewButton.setBounds(141, 162, 133, 160);
			PanelCenter.add(btnNewButton);
			JButton button = new JButton(new ImageIcon("factors2/neike.png"));
			button.setFocusPainted(false);
			button.setBorder(null);
			button.setBounds(275, 162, 133, 160);
			PanelCenter.add(button);
			JButton button_1 = new JButton(new ImageIcon("factors2/fuchanke.png"));
			button_1.setFocusPainted(false);
			button_1.setBorder(null);
			button_1.setBounds(409, 162, 133, 160);
			PanelCenter.add(button_1);
			JButton button_2 = new JButton(new ImageIcon("factors2/erke.png"));
			button_2.setFocusPainted(false);
			button_2.setBorder(null);
			button_2.setBounds(542, 162, 133, 160);
			PanelCenter.add(button_2);			
			JButton button_3 = new JButton(new ImageIcon("factors2/yanke.png"));
			button_3.setFocusPainted(false);
			button_3.setBorder(null);
			button_3.setBounds(141, 324, 133, 160);
			PanelCenter.add(button_3);			
			JButton button_4 = new JButton(new ImageIcon("factors2/erbihouke.png"));
			button_4.setFocusPainted(false);
			button_4.setBorder(null);
			button_4.setBounds(275, 324, 133, 160);
			PanelCenter.add(button_4);			
			JButton button_5 = new JButton(new ImageIcon("factors2/kouqiangke.png"));
			button_5.setFocusPainted(false);
			button_5.setBorder(null);
			button_5.setBounds(409, 324, 133, 160);
			PanelCenter.add(button_5);			
			JButton button_6 = new JButton(new ImageIcon("factors2/anbingqing.png"));
			button_6.setFocusPainted(false);
			button_6.setBorder(null);
			button_6.setBounds(542, 324, 133, 160);
			PanelCenter.add(button_6);			
			JButton button_7 = new JButton(new ImageIcon("factors2/back.png"));
			button_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cards.show(PanelCenter1, "p1");
				}
			});
			button_7.setFocusPainted(false);
			button_7.setBorder(null);
			button_7.setBounds(625, 78, 105, 47);
			PanelCenter.add(button_7);
			
			JLabel lblNewLabel = new JLabel("请选择想要预约的科室！");//返回按钮
			lblNewLabel.setForeground(new Color(138, 138, 138));
			lblNewLabel.setFont(new Font("方正姚体", Font.PLAIN, 28));
			lblNewLabel.setBounds(58, 78, 447, 47);
			PanelCenter.add(lblNewLabel);
			
			
			PanelCenter1.add(home,"p1");
			
			JLabel lblNewLabel_2 = new JLabel("欢迎进入哈尔滨工业大学医院自助挂号系统！");
			lblNewLabel_2.setFont(new Font("方正姚体", Font.PLAIN, 25));
			lblNewLabel_2.setBounds(63, 108, 560, 46);
			lblNewLabel_2.setForeground(new Color(138,138,138));
			home.add(lblNewLabel_2);
			PanelCenter1.add(PanelCenter, "p2");
			
			return PanelCenter1;
	}
		
		
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new client_gui();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
