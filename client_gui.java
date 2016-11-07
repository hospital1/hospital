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
	private JPanel PanelCenter;
	private CardLayout cl_PanelCenter = new CardLayout();// 设置卡片布局
	private JTextField textField;
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
			PanelCenter = new JPanel();
			PanelCenter.setPreferredSize(new Dimension(0, 565));
			PanelCenter.setLayout(cl_PanelCenter);//cards layout
			
			JPanel Home = new JPanel();
			JPanel Department = new JPanel();
			JPanel Doc_infor = new JPanel();
			Home.setLayout(null);
			Department.setLayout(null);
			Doc_infor.setLayout(null);
			Home.setPreferredSize(new Dimension(0, 565));
			Department.setPreferredSize(new Dimension(0, 565));
			Doc_infor.setPreferredSize(new Dimension(0, 565));
			Home.setBackground(new Color(230,230,230));
			Department.setBackground(new Color(230,230,230));
			Doc_infor.setBackground(new Color(230,230,230));
			
			JButton will = new JButton(new ImageIcon("factors2/will.png"));//home page
			will.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cl_PanelCenter.show(PanelCenter, "p2");
				}
			});
			will.setBorder(null);
			will.setFocusPainted(false);
			will.setBounds(74, 200, 297, 183);
			Home.add(will);
			JButton already = new JButton(new ImageIcon("factors2/already.png"));
			already.setFocusPainted(false);
			already.setBorder(null);
			already.setBounds(422, 200, 297, 183);
			Home.add(already);
            JLabel welcome = new JLabel("欢迎进入哈尔滨工业大学医院自助挂号系统！");
			welcome.setFont(new Font("方正姚体", Font.PLAIN, 25));
			welcome.setBounds(63, 108, 560, 46);
			welcome.setForeground(new Color(138,138,138));
			Home.add(welcome);
			PanelCenter.add(Home,"p1");
			
			JButton waike = new JButton(new ImageIcon("factors2/waike.png"));//Department page
			waike.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					cl_PanelCenter.show(PanelCenter, "p3");
				}
			});
			waike.setBorder(null);
			waike.setFocusPainted(false);
			waike.setBounds(141, 162, 133, 160);
			Department.add(waike);
			JButton neike = new JButton(new ImageIcon("factors2/neike.png"));
			neike.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cl_PanelCenter.show(PanelCenter, "p3");
				}
			});
			neike.setFocusPainted(false);
			neike.setBorder(null);
			neike.setBounds(275, 162, 133, 160);
			Department.add(neike);
			JButton fuchanke = new JButton(new ImageIcon("factors2/fuchanke.png"));
			fuchanke.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cl_PanelCenter.show(PanelCenter, "p3");
				}
			});
			fuchanke.setFocusPainted(false);
			fuchanke.setBorder(null);
			fuchanke.setBounds(409, 162, 133, 160);
			Department.add(fuchanke);
			JButton erke = new JButton(new ImageIcon("factors2/erke.png"));
			erke.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cl_PanelCenter.show(PanelCenter, "p3");
				}
			});
			erke.setFocusPainted(false);
			erke.setBorder(null);
			erke.setBounds(542, 162, 133, 160);
			Department.add(erke);			
			JButton yanke = new JButton(new ImageIcon("factors2/yanke.png"));
			yanke.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cl_PanelCenter.show(PanelCenter, "p3");
				}
			});
			yanke.setFocusPainted(false);
			yanke.setBorder(null);
			yanke.setBounds(141, 324, 133, 160);
			Department.add(yanke);			
			JButton erbihouke = new JButton(new ImageIcon("factors2/erbihouke.png"));
			erbihouke.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cl_PanelCenter.show(PanelCenter, "p3");
				}
			});
			erbihouke.setFocusPainted(false);
			erbihouke.setBorder(null);
			erbihouke.setBounds(275, 324, 133, 160);
			Department.add(erbihouke);			
			JButton kouqiangke = new JButton(new ImageIcon("factors2/kouqiangke.png"));
			kouqiangke.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cl_PanelCenter.show(PanelCenter, "p3");
				}
			});
			kouqiangke.setFocusPainted(false);
			kouqiangke.setBorder(null);
			kouqiangke.setBounds(409, 324, 133, 160);
			Department.add(kouqiangke);			
			JButton anbingqing = new JButton(new ImageIcon("factors2/anbingqing.png"));
			anbingqing.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cl_PanelCenter.show(PanelCenter, "p3");
				}
			});
			anbingqing.setFocusPainted(false);
			anbingqing.setBorder(null);
			anbingqing.setBounds(542, 324, 133, 160);
			Department.add(anbingqing);			
			JButton back = new JButton(new ImageIcon("factors2/back.png"));
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cl_PanelCenter.show(PanelCenter, "p1");
				}
			});
			back.setFocusPainted(false);
			back.setBorder(null);
			back.setBounds(625, 78, 105, 47);
			Department.add(back);
			JLabel prompt = new JLabel("请选择想要预约的科室！");
			prompt.setForeground(new Color(138, 138, 138));
			prompt.setFont(new Font("方正姚体", Font.PLAIN, 28));
			prompt.setBounds(58, 78, 447, 47);
			Department.add(prompt);
			PanelCenter.add(Department, "p2");
						
			JButton Doc1 = new JButton(new ImageIcon("factors2/doctor.png"));
			Doc1.setBounds(70, 96, 133, 185);
			Doc1.setFocusPainted(false);
			Doc1.setBorder(null);
			Doc_infor.add(Doc1);
			JButton Doc2 = new JButton(new ImageIcon("factors2/doctor.png"));
			Doc2.setBounds(70, 316, 133, 185);
			Doc2.setFocusPainted(false);
			Doc2.setBorder(null);
			Doc_infor.add(Doc2);			
			JButton order1 = new JButton(new ImageIcon("factors2/order.png"));
			order1.setBounds(645, 243, 86, 38);
			order1.setFocusPainted(false);
			order1.setBorder(null);
			Doc_infor.add(order1);
			JButton order2 = new JButton(new ImageIcon("factors2/order.png"));
			order2.setBounds(645, 463, 86, 38);
			order2.setFocusPainted(false);
			order2.setBorder(null);
			Doc_infor.add(order2);
			JTextField info1 = new JTextField();
			info1.setFont(new Font("方正姚体", Font.PLAIN, 15));
			info1.setForeground(new Color(138,138,138));
			info1.setBounds(247, 96, 484, 134);
			info1.setBorder(null);
			info1.setBackground(new Color(230,230,230));
			info1.setColumns(10);
			info1.setText("医师简介");
			Doc_infor.add(info1);
			JTextField info2 = new JTextField();
			info2.setColumns(10);
			info2.setFont(new Font("方正姚体", Font.PLAIN, 15));
			info2.setForeground(new Color(138,138,138));
			info2.setBorder(null);
			info2.setBackground(new Color(230,230,230));
			info2.setBounds(247, 316, 484, 134);
			info2.setText("医师简介");
			Doc_infor.add(info2);
			JButton back1 = new JButton(new ImageIcon("factors2/back.png"));
			back1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cl_PanelCenter.show(PanelCenter, "p2");
				}
			});
			back1.setFocusPainted(false);
			back1.setBorder(null);
			back1.setBounds(659, 25, 105, 47);
			Doc_infor.add(back1);
			PanelCenter.add(Doc_infor,"p3");
			
			return PanelCenter;
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
