package feature;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

import org.json.simple.parser.ParseException;

class form extends JFrame{
	
	//Image setsize = background.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
	static JPanel Now = new JPanel();
	static JPanel Today = new JPanel();
	static JLabel ma = new JLabel();
	static JPanel T1 = new JPanel();
	static JPanel T2 = new JPanel();
	static JLabel T1_DAY = new JLabel();
	static JLabel T1_TEM = new JLabel();
	static JLabel T1_SKY = new JLabel();
	static JLabel T2_DAY = new JLabel();
	static JLabel T2_TEM = new JLabel();
	static JLabel T2_SKY = new JLabel();
	Image background = new ImageIcon(form.class.getResource("/image/back2.jpg")).getImage();
	public form() {
		setTitle("실습 10번");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c=getContentPane();
		c.setLayout(null);
		Now.setSize(400, 300);
		Now.setLocation(400,0);
		Now.setBackground(new Color(0,0,0,122)); // Transparent
		Now.setLayout(null);
		
		Today.setSize(1200, 200);
		Today.setLocation(0,350);
		Today.setBackground(new Color(0,0,0,122)); // Transparent
		Today.setLayout(null);
		// T1
		T1.setSize(200, 190);
		T1.setLocation(5,5);
		//T1.setBackground(new Color(255,255,255,122)); // Transparent
		//T1.setLayout(null);

		
		T1_DAY.setSize(100,10);
		T1_DAY.setFont(new Font("Serif", Font.PLAIN, 10));
		T1_DAY.setLocation(10,10);
		T1_DAY.setText("T1");
		T1_TEM.setSize(100,10);
		T1_TEM.setFont(new Font("Serif", Font.PLAIN, 10));
		T1_TEM.setLocation(10,40);
		T1_TEM.setText("T1 TEM");
		T1.add(T1_DAY);
		T1.add(T1_TEM);
		// T2
		T2.setSize(200, 190);
		T2.setLocation(210,5);
		//T2.setBackground(new Color(255,255,255,122)); // Transparent
		T2.setLayout(new GridLayout(3,3));

		T2_DAY.setSize(100,10);
		T2_DAY.setFont(new Font("Serif", Font.PLAIN, 10));
		T2_DAY.setLocation(10,10);
		T2_DAY.setText("T2");
		
		T2_TEM.setSize(100,10);
		T2_TEM.setFont(new Font("Serif", Font.PLAIN, 10));
		T2_TEM.setLocation(10,40);
		T2_TEM.setText("T2 TEM");
		T2.add(T2_DAY);
		T2.add(T2_TEM);
		// 24시간
		ma.setText("a");
		ma.setForeground(Color.WHITE);
		ma.setFont(new Font("Serif", Font.BOLD, 30));
		ma.setSize(100, 30);
		c.add(Now);
		c.add(Today);
		Now.add(ma);
		Today.add(T1);
		Today.add(T2);
		setSize(1200,800);
		setVisible(true);
	}
	class logic extends weather {
		public void today_logic() {
			
		}
	}
	class background extends form {
		public void paint(Graphics g) {//그리는 함수
			background.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
			g.drawImage(background, 0, 0, null);//background를 그려줌
		}
	}
	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		//new form();
		feature.weather.getapi();
		
		
	}
}






