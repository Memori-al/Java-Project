package feature;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.*;
import java.io.FileWriter;


public class weather extends form
{
	static LocalTime now = LocalTime.now();
	static String category = "";
	public static String tmp_value = "";
	public static String pop_value = "";
	public static String sky_value = "";
	public static int NT_INT = now.getHour();
	public static int NOW_INT = 0;
	public static String NT_ST = "";
	public static String NOW = "";
	static String time = "";
    static String baseDate = "20221115";
    static String baseTime = "0500";
    static String result;
    
	public static void getapi() throws IOException, ParseException
	{
		NT_INT = (NT_INT * 100)+100;
		System.out.println("NOW " + NT_INT);
	    String nx = "63";	//위도
	    String ny = "89";	//경도
	    String type = "json";
	    String Key = "NPTCngB4moFVqeRUE7mXY9ngffup6u%2Bx6gsGmA1K0yFzOKfgLC8OQqPF8YJyWlp15IIwI6O4aPh64H7%2F3x0I1w%3D%3D";
	    String page = "1";
	    String row = "100";
	    String Url = ("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?" + "serviceKey=" +
	    Key + "&pageNo=" + page + "&numOfRows=" + row + "&dataType=" + type + "&base_date=" + baseDate + "&base_time=" + baseTime + "&nx=" + nx + "&ny=" + ny);
	    URL url = new URL(Url);
	    System.out.print(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("\n");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
       // FileWriter file = new FileWriter("c:/OS/" + baseTime + ".json");
		
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        result= sb.toString();
        JSONParser parser = new JSONParser(); // 객체 선언
        JSONObject top = (JSONObject) parser.parse(result); // top 객체에 result 값 삽입
        /*file.write(obj.toJSONString());
        file.flush();
		file.close();*/
        JSONObject response = (JSONObject) top.get("response"); // top 객체에서 response 찾아감
		JSONObject body = (JSONObject) response.get("body"); // response 객체에서 body 찾아감
		JSONObject items = (JSONObject) body.get("items"); // body 객체에서 items 찾아감
		JSONArray item = (JSONArray) items.get("item"); // items 객체의 item 배열 찾아 저장
		form gui = new form();
		
		int count = 0;
		int icount = 0;
		ImageIcon sun = new ImageIcon("src/image/sunny.png");
		ImageIcon cloudy = new ImageIcon("src/image/cloudy.png");
		ImageIcon cloudy2 = new ImageIcon("src/image/cloudy2.png");
		ImageIcon cloudy3 = new ImageIcon("src/image/cloudy3.png");
		ImageIcon rain = new ImageIcon("src/image/rain.png");
		for(int i = 0 ; i < item.size(); i++) {	
			JSONObject weather = (JSONObject) item.get(i); // weather 객체에 item i 배열 저장 
			time = (String) weather.get("fcstTime"); // weather 객체에서 FcstTime 값을 찾아 time 저장
			category = (String) weather.get("category"); // weather 객체에서 category 값을 찾아 category에 저장

			if (NT_INT == 0 ) { // NT_INT가 0보다 작을때
				NT_ST = "0000"; // NT_ST는 0000 자정
			} else { // NT_INT가 0이 아닐때
				//if (NT_ST.length() < 4) { // 0이 아니고 NT_ST 길이가 3일때
				if (NT_INT < 1000) {
					NT_ST = Integer.toString(NT_INT);
					NT_ST = "0" + NT_ST;
					//System.out.println("Num1 " + NT_ST + " Length = " + NT_ST.length());
				}
				//if (NT_ST.length() == 4){ // 0이 아니고 NT_ST 길이가 4이 아닐때
				if (NT_INT >= 1000) {
					NT_ST = Integer.toString(NT_INT);
					NT_ST = NT_ST;
					//System.out.println("Num " + NT_ST + " Length = " + NT_ST.length());
				}
			}
			
			System.out.println("CT : " + category + " TIME : " + time + " NT_ST : " + NT_ST);
			
			if (NT_ST.equals(time)) {
				if (category.equals("TMP")) { 
					tmp_value = (String) weather.get("fcstValue");
					count = count + 1;
					System.out.println("Category = " + category + " Time Verified! " + time + "\nTMP = " + tmp_value);
				}
				if (category.equals("SKY")) {
					sky_value = (String) weather.get("fcstValue");
					count = count + 1;
					System.out.println("Category = " + category + " Time Verified! " + time + "\nSKY = " + sky_value);
				}
				if(category.equals("POP")) {
					pop_value = (String) weather.get("fcstValue");
					count = count + 1;
					System.out.println(i + "Category = " + category + " Time Verified! " + time + "\nPOP = " + pop_value);
				}
				//

				if (count >= 3) {
					NT_INT = NT_INT + 100;
					count = 0;
					icount = icount + 1;
				}
				System.out.println("ic = " + icount);
				if (icount <= 8) {
					NOW_INT = NT_INT;
					for (int j=0; j<=24; j++) {
						int hours = j * 100;
						if (NOW_INT == hours) {
							if (hours < 1200) {
								NOW = "오전 " + j + "시";	
							} else {
								NOW = "오후 " + j + "시";
							}
							 
						}
					}
					gui.Today_Time[icount].setText(NOW);
					gui.Today_TMP[icount].setText(tmp_value + "℃");
					gui.Today_Image[icount].setText(sky_value);
					if (Today_Image[icount].equals("1")) {
						gui.Today_Image[icount].setIcon(sun);
					}
					if (sky_value.equals("1")) {
						gui.Today_Image[icount].setIcon(sun);
					}
					if (sky_value.equals("2")) {
						gui.Today_Image[icount].setIcon(sun);
					}
					if (sky_value.equals("3")) {
						gui.Today_Image[icount].setIcon(cloudy2);
					}
					if (sky_value.equals("4")) {
						gui.Today_Image[icount].setIcon(cloudy3);
					}
					if (sky_value.equals("5")) {
						gui.Today_Image[icount].setIcon(rain);
					}
					if (sky_value.equals("6")) {
						gui.Today_Image[icount].setIcon(rain);
					}
				}
			}
			
		}
		//gui.address_tmp.setText(tmp_value + "℃")
			

			/*
			단기예보 0200, 0500, 0800, 1100, 1400, 1700, 2000, 2300
				POP	강수확률	%	
				PTY	강수형태 : 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4) 
				PCP	1시간 강수량	범주 (1 mm)	
				REH	습도	%	8
				SNO	1시간 신적설	범주(1 cm)	
				SKY	하늘상태 : 맑음(1), 구름조금(2), 구름많음(3), 흐림(4)
				TMP	1시간 기온	℃	
				TMN	일 최저기온	℃
				TMX	일 최고기온	℃	
				UUU	풍속(동서성분)	m/s	
				VVV	풍속(남북성분)	m/s	
				WAV	파고	M	
				VEC	풍향	deg
				WSD	풍속	m/s	
			 */
		
	
	
    }
	
}