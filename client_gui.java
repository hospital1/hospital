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
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import tcp.client;//import the link for send and receive info
import sql.doc_data;
public class client_gui extends JFrame {
	private static Point origin = new Point();//初始位点
	public JFrame jf;
	public client cg;
	private String orderinfo;
	private JPanel PanelCenter;
	private CardLayout cl_PanelCenter = new CardLayout();// set the layout in card
	private JTextField ordernum;
	private JTextPane info1,info2;
	private JTextArea order_info;
	public String[] array = new String[20];//save message from server 
	public boolean flag =true;
	public int doctornum = 0;
	public boolean orderflag = false;//记录是否有订单存在
	
	private JCheckBox one;
	
	public client_gui(client cg2) {
		this.cg = cg2;
		this.jf = new JFrame();
		initUI(jf);
		jf.setVisible(true);
	}
	
	//定义一个初始化界面的方法  
	public void initUI(JFrame jf){  
	    jf.setSize(800, 600);//设置窗体的大小  
	    jf.setLocationRelativeTo(null);
	    jf.setDefaultCloseOperation(3);//设置关闭时退出程序  
	    jf.setResizable(true);//设置不能调整窗口大小 
	    jf.setUndecorated(true);//消除边框
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
	    PanelNorth.updateUI();
	    jf.getContentPane().add(PanelNorth,bl.NORTH);        
	    /*********中间的面板容器*************/  
	    JPanel PanelCenter = creatPanelCenter();
	    PanelCenter.updateUI();
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
				cg.tcpsend("exit" +"&"+"hahahahah");
				flag = false;
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
		lblNewLabel_1.setBounds(14, -5, 196, 45);
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
			JPanel alre = new JPanel();
			Home.setLayout(null);
			Department.setLayout(null);
			Doc_infor.setLayout(null);
			alre.setLayout(null);
			Home.setPreferredSize(new Dimension(0, 565));
			Department.setPreferredSize(new Dimension(0, 565));
			Doc_infor.setPreferredSize(new Dimension(0, 565));
			alre.setPreferredSize(new Dimension(0, 565));
			Home.setBackground(new Color(230,230,230));
			Department.setBackground(new Color(230,230,230));
			Doc_infor.setBackground(new Color(230,230,230));
			alre.setBackground(new Color(230,230,230));
			
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
			already.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cl_PanelCenter.show(PanelCenter, "p4");
				}
			});
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
					doctornum = 10;
					doc_data dd = new doc_data();
					try {
						info1.setText(dd.query(doctornum+1));
						info2.setText(dd.query(doctornum+2));
					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					
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
					doctornum = 20;
					doc_data dd = new doc_data();
					try {
						info1.setText(dd.query(doctornum+1));
						info2.setText(dd.query(doctornum+2));
					} catch (SQLException e2) {
						// TODO 自动生成的 catch 块
						e2.printStackTrace();
					}
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
					doctornum = 30;
					doc_data dd = new doc_data();
					try {
						info1.setText(dd.query(doctornum+1));
						info2.setText(dd.query(doctornum+2));
					} catch (SQLException e2) {
						// TODO 自动生成的 catch 块
						e2.printStackTrace();
					}
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
					doctornum = 40;
					doc_data dd = new doc_data();
					try {
						info1.setText(dd.query(doctornum+1));
						info2.setText(dd.query(doctornum+2));
					} catch (SQLException e2) {
						// TODO 自动生成的 catch 块
						e2.printStackTrace();
					}
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
					doctornum = 50;
					doc_data dd = new doc_data();
					try {
						info1.setText(dd.query(doctornum+1));
						info2.setText(dd.query(doctornum+2));
					} catch (SQLException e2) {
						// TODO 自动生成的 catch 块
						e2.printStackTrace();
					}
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
					doctornum = 60;
					doc_data dd = new doc_data();
					try {
						info1.setText(dd.query(doctornum+1));
						info2.setText(dd.query(doctornum+2));
					} catch (SQLException e2) {
						// TODO 自动生成的 catch 块
						e2.printStackTrace();
					}
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
					doctornum = 70;
					doc_data dd = new doc_data();
					try {
						info1.setText(dd.query(doctornum+1));
						info2.setText(dd.query(doctornum+2));
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
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
					new querybyillness(cg);
				}
			});
			anbingqing.setFocusPainted(false);
			anbingqing.setBorder(null);
			anbingqing.setBounds(542, 324, 133, 160);
			Department.add(anbingqing);			
			JButton dep_back = new JButton(new ImageIcon("factors2/back.png"));
			dep_back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cl_PanelCenter.show(PanelCenter, "p1");
				}
			});
			dep_back.setFocusPainted(false);
			dep_back.setBorder(null);
			dep_back.setBounds(625, 78, 105, 47);
			Department.add(dep_back);
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
			order1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					doc_data dd = new doc_data();
					try {
						new order_info(cg,dd.orderwarn(doctornum+1),doctornum+1);
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					//String order_mess = "" +inf.pname +"&"+inf.psex+"&"+inf.pold+"&"+inf.pmedi+"&"+inf.pat_info;
					//System.out.println(order_mess);
				}
			});
			order1.setBounds(645, 243, 86, 38);
			order1.setFocusPainted(false);
			order1.setBorder(null);
			Doc_infor.add(order1);
			JButton order2 = new JButton(new ImageIcon("factors2/order.png"));
			order2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					doc_data dd = new doc_data();
					try {
						new order_info(cg,dd.orderwarn(doctornum+2),doctornum+2);
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
			});
			order2.setBounds(645, 463, 86, 38);
			order2.setFocusPainted(false);
			order2.setBorder(null);
			Doc_infor.add(order2);
			info1 = new JTextPane();
			info1.setFont(new Font("方正姚体", Font.PLAIN, 15));
			info1.setForeground(new Color(138,138,138));
			info1.setBounds(247, 129, 484, 119);
			info1.setBorder(null);
			info1.setBackground(new Color(230,230,230));
			Doc_infor.add(info1);
			info2 = new JTextPane();
			info2.setFont(new Font("方正姚体", Font.PLAIN, 15));
			info2.setForeground(new Color(138,138,138));
			info2.setBorder(null);
			info2.setBackground(new Color(230,230,230));
			info2.setBounds(247, 351, 484, 119);
			Doc_infor.add(info2);
			JButton doc_back = new JButton(new ImageIcon("factors2/back.png"));
			doc_back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cl_PanelCenter.show(PanelCenter, "p2");
					doctornum = 0;//清零工作
				}
			});
			doc_back.setFocusPainted(false);
			doc_back.setBorder(null);
			doc_back.setBounds(659, 25, 105, 47);
			Doc_infor.add(doc_back);
			PanelCenter.add(Doc_infor,"p3");
			
			JLabel label1 = new JLabel("\u533B\u5E08\u7B80\u4ECB");
			label1.setFont(new Font("方正姚体", Font.PLAIN, 15));
			label1.setForeground(new Color(138,138,138));
			label1.setBounds(247, 325, 72, 20);
			Doc_infor.add(label1);
			
			JLabel label = new JLabel("\u533B\u5E08\u7B80\u4ECB");
			label.setForeground(new Color(138, 138, 138));
			label.setFont(new Font("方正姚体", Font.PLAIN, 15));
			label.setBounds(247, 105, 72, 20);
			Doc_infor.add(label);
			
			PanelCenter.add(alre, "p4");
			
			JLabel assign = new JLabel("\u8BF7\u8F93\u5165\u60A8\u7684\u8BA2\u5355\u53F7\uFF1A");
			assign.setFont(new Font("方正姚体", Font.BOLD, 18));
			assign.setBounds(37, 18, 180, 36);
			assign.setForeground(new Color(138,138,138));
			alre.add(assign);
			
			ordernum = new JTextField();
			ordernum.setBounds(209, 22, 241, 34);
			alre.add(ordernum);
			ordernum.setColumns(10);
			
			JButton query = new JButton(new ImageIcon("factors2/query.png"));
			query.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					cg.tcpsend("query&"+ordernum.getText());
					String ordermes ="";
					String txt = "订单信息 ："+ "\r\n\r\n";
					try {
						ordermes = cg.br.readLine();
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					System.out.println(ordermes);
					array = ordermes.split("&");
					if(array[0] .equals("ordermes")){
						txt += "\t"+"姓名:" + array[1] + "\r\n";
						txt += "\t"+"性别:" + array[2] + "\r\n";
						txt += "\t"+"年龄:" + array[3] + "\r\n";
						txt += "\t"+"医保:" + array[4] + "\r\n";
						txt += "\t"+"病情描述:" + array[5] + "\r\n";
						orderflag = true;	
					}else if(array[0].equals("ordernull")){
						txt += "\t\r\n" + array[1];
						orderflag = false;
					}
					order_info.setText(txt);
					
				}
			});
			query.setBounds(473, 22, 80, 32);
			query.setFocusPainted(false);
			query.setBorder(null);
			alre.add(query);
			
			order_info = new JTextArea();
			order_info.setFont(new Font("方正姚体", Font.PLAIN, 22));
			order_info.setForeground(new Color(138,138,138));
			order_info.setBounds(115, 100, 557, 328);
			alre.add(order_info);
			order_info.setColumns(10);
			
			JButton cancel = new JButton(new ImageIcon("factors2/cancel.png"));
			cancel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String order = ordernum.getText();
					
					if(orderflag) {
						cg.tcpsend("cancel&" + order);//发送取消订单请求
						String answer ="";
						
						try {
							answer = cg.br.readLine();
						} catch (IOException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
						String[] array = answer.split("&");
						JOptionPane.showMessageDialog(null, array[1] , "提示", JOptionPane.ERROR_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "无此订单信息" , "提示", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});
			cancel.setFocusPainted(false);
			cancel.setBorder(null);
			cancel.setBounds(166, 456, 188, 58);
			alre.add(cancel);
			
			JButton order_back = new JButton(new ImageIcon("factors2/back2.png"));
			order_back.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					cl_PanelCenter.show(PanelCenter, "p1");
				}
			});
			order_back.setFocusPainted(false);
			order_back.setBorder(null);
			order_back.setBounds(416, 456, 188, 58);
			alre.add(order_back);
			return PanelCenter;
	}
		
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client_gui clg = new client_gui(new client("127.0.0.1",5000));
					clg.cg.tcpsend("startinfo"+"&"+"client of " + clg.cg.hashCode() +" is in");	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
