package com.yedam.forecast.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.yedam.forecast.service.vo.WeatherInfo;

import openApi.com.yedam.common.PropertiesPair;

public class ForecastService {
	// 동네예보 - 육상예보조회
	public static List<WeatherInfo> getWeatherForecast() {
		String key = "2g/0ruZMmeBtnGAmvfzhrhA3jjDw5qrxJGXobIeTn5g4UL08k5jFAg508CtJNBJJkv2zNQO9SSW/OZfZgshwKA==";
		// 이 URL은 계발계정 상세보기 중간쯤에 있는 일반인증키(Decoding) 주소를 복사해서 들고온것

		String serviceURL = "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?";
		// 서비스URL에는 항상 맨 끝에 ?를 붙일것
		// 이 URL은 기상청 상세보기에 들어가서 육상예보조회로 바꾼후 조회누르고 요청주소를 들고온것

		List<PropertiesPair> params = new ArrayList<PropertiesPair>();

//	ServiceKey
//	pageNo
//	numOfRows
//	dataType
//	regId
//이 5개는 기상청에서 상세보기에 들어가있는 요청변수를 들고온것
		params.add(new PropertiesPair("ServiceKey", key));
		params.add(new PropertiesPair("pageNo", "1"));
		params.add(new PropertiesPair("numOfRows", "10"));
		params.add(new PropertiesPair("dataType", "JSON"));
		params.add(new PropertiesPair("regId", "11A00101"));

		StringBuilder sb = new StringBuilder();

		try {
			String paramURL = PropertiesPair.getQuery(params);

			String requestURL = serviceURL + paramURL;
			URL url = new URL(requestURL);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			// 헤더정보추가
			con.setRequestMethod("GET"); // GET이 디폴트값
			con.setRequestProperty("Content-type", "application/json");
			// 헤더를 구성할때 여러가지정보를 줘야할대 있는데 그때 포괄적으로 사용하는데 Content-type은 Http에서 정해준것이라 써야함
			// 파일을 보낼때 Content-type 사용하면됨

			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
			} else {
				System.out.println(con.getResponseMessage());
			}
			con.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		String jsonResult = sb.toString();
	//	System.out.println(jsonResult);

		return getWeatherData(jsonResult);
	}

	// gson이랑 simple json이랑 다름 simple json은 한번에 출력불가
	private static List<WeatherInfo> getWeatherData(String jsonData){
		List<WeatherInfo> list = new ArrayList<WeatherInfo>();
		try {
			JSONParser parser = new JSONParser();
			
			JSONObject forecastData = (JSONObject) parser.parse(jsonData); //parser가 반환하는건 jsonobject가 아니라 그냥 object임
			JSONObject response = (JSONObject) forecastData.get("response");
			JSONObject body = (JSONObject) response.get("body");
			JSONObject items = (JSONObject) body.get("items");
			JSONArray item = (JSONArray) items.get("item");
			
			for(int i=0; i<item.size(); i++) {
				JSONObject data = (JSONObject) item.get(i);
				WeatherInfo info = new WeatherInfo();
				
				// announceTime 발표시간 - 필수
				info.setAnnounceTime((long)data.get("announceTime"));
//	에러가뜬다면	info.setAnnounceTime(Long.parseLong(data.get("announceTime").toString()));
				// numEf 발표번호 - 옵션
				info.setNumEf((data.get("numEf") == null)? 0 : (long)data.get("numEf"));
				// regId 예보구역코드 - 필수
				info.setRegId((String)data.get("regId"));
				// rnSt 강수확률 - 옵션
				info.setRnSt((data.get("rnSt")==null)? 0 : (long)data.get("rnSt"));
				// rnYn 강수형태 - 필수
				info.setRnYn((long)data.get("rnYn"));
				// ta 예상기온 - 옵션
				info.setTa((String)data.get("ta"));
				// wf 날씨 - 필수
				info.setWf((String)data.get("wf"));
				// wfCd 하늘상태 - 필수
				info.setWfCd((String)data.get("wfCd"));
				// wsIt 풍속강도코드 - 필수
				info.setWsIt((String)data.get("wsIt"));
				
				list.add(info);
				
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return list;
	}
}
