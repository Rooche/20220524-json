package openApi.com.yedam.movieVO;

import java.util.List;

import lombok.Data;

@Data
public class BoxOfficeInfo {
	private String boxofficeType;
	private String showRange;
	private List<MovieInfo> dailyBoxOfficeList;

}
