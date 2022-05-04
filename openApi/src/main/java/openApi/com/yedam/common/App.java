package openApi.com.yedam.common;

import java.util.List;

import openApi.com.yedam.movie.service.MovieService;
import openApi.com.yedam.movieVO.MovieInfo;

public class App {

	public static void main(String[] args) {
	//	MovieService.getDailyBoxOfficeResult();
		List<MovieInfo> list = MovieService.getDailyBoxOfficeResult();
		for(MovieInfo info : list) {
			System.out.println(info);
		}

	}

}
