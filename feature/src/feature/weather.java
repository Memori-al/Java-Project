package feature;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.*;
import javax.swing.event.*;


public class weather extends form
{
	static String category = "";
	public static String value = "";
	static String time = "";
    static String baseDate = "20221112";	//조회하고싶은 날짜
    static String baseTime = "2000";	//조회하고싶은 시간
    static String result;
	public static void getapi() throws IOException, ParseException
	{
	    String nx = "63";	//위도
	    String ny = "89";	//경도
	    String type = "json";	//조회하고 싶은 type(json, xml 중 고름)
	    String Key = "NPTCngB4moFVqeRUE7mXY9ngffup6u%2Bx6gsGmA1K0yFzOKfgLC8OQqPF8YJyWlp15IIwI6O4aPh64H7%2F3x0I1w%3D%3D";
	    String page = "1";
	    String row = "500";
	    String Url = ("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?" + "serviceKey=" +
	    Key + "&pageNo=" + page + "&numOfRows=" + row + "&dataType=" + type + "&base_date=" + baseDate + "&base_time=" + baseTime + "&nx=" + nx + "&ny=" + ny);
        /*StringBuilder urlBuilder = new StringBuilder(Url);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+Key);
        urlBuilder.append("&pageNo=1&numOfRows=10&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(type, "UTF-8"));	
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); //경도
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); //위도

	    URL url = new URL(urlBuilder.toString());*/
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
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        result= sb.toString();
        JSONParser parser = new JSONParser(); // 객체 선언
        JSONObject obj = (JSONObject) parser.parse(result); // result 값 obj 객체화
        JSONObject response = (JSONObject) obj.get("response"); // obj 객체에서 response 찾아 파싱
		JSONObject body = (JSONObject) response.get("body"); // response 객체에서 body 찾아 파싱
		JSONObject items = (JSONObject) body.get("items"); // body 객체에서 items 찾아 파싱
		JSONArray item = (JSONArray) items.get("item"); // items 객체에서 item 배열 찾아 파싱
		/*
		단기예보
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
		form gui = new form();
		for(int i = 0 ; i < item.size(); i++) {
			//System.out.println(i);	
			JSONObject weather = (JSONObject) item.get(i);
			System.out.println(weather.toString());
			time = (String) weather.get("baseTime");
			//System.out.println("시간 : " + time + "시");
			category = (String) weather.get("category");
			value = (String) weather.get("fcstValue");
			if (time.equals(baseTime)) {
				if (category.equals("TMP")) { 
					System.out.println("기온 : " + value + "℃"); 
					gui.T2_TEM.setText(value);
					gui.T2_DAY.setText("아놔");
				}
				if (category.equals("UUU")) { 
						System.out.println("퐁속 : " + value + "m/s");
				}
				if (category.equals("POP")) { System.out.println("강수확룰 : " + value + "%"); }
			}
			//if (category.equals("SKY")) { System.out.println("강수량 = " + value + "mm"); }
			//if (category.equals("PCP")) { System.out.println("강수량 = " + value + "mm"); }
			//if (category.equals("TMN")) { System.out.println("최저기온 = " + value + "℃"); }
			//if (category.equals("TMX")) { System.out.println("최고기온 = " + value + "℃"); }
		}
	
	
    }
	
}