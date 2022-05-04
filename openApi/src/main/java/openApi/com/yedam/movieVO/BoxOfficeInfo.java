package openApi.com.yedam.movieVO;

import java.util.List;

import lombok.Data;

@Data
public class BoxOfficeInfo {
	private String boxOfficeType;
	private String showRange;
	private List<MovieInfo> dailyBoxOfficeList;

}
