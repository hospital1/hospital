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
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import sql.doc_data;
import javax.swing.Icon;




public class querybyillness extends JFrame {
	private static Point origin = new Point();//初始位点
	public JFrame jf;
	private JTextPane textPane;
	public querybyillness() {
		
		this.jf = new JFrame();
		initUI(jf);
		jf.setVisible(true);
	}

	//定义一个初始化界面的方法  
	public void initUI(JFrame jf){  
	    jf.setSize(800, 560);//设置窗体的大小  
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
	      
	}  
	
	// 最大最小图标
	public JPanel creatPanelNorth() {

		JPanel PanelNorth = new JPanel();
		PanelNorth.setLayout(null);
		PanelNorth.setBackground(new Color(198, 47, 47));// 设置背景颜色
		PanelNorth.setPreferredSize(new Dimension(0, 35)); // 设置大小
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
		jbm.setBounds(747, 5, 27, 22);
		jbm.setContentAreaFilled(false);
		jbm.setRolloverIcon(new ImageIcon("factors2/min_hover.png"));
		jbm.setPressedIcon(new ImageIcon("factors2/min_pressed.png"));
		jbm.setBorderPainted(false);
		jbm.setFocusPainted(false);
		jbm.setToolTipText("最小化");
		PanelNorth.add(jbm);
		
		JLabel logo = new JLabel("按病情查询系统");
		logo.setFont(new Font("方正姚体", Font.PLAIN, 22));
		logo.setBounds(14, -5, 196, 45);
		logo.setForeground(new Color(230,230,230));
		PanelNorth.add(logo);
		return PanelNorth;
			
	}
	public JPanel creatPanelCenter() {
			JPanel PanelCenter = new JPanel();
			
			PanelCenter.setBackground(new Color(230, 230, 230));// 设置背景颜色
			PanelCenter.setLayout(null);
			
			textPane = new JTextPane();
			textPane.setBounds(75, 191, 655, 201);
			textPane.setBackground(new Color(230,230,230));
			PanelCenter.add(textPane);
			
			JButton query = new JButton(new ImageIcon("factors2/query.png"));
			query.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			query.setBounds(650, 437, 80, 32);
			query.setFocusPainted(false);
			query.setBorder(null);
			PanelCenter.add(query);
			
			JButton button = new JButton((Icon) null);
			button.setFocusPainted(false);
			button.setBorder(null);
			button.setBounds(75, 80, 80, 32);
			PanelCenter.add(button);
			
			JButton button_1 = new JButton((Icon) null);
			button_1.setFocusPainted(false);
			button_1.setBorder(null);
			button_1.setBounds(190, 80, 80, 32);
			PanelCenter.add(button_1);
			
			JButton button_2 = new JButton((Icon) null);
			button_2.setFocusPainted(false);
			button_2.setBorder(null);
			button_2.setBounds(305, 80, 80, 32);
			PanelCenter.add(button_2);
			
			JButton button_3 = new JButton((Icon) null);
			button_3.setFocusPainted(false);
			button_3.setBorder(null);
			button_3.setBounds(420, 80, 80, 32);
			PanelCenter.add(button_3);
			
			JButton button_4 = new JButton((Icon) null);
			button_4.setFocusPainted(false);
			button_4.setBorder(null);
			button_4.setBounds(535, 80, 80, 32);
			PanelCenter.add(button_4);
			
			JButton button_5 = new JButton((Icon) null);
			button_5.setFocusPainted(false);
			button_5.setBorder(null);
			button_5.setBounds(650, 80, 80, 32);
			PanelCenter.add(button_5);
			
			JButton button_6 = new JButton((Icon) null);
			button_6.setFocusPainted(false);
			button_6.setBorder(null);
			button_6.setBounds(75, 125, 80, 32);
			PanelCenter.add(button_6);
			
			JButton button_7 = new JButton((Icon) null);
			button_7.setFocusPainted(false);
			button_7.setBorder(null);
			button_7.setBounds(190, 125, 80, 32);
			PanelCenter.add(button_7);
			
			JButton button_8 = new JButton((Icon) null);
			button_8.setFocusPainted(false);
			button_8.setBorder(null);
			button_8.setBounds(305, 125, 80, 32);
			PanelCenter.add(button_8);
			
			JButton button_9 = new JButton((Icon) null);
			button_9.setFocusPainted(false);
			button_9.setBorder(null);
			button_9.setBounds(420, 125, 80, 32);
			PanelCenter.add(button_9);
			
			JButton button_10 = new JButton((Icon) null);
			button_10.setFocusPainted(false);
			button_10.setBorder(null);
			button_10.setBounds(535, 125, 80, 32);
			PanelCenter.add(button_10);
			
			JButton button_11 = new JButton((Icon) null);
			button_11.setFocusPainted(false);
			button_11.setBorder(null);
			button_11.setBounds(650, 125, 80, 32);
			PanelCenter.add(button_11);
			
			JLabel assignment = new JLabel("\u8BF7\u9009\u62E9\u60A8\u7B26\u5408\u7684\u75C7\u72B6\uFF1A");
			assignment.setFont(new Font("方正姚体", Font.PLAIN, 25));
			assignment.setBounds(14, 13, 431, 45);
			assignment.setForeground(new Color(138,138,138));
			PanelCenter.add(assignment);
			return PanelCenter;
	}
		
		
	
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new querybyillness();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
