package feature;
import java.io.InputStreamReader;
import java.time.LocalDate;
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

public class weather extends form
{
	
	static LocalTime now = LocalTime.now();
	static LocalDate dow = LocalDate.now();
	static String category = "";
	public static String tmp_value = "";
	public static String pop_value = "0";
	public static String sky_value = "";
	public static String wsd_value = "";
	public static int NT_INT = now.getHour();
	public static int NOW_INT = 0;
	public static String NT_ST = "";
	public static String NOW = "";
	static String time = "";
	static String year = Integer.toString(dow.getYear());
	static String month = Integer.toString(dow.getMonthValue());
	static String day = Integer.toString(dow.getDayOfMonth()-1);
    static String baseDate =  "";
    static String baseTime = "";
    static String result;

    // 단기예보 0200, 0500, 0800, 1100, 1400, 1700, 2000, 2300
	public static void getapi() throws IOException, ParseException
	{
		NT_INT = (NT_INT * 100)+100;
		if (NT_INT == 100) { baseTime = "2300"; } 
		else if (NT_INT == 200) { baseTime = "2300"; }
		else if (NT_INT == 300) { baseTime = "0200"; }
		else if (NT_INT == 400) { baseTime = "0200"; }
		else if (NT_INT == 500) { baseTime = "0500"; }
		else if (NT_INT == 600) { baseTime = "0500"; }
		else if (NT_INT == 700) { baseTime = "0500"; }
		else if (NT_INT == 800) { baseTime = "0800"; }
		else if (NT_INT == 900) { baseTime = "0800"; }
		else if (NT_INT == 1000) { baseTime = "0800"; }
		else if (NT_INT == 1100) { baseTime = "1100"; }
		else if (NT_INT == 1200) { baseTime = "1100"; }
		else if (NT_INT == 1300) { baseTime = "1100"; }
		else if (NT_INT == 1400) { baseTime = "1400"; }
		else if (NT_INT == 1500) { baseTime = "1400"; }
		else if (NT_INT == 1600) { baseTime = "1400"; }
		else if (NT_INT == 1700) { baseTime = "1700"; }
		else if (NT_INT == 1800) { baseTime = "1700"; }
		else if (NT_INT == 1900) { baseTime = "1700"; }
		else if (NT_INT == 2000) { baseTime = "2000"; }
		else if (NT_INT == 2100) { baseTime = "2000"; }
		else if (NT_INT == 2200) { baseTime = "2000"; }
		else if (NT_INT == 2300) { baseTime = "2000"; }
		else if (NT_INT == 2400) { baseTime = "2300"; }
		if (NT_INT < 1200) {
			Background.setIcon(img1);
		} else {
			if (NT_INT < 1900) {
				Background.setIcon(img2);
			} else {
				Background.setIcon(img3);
			}
		}
		Background.setIcon(img3);
		if (day.length() == 1) {
			baseDate = year + month + "0" + day;
		} else {
			baseDate = year + month + day;
		}
		System.out.println("NOW " + NT_INT + "\nBASETIME = " + baseTime + "\nBASEDATE = " + baseDate);
	    String nx = "63";	//위도
	    String ny = "89";	//경도
	    String type = "json";
	    String Key = "NPTCngB4moFVqeRUE7mXY9ngffup6u%2Bx6gsGmA1K0yFzOKfgLC8OQqPF8YJyWlp15IIwI6O4aPh64H7%2F3x0I1w%3D%3D";
	    String page = "1";
	    String row = "400";
	    String Url = ("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?" + "serviceKey=" +
	    Key + "&pageNo=" + page + "&numOfRows=" + row + "&dataType=" + type + "&base_date=" + baseDate + "&base_time=" + baseTime + "&nx=" + nx + "&ny=" + ny);
	    URL url = new URL(Url);
	    
	    System.out.print(url);
	    
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        //System.out.println("\n");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
		
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        result= sb.toString();
        form gui = new form();
        
        JSONParser parser = new JSONParser(); // 객체 선언
        JSONObject top = (JSONObject) parser.parse(result); // top 객체에 result 값 삽입
        JSONObject response = (JSONObject) top.get("response"); // top 객체에서 response 찾아감
		JSONObject body = (JSONObject) response.get("body"); // response 객체에서 body 찾아감
		JSONObject items = (JSONObject) body.get("items"); // body 객체에서 items 찾아감
		JSONArray item = (JSONArray) items.get("item"); // items 객체의 item 배열 찾아 저장
		

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
			
			if (NT_INT == 2400) { // 자정일 때
			//if (NT_INT > 2400) {
				NT_ST = "0000";
				//System.out.println("NT_ST IS 0000");
			}
			if (NT_INT == 2500) { NT_ST = "0100"; }
			if (NT_INT == 2600) { NT_ST = "0200"; }
			if (NT_INT == 2700) { NT_ST = "0300"; }
			if (NT_INT == 2800) { NT_ST = "0400"; }
			if (NT_INT == 2900) { NT_ST = "0500"; }
			if (NT_INT == 3000) { NT_ST = "0600"; }
			if (NT_INT == 3100) { NT_ST = "0700"; }
			if (NT_INT == 3200) { NT_ST = "0800"; }
			if (NT_INT == 3300) { NT_ST = "0900"; }
			if (NT_INT == 3400) { 
				NT_INT = 1000;
				NT_ST = "1000"; 
			}
			// 여기서 인트는 0 st = 0000
			if (NT_INT < 2400){ // 자정이 아닐 때
				if (NT_INT >= 1000) { // 4자리(오전 10시 이상) 일때
					NT_ST = Integer.toString(NT_INT);
				} 
				if (NT_INT <= 900) { // 3자리(오전 9시 이하) 일때
					NT_ST = Integer.toString(NT_INT);
					NT_ST = "0" + NT_ST;
					
				}
			}
			//System.out.println("NT ST = " + NT_ST);
			System.out.println("CT : " + category + " TIME : " + time + " NT_ST : " + NT_ST + " NT_INT : " + NT_INT);
			
			if (NT_ST.equals(time)) {
				if (category.equals("TMP")) { 
					tmp_value = (String) weather.get("fcstValue");
					count = count + 1;
					//System.out.println("Category = " + category + " Time Verified! " + time + "TMP = " + tmp_value);
				}
				if (category.equals("SKY")) {
					sky_value = (String) weather.get("fcstValue");
					count = count + 1;
					//System.out.println("Category = " + category + " Time Verified! " + time + "\nSKY = " + sky_value);
				}
				if(category.equals("POP")) {
					pop_value = (String) weather.get("fcstValue");
					count = count + 1;
					//System.out.println(i + "Category = " + category + " Time Verified! " + time + "POP = " + pop_value);
				}
				if(category.equals("WSD")) {
					wsd_value = (String) weather.get("fcstValue");
					count = count + 1;
					//System.out.println(i + "Category = " + category + " Time Verified! " + time + "\nWSD = " + wsd_value);
				}
				//
				if (count >= 4) {
					count = 0;
					NT_INT = NT_INT + 100;
					icount = icount + 1;
					//System.out.println("INT = " + NT_INT);
				}
				//System.out.println("ic = " + icount);
				if (icount < 24) {
						if (NT_ST.equals("0000")) { NOW = "오전 12시"; }
						if (NT_ST.equals("0100")) { NOW = "오전 1시"; }
						if (NT_ST.equals("0200")) { NOW = "오전 2시"; }
						if (NT_ST.equals("0300")) { NOW = "오전 3시"; }
						if (NT_ST.equals("0400")) { NOW = "오전 4시"; }
						if (NT_ST.equals("0500")) { NOW = "오전 5시"; }
						if (NT_ST.equals("0600")) { NOW = "오전 6시"; }
						if (NT_ST.equals("0700")) { NOW = "오전 7시"; }
						if (NT_ST.equals("0800")) { NOW = "오전 8시"; }
						if (NT_ST.equals("0900")) { NOW = "오전 9시"; }
						if (NT_ST.equals("1000")) { NOW = "오전 10시"; }
						if (NT_ST.equals("1100")) { NOW = "오전 11시"; }
						if (NT_ST.equals("1200")) { NOW = "오후 12시"; }
						if (NT_ST.equals("1300")) { NOW = "오후 1시"; }
						if (NT_ST.equals("1400")) { NOW = "오후 2시"; }
						if (NT_ST.equals("1500")) { NOW = "오후 3시"; }
						if (NT_ST.equals("1600")) { NOW = "오후 4시"; }
						if (NT_ST.equals("1700")) { NOW = "오후 5시"; }
						if (NT_ST.equals("1800")) { NOW = "오후 6시"; }
						if (NT_ST.equals("1900")) { NOW = "오후 7시"; }
						if (NT_ST.equals("2000")) { NOW = "오후 8시"; }
						if (NT_ST.equals("2100")) { NOW = "오후 9시"; }
						if (NT_ST.equals("2200")) { NOW = "오후 10시"; }
						if (NT_ST.equals("2300")) { NOW = "오후 11시"; }
						gui.Today_POP[icount].setText(pop_value + " %");
						gui.Today_Time[icount].setText(NOW);
						gui.Today_TMP[icount].setText(tmp_value + " ℃");
						gui.Today_Image[icount].setText("");
						gui.Today_WSD[icount].setText(wsd_value + " m/s");

					if (sky_value.equals("1")) {
						gui.Today_Image[icount].setIcon(sun);
						gui.address_Image.setIcon(ncloudy1);
						address_sky.setText("화창한 날씨");
					}
					if (sky_value.equals("2")) {
						gui.Today_Image[icount].setIcon(cloudy);
						gui.address_Image.setIcon(ncloudy1);
						address_sky.setText("화창한 날씨");
					}
					if (sky_value.equals("3")) {
						gui.Today_Image[icount].setIcon(cloudy2);
						gui.address_Image.setIcon(ncloudy1);
						address_sky.setText("다소 흐린 날씨");
					}
					if (sky_value.equals("4")) {
						gui.Today_Image[icount].setIcon(cloudy3);
						gui.address_Image.setIcon(ncloudy1);
						address_sky.setText("다소 흐린 날씨");
					}
					if (sky_value.equals("5")) {
						gui.Today_Image[icount].setIcon(rain);
						gui.address_Image.setIcon(ncloudy1);
						address_sky.setText("눈 또는 비");
					}
					if (sky_value.equals("6")) {
						gui.Today_Image[icount].setIcon(rain);
						gui.address_Image.setIcon(ncloudy1);
						address_sky.setText("눈 또는 비");
					}

					if (icount == 0) {
						gui.address_tmp.setText(tmp_value + "℃");
						gui.address_wsd.setText("풍속 " + wsd_value + " m/s");
						gui.address_pop.setText("강수확률 " + pop_value + " %");
						gui.address_time.setText("마지막 업데이트: " + NOW);
					}

				}
			}
			
		}
			

			/*
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
