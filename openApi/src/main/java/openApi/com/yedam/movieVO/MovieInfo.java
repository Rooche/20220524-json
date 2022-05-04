package openApi.com.yedam.movieVO;

import java.util.Date;

import lombok.Data;

@Data
public class MovieInfo {
				private int runm;
				private int rank;
				private int rankInten;
				private String rankOldAndNew;
				private String movieCd;
				private String movieNm;
				private Date openDt;
				private Long salesAmt;
				private double salesShare;
				private Long salesInten;
				private double salesChange;
				private Long salesAcc;
				private Long audiCnt;
				private Long audiInten;
				private double audiChange;
				private Long audiAcc;
				private Long scrnCnt;
				private Long showCnt;
			
		}
