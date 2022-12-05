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
// https://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa?serviceKey=NPTCngB4moFVqeRUE7mXY9ngffup6u%2Bx6gsGmA1K0yFzOKfgLC8OQqPF8YJyWlp15IIwI6O4aPh64H7%2F3x0I1w%3D%3D&pageNo=1&numOfRows=10&dataType=JSON&regId=11B10101&tmFc=202212030600
public class day extends form {
	static String TMP_MAX;
	static String TMP_MIN;
	public static void getday() throws IOException, ParseException
	{
	    String tmFc = "202212040600"; // 예보시간 yyyy mm dd hh minmin
	    String type = "json";
	    String Key = "NPTCngB4moFVqeRUE7mXY9ngffup6u%2Bx6gsGmA1K0yFzOKfgLC8OQqPF8YJyWlp15IIwI6O4aPh64H7%2F3x0I1w%3D%3D";
	    String page = "1";
	    String row = "10";
	    String regid = "11B10101"; // 예보구역코드
	    String Url = ("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa?" + "serviceKey=" +
	    Key + "&pageNo=" + page + "&numOfRows=" + row + "&dataType=" + type + "&regId=" + regid +"&tmFc=" + tmFc);
	    URL url = new URL(Url);
	    String result = "";
	    System.out.print(url);
	    
	    // API 호출 부 START
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
        // API 호출 부 END
        
        
        JSONParser parser = new JSONParser(); // 객체 선언
        JSONObject top = (JSONObject) parser.parse(result); // top 객체에 result 값 삽입
        JSONObject response = (JSONObject) top.get("response"); // top 객체에서 response 찾아감
		JSONObject body = (JSONObject) response.get("body"); // response 객체에서 body 찾아감
		JSONObject items = (JSONObject) body.get("items"); // body 객체에서 items 찾아감
		JSONArray item = (JSONArray) items.get("item"); // items 객체의 item 배열 찾아 저장
		
		
		JSONObject Day = (JSONObject) item.get(0); // weather 객체에 item i 배열 저장
		for (int i=3; i<=7; i++) {
			Number MAX = (Number) Day.get("taMax" + i);
			Number MIN = (Number) Day.get("taMin" + i);
			TMP_MAX = MAX.toString();
			TMP_MIN = MIN.toString();
			form.Day_TMP[i].setText(TMP_MAX + "/"+ TMP_MIN);
			
			System.out.println("Day " + i + " TMP MAX = " + TMP_MAX);
			System.out.println("Day " + i + " TMP MIX = " + TMP_MIN);
		}
		form.Today.repaint();

	}

}
