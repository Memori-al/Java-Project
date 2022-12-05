package feature;

import java.awt.*;
import java.awt.Image.*;
import java.io.IOException;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

import org.json.simple.parser.ParseException;

class form extends JFrame {
	static JPanel Now = new JPanel();
	static JPanel Day = new JPanel();
	static JPanel DayPane[] = new JPanel[24];
	static JLabel Days[] = new JLabel[24];
	static JLabel Day_Image[] = new JLabel[24];
	static JLabel Day_TMP[] = new JLabel[24];
	
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
	int Mouse_Checked;
	int wheel_count;
	public form() {
		setTitle("날씨");
		//setLocationRelativeTo(null);
		JLabel Background = new JLabel(img2);
		Background.setIcon(img2);
		Background.setSize(1280, 1024);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container main = getContentPane();
		main.setLayout(null);
		
		Now.setSize(400, 300);
		Now.setLocation(400,30);
		Now.setBackground(new Color(0,0,0,50)); // Transparent
		Now.setLayout(null);
		
		Day.setSize(1200, 170);
		Day.setLocation(30, 500);
		Day.setBackground(new Color(0,0,0,50));
		Day.setLayout(null);
		
		Today.setSize(1200, 170);
		Today.setLocation(30, 750);
		Today.setBackground(new Color(0,0,0,50));
		Today.setLayout(null);
		
		int xd = 1;
		int yd = 10;
		int x = 1;
		int y = 10;
		for (i=0; i<7; i++) {
			DayPane[i] = new JPanel();
			Days[i] = new JLabel(Integer.toString(i));
			Day_Image[i] = new JLabel(Integer.toString(i));
			Day_TMP[i] = new JLabel(Integer.toString(i));
			
			DayPane[i].setSize(150, 150);
			DayPane[i].setLayout(null);
			DayPane[i].setBackground(new Color(0,0,0,0));
			
			if (i==0) {
				DayPane[i].setLocation(5, yd);
			} else {
				xd = xd + 170;
				DayPane[i].setLocation(xd, yd);
			}
			Days[i].setSize(100, 20);
			Days[i].setForeground(Color.WHITE);
			Days[i].setFont(new Font("나눔고딕", Font.PLAIN, 15));
			Days[i].setText(Integer.toString(i));
			Days[i].setLocation(70, 10);
			
			Day_Image[i].setSize(70, 70);
			Day_Image[i].setForeground(Color.WHITE);
			Day_Image[i].setFont(new Font("나눔고딕", Font.PLAIN, 15));
			//Today_Image[i].setText(Integer.toString(i));
			Day_Image[i].setLocation(65, 50);
			
			Day_TMP[i].setSize(100, 20);
			Day_TMP[i].setForeground(Color.WHITE);
			Day_TMP[i].setFont(new Font("나눔고딕", Font.PLAIN, 15));
			Day_TMP[i].setText(Integer.toString(i));
			Day_TMP[i].setLocation(70, 125);
			Day.add(DayPane[i]);
			DayPane[i].add(Days[i]);
			DayPane[i].add(Day_Image[i]);
			DayPane[i].add(Day_TMP[i]);
		}
		main.add(Day);
		
		for (i=0; i<24; i++) {
			TimePane[i] = new JPanel();
			Today_Time[i] = new JLabel(Integer.toString(i));
			Today_Image[i] = new JLabel(Integer.toString(i));
			Today_TMP[i] = new JLabel(Integer.toString(i));
			
			TimePane[i].setSize(150, 150);
			TimePane[i].setLayout(null);
			TimePane[i].setBackground(new Color(0,0,0,0));
			// 0 - 6, 7 - 13, 14 - 20, 21 - 23
			if (i==0) {
				TimePane[i].setLocation(5, y);
			} else {
				if (i==7) {
					x = 1;
					TimePane[i].setLocation(5, y);
					//TimePane[i].setVisible(false);
				} else if(i==14) {
					x = 1;
					TimePane[i].setLocation(5, y);
				} else if(i==21) {
					x = 1;
					TimePane[i].setLocation(5, y);
				} else {
					x = x + 170;
					TimePane[i].setLocation(x, y);
				}
			}
			if (i >= 7) {
				TimePane[i].setVisible(false);
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
		//day day2 = new day();
		
		/*Today.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				int Mouse_Checked = 1;
				System.out.println("Mouse Entered!");
			}
			public void mouseExited(MouseEvent e) {
				int Mouse_Checked = 0;
				System.out.println("Mouse Exited!");
			}
		});*/
		wheel_count=0;
		Today.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int wheel=e.getWheelRotation();
				if (wheel_count == 0) {
					wheel_count = 1;
					for (i=0; i<=6; i++) {
						TimePane[i].setVisible(true);
					}
				}
				if (wheel > 0) { // 휠을 내렸을 때
					//System.out.println("Wheel is down");
					if (wheel_count == 1) {
						wheel_count = 2;
						for (i=0; i<=6; i++) {
							TimePane[i].setVisible(false);
						}
						for (i=7; i<=13; i++) {
							TimePane[i].setVisible(true);
						}
					}
					else if (wheel_count == 2) {
						for (i=7; i<=13; i++) {
							TimePane[i].setVisible(false);
						}
						for (i=14; i<=20; i++) {
							TimePane[i].setVisible(true);
						}
						wheel_count = 3;
					} else if (wheel_count == 3) {
						for (i=14; i<=20; i++) {
							TimePane[i].setVisible(false);
						}
						for (i=21; i<24; i++) {
							TimePane[i].setVisible(true);
						}
					}
				} else { // 휠을 올렸을 때
					if (wheel_count == 1) {
						wheel_count = 0;
						for (i=0; i<=6; i++) {
							TimePane[i].setVisible(true);
						}
						for (i=7; i<=13; i++) {
							TimePane[i].setVisible(false);
						}
					} 
					else if (wheel_count == 2) {
						wheel_count = 1;
						for (i=7; i<=13; i++) {
							TimePane[i].setVisible(true);
						}
						for (i=14; i<=20; i++) {
							TimePane[i].setVisible(false);
						}
					} 
					else if (wheel_count == 3) {
						wheel_count = 2;
						for (i=14; i<=20; i++) {
							TimePane[i].setVisible(true);
						}
						for (i=21; i<24; i++) {
							TimePane[i].setVisible(false);
						}
					}
				}
				repaint();
				//System.out.println("Wheel = " + wheel);
				//System.out.println("\n" + "Wheel Count = " + wheel_count);
				
			}
			
		});
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
		setSize(1280,1024);
		setVisible(true);
	}

	
	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
	
	//new form();
	feature.weather.getapi();
	feature.day.getday();
	
	
	}
}