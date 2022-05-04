package com.yedam.forecast.service.vo;

import lombok.Data;

@Data
public class WeatherInfo {
	// announceTime 발표시간 - 필수
	private long announceTime;
	// numEf 발표번호 - 옵션
	private long numEf;
	// regId 예보구역코드 - 필수
	private String regId;
	// rnSt 강수확률 - 옵션
	private long rnSt;
	// rnYn 강수형태 - 필수
	private long rnYn;
	// ta 예상기온 - 옵션
	private String ta;
	// wf 날씨 - 필수
	private String wf;
	// wfCd 하늘상태 - 필수
	private String wfCd;
	// wsIt 풍속강도코드 - 필수
	private String wsIt;

}
