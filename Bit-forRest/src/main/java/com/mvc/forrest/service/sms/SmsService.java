package com.mvc.forrest.service.sms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Service
public class SmsService{

	public String makeSignature(String url, String timestamp, String method, String accessKey, String secretKey )throws Exception {
//		String space = " ";					
//		String newLine = "\n";					
//		String method = "GET";					
//		String url = "https://sens.apigw.ntruss.com/sms/v2/"
//				+ "services/ncp:sms:kr:285705455384:forrest\r\n"
//				+ "/messages";
//		String accessKey = "L5CjqUAH9JbC4lkHKeEx";
//		String secretKey = "vzZDYJ0qxHV69VqVPXekeDVUkp4L1raqizvaKq7H";
//
//		String message = new StringBuilder()
//			.append(method)
//			.append(space)
//			.append(url)
//			.append(newLine)
//			.append(timestamp)
//			.append(newLine)
//			.append(accessKey)
//			.toString();
//
//		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
//		Mac mac = Mac.getInstance("HmacSHA256");
//		mac.init(signingKey);
//
//		byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
//		String encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
//		
//		System.out.println("encodeBase64String : "+encodeBase64String);
//	  return encodeBase64String;
	    String space = " ";                    // one space
	    String newLine = "\n";                 // new line
	    

	    String message = new StringBuilder()
	        .append(method)
	        .append(space)
	        .append(url)
	        .append(newLine)
	        .append(timestamp)
	        .append(newLine)
	        .append(accessKey)
	        .toString();

	    SecretKeySpec signingKey;
	    String encodeBase64String;
		try {
			
			signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
		    encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			encodeBase64String = e.toString();
		}
	    

	  return encodeBase64String;

	}
	
	public void makeMassage() throws Exception{
		
//		String reqURL = "https://sens.apigw.ntruss.com/sms/v2/"
//				+ "services/ncp:sms:kr:285705455384:forrest"
//				+ "/messages";	
//		
//		JsonObject bodyJson = new JsonObject();
//		JsonObject toJson = new JsonObject();
//		JsonArray toArr = new JsonArray();
//		
//		bodyJson.addProperty("type", "SMS");
//		bodyJson.addProperty("from", "01033294534");
//		bodyJson.addProperty("content", "인증번호 : ");
//		bodyJson.add("messages", toArr);
//		
//		toJson.addProperty("subject", "[forREST]");
//		toJson.addProperty("to", phone);
//		toArr.add(toJson);
//		
//		String body = bodyJson.toString();
//		
//		URL url = new URL(reqURL);
//		System.out.println("  url : "+url);
//		
//		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//		conn.setDoOutput(true);
//		conn.setDoInput(true);
//		conn.setRequestProperty("Content-Type", "application/json");
//		conn.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
//		conn.setRequestProperty("x-ncp-iam-access-key", "L5CjqUAH9JbC4lkHKeEx");
//		conn.setRequestProperty("x-ncp-apigw-signature-v2", signature);
//		conn.setRequestMethod("POST");
//		conn.setDoOutput(true);
//		
//		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
//		wr.write(body.getBytes());
//		wr.flush();
//		wr.close();
//		
//		int responseCode = conn.getResponseCode();
//		System.out.println("responseCode : " + responseCode);
//		
//////////////////////////////////여기까지 request//////////////////////////////////
//		
//		BufferedReader br;
//		String line = "";
//		String result = "";
//		
//		
//		 if(responseCode==202) { // 정상 호출
//             br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//         } else {  // 에러 발생
//             br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//             while ((line = br.readLine()) != null) {
//     			result += line;
//     		}
//     		System.out.println("response body : " + result);
//
//         }
//
//         String inputLine;
//         StringBuffer response = new StringBuffer();
//         while ((inputLine = br.readLine()) != null) {
//             response.append(inputLine);
//         }
//         br.close();
		String hostNameUrl = "https://sens.apigw.ntruss.com";     		// 호스트 URL
		String requestUrl= "/sms/v2/services/";                   		// 요청 URL
		String requestUrlType = "/messages";                      		// 요청 URL
		String accessKey = "L5CjqUAH9JbC4lkHKeEx";                     	// 네이버 클라우드 플랫폼 회원에게 발급되는 개인 인증키			// Access Key : https://www.ncloud.com/mypage/manage/info > 인증키 관리 > Access Key ID
		String secretKey = "vzZDYJ0qxHV69VqVPXekeDVUkp4L1raqizvaKq7H";  // 2차 인증을 위해 서비스마다 할당되는 service secret key	// Service Key : https://www.ncloud.com/mypage/manage/info > 인증키 관리 > Access Key ID	
		String serviceId = "ncp:sms:kr:285705455384:forrest";       // 프로젝트에 할당된 SMS 서비스 ID							// service ID : https://console.ncloud.com/sens/project > Simple & ... > Project > 서비스 ID
		String method = "POST";											// 요청 method
		String timestamp = Long.toString(System.currentTimeMillis()); 	// current timestamp (epoch)
		requestUrl += serviceId + requestUrlType;
		String apiUrl = hostNameUrl + requestUrl;
		
		// JSON 을 활용한 body data 생성
		JSONObject bodyJson = new JSONObject();
		JSONObject toJson = new JSONObject();
	    JSONArray  toArr = new JSONArray();

	    //toJson.put("subject","");							// Optional, messages.subject	개별 메시지 제목, LMS, MMS에서만 사용 가능
	    //toJson.put("content","sms test in spring 111");	// Optional, messages.content	개별 메시지 내용, SMS: 최대 80byte, LMS, MMS: 최대 2000byte
	    toJson.put("to","01093512557");						// Mandatory(필수), messages.to	수신번호, -를 제외한 숫자만 입력 가능
	    toArr.put(toJson);
	    
	    bodyJson.put("type","SMS");							// Madantory, 메시지 Type (SMS | LMS | MMS), (소문자 가능)
	    //bodyJson.put("contentType","");					// Optional, 메시지 내용 Type (AD | COMM) * AD: 광고용, COMM: 일반용 (default: COMM) * 광고용 메시지 발송 시 불법 스팸 방지를 위한 정보통신망법 (제 50조)가 적용됩니다.
	    //bodyJson.put("countryCode","82");					// Optional, 국가 전화번호, (default: 82)
	    bodyJson.put("from","01033294534");					// Mandatory, 발신번호, 사전 등록된 발신번호만 사용 가능		
	    //bodyJson.put("subject","");						// Optional, 기본 메시지 제목, LMS, MMS에서만 사용 가능
	    bodyJson.put("content","sms test in spring 222");	// Mandatory(필수), 기본 메시지 내용, SMS: 최대 80byte, LMS, MMS: 최대 2000byte
	    bodyJson.put("messages", toArr);					// Mandatory(필수), 아래 항목들 참조 (messages.XXX), 최대 1,000개
	    
	    //String body = bodyJson.toJSONString();
	    String body = bodyJson.toString();
	    
	    System.out.println(body);
	    
        try {
            URL url = new URL(apiUrl);

            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("content-type", "application/json");
            con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
            con.setRequestProperty("x-ncp-iam-access-key", accessKey);
            con.setRequestProperty("x-ncp-apigw-signature-v2", makeSignature(requestUrl, timestamp, method, accessKey, secretKey));
            con.setRequestMethod(method);
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            
            wr.write(body.getBytes());
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            System.out.println("responseCode" +" " + responseCode);
            if(responseCode == 202) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else { // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            
            System.out.println(response.toString());

        } catch (Exception e) {
            System.out.println(e);
        }

		

	}
	
}
