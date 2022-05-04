package openApi.com.yedam.movie.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import openApi.com.yedam.movieVO.MovieInfo;

public class MovieService {

	// 일별 박스 오피스
	public List<MovieInfo> getDailyBoxOfficeResult() {
		String serviceURL = " http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?"; //끝에 (json) ? 를 붙여야함
		//www.naver.com? 라고하면 ?뒤에는 정보를 주고 받는데 name = "홍길동" & "dept = "영업"
		//												key=value					get방식
		String paramURL = "";

		String requestURL = serviceURL + paramURL;
		StringBuilder sb = new StringBuilder();
		try {

			URL url = new URL(requestURL);

			HttpURLConnection con = (HttpURLConnection) url.openConnection(); //최상위 객체로 URL이 있고 그걸 다시 구현한게 HttpURL
			//URL은 단순한 인터넷 주소를 가지는것 연결은 했지만 정보를 주는건 아님
			//POST를 통해서 헤더와 바디를 구성해야하니 연결을 하고 구성을 한다
			//URL을 이용해서 접속하고자하는 서버 위치를 확인하고 어떤 정보를 보낼껀지 확인후 getResponseCode를 사용하여 서버가 반응했을때 코드로 반환함(반환 했다는것은 서버가 반응을 했다는거임)			
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				//OK인 경우에만 가공해야함 OK이외의 것이 응답왔다면
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream())); //reader스트림 보조용으로 input스트림을 붙임
				String line;
				while((line=br.readLine())!= null) {
					sb.append(line);
				}
				br.close();
			}else { //con.getResponseMessage()를 사용해주면 됨
				System.out.println(con.getResponseMessage());
			}
			con.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		String jsonResult = sb.toString();
	}
}
