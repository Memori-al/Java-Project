package feature;

import java.awt.*;
import java.awt.Image.*;
import java.io.IOException;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

import org.json.simple.parser.ParseException;

class form extends JFrame{
	
	static JPanel Now = new JPanel();
	static JPanel Today = new JPanel();
	static JPanel TimePane[] = new JPanel[24];
	static JLabel Today_Time[] = new JLabel[24];
	static JLabel Today_Image[] = new JLabel[24];
	static JLabel Today_TMP[] = new JLabel[24];
	static JLabel address_title = new JLabel();
	static JLabel address_tmp = new JLabel();
	//static JLabel address_tmps = new JLabel();
	static JLabel address_sky = new JLabel();
	static JLabel address_vvv = new JLabel();
	static JLabel address_pop = new JLabel();
	static ImageIcon img1 = new ImageIcon("src/image/back.jpg");
	static ImageIcon img2 = new ImageIcon("src/image/back2.jpg");
	
	int i;
	public form() {
		setTitle("날씨");
		//setLocationRelativeTo(null);
		JLabel Background = new JLabel(img2);
		Background.setIcon(img2);
		Background.setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container main = getContentPane();
		main.setLayout(null);
		
		Now.setSize(400, 300);
		Now.setLocation(400,30);
		Now.setBackground(new Color(0,0,0,50)); // Transparent
		Now.setLayout(null);
		Today.setSize(1200, 170);
		Today.setLocation(0, 500);
		Today.setBackground(new Color(0,0,0,50));
		Today.setLayout(null);
		
		int x = 1;
		int y = 10;
		int size=9;
		for (i=0; i<size; i++) {
			TimePane[i] = new JPanel();
			Today_Time[i] = new JLabel(Integer.toString(i));
			Today_Image[i] = new JLabel(Integer.toString(i));
			Today_TMP[i] = new JLabel(Integer.toString(i));
			
			TimePane[i].setSize(150, 150);
			TimePane[i].setLayout(null);
			TimePane[i].setBackground(new Color(0,0,0,0));
			if (i==0) {
				TimePane[i].setLocation(5, y);
			} else {
				x = x + 170;
				TimePane[i].setLocation(x, y);
			}
			
			Today_Time[i].setSize(100, 20);
			Today_Time[i].setForeground(Color.WHITE);
			Today_Time[i].setFont(new Font("나눔고딕", Font.PLAIN, 15));
			Today_Time[i].setText(Integer.toString(i));
			Today_Time[i].setLocation(70, 10);
			
			Today_Image[i].setSize(70, 70);
			Today_Image[i].setForeground(Color.WHITE);
			Today_Image[i].setFont(new Font("나눔고딕", Font.PLAIN, 15));
			//Today_Image[i].setText(Integer.toString(i));
			Today_Image[i].setLocation(65, 50);
			
			
			Today_TMP[i].setSize(100, 20);
			Today_TMP[i].setForeground(Color.WHITE);
			Today_TMP[i].setFont(new Font("나눔고딕", Font.PLAIN, 15));
			Today_TMP[i].setText(Integer.toString(i));
			Today_TMP[i].setLocation(70, 125);
			
			Today.add(TimePane[i]);
			TimePane[i].add(Today_Time[i]);
			TimePane[i].add(Today_Image[i]);
			TimePane[i].add(Today_TMP[i]);
		}

		main.add(Today);
		
		address_title.setText("전라북도 전주시");
		address_title.setLocation(100, 0);
		address_title.setForeground(Color.WHITE);
		address_title.setFont(new Font("나눔고딕", Font.BOLD, 30));
		address_title.setSize(250, 150);
		
		address_tmp.setText("7℃");
		address_tmp.setLocation(170, 65);
		address_tmp.setForeground(Color.WHITE);
		address_tmp.setFont(new Font("나눔고딕", Font.PLAIN, 80));
		address_tmp.setSize(250, 150);
		
		address_sky.setText("화창한 날씨");
		address_sky.setLocation(143, 123);
		address_sky.setForeground(Color.WHITE);
		address_sky.setFont(new Font("나눔고딕", Font.PLAIN, 25));
		address_sky.setSize(250, 150);
		
		main.add(Now);
		Now.add(address_title);
		Now.add(address_tmp);
		Now.add(address_sky);
		main.add(Background);
		setSize(1200,800);
		setVisible(true);
	}

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		
	//new form();
		feature.weather.getapi();
	}
}








