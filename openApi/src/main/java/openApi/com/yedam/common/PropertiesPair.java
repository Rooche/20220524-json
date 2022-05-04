package openApi.com.yedam.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertiesPair {
		private String key;
		private String value;
		
		public static String getQuery(List<PropertiesPair> params) throws UnsupportedEncodingException {
			StringBuilder sb = new StringBuilder();
			boolean isFirst = true;
			
			for(PropertiesPair parms : params) {
				if(isFirst) {
					isFirst = false;
				}else {
					sb.append("&");
				}
				
				sb.append(URLEncoder.encode(parms.getKey(), "UTF-8"));
				sb.append("=");
				sb.append(URLEncoder.encode(parms.getValue(), "UTF-8"));
			}
			return sb.toString();
		}
		
}
