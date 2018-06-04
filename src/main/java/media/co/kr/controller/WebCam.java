package media.co.kr.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;

import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.Likelihood;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.protobuf.ByteString;

/**
 * Handles requests for the application home page.
 */

@Controller
public class WebCam {
	// java(controller)
	@RequestMapping(value = "/capture.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView saveCanvasImage(
			@RequestParam(value = "imageBase64", defaultValue = "") String imageBase64)
			throws FileNotFoundException, IOException {
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		String face[] = new String[4];
		String tt ="" ;
		
		Map resultMap = new HashMap();
		Map result = new HashMap();
		try {
			String data = imageBase64.split(",")[1];
			byte[] imageBytes = Base64.decodeBase64(data);
			BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageBytes));
			ImageIO.write(bufImg, "png", new File("C:\\webcam\\test.png"));

		} catch (Exception e) {
			res.put("ret", -1);
			res.put("msg", "Cannot process due to the image processing error.");
	
		}
		String imageFilePath = "C:\\webcam\\test.png";
		List<AnnotateImageRequest> requests_face = new ArrayList<>();
		ByteString imgBytes = ByteString.readFrom(new FileInputStream(imageFilePath));

		Image img = Image.newBuilder().setContent(imgBytes).build();
		Feature feat_face = Feature.newBuilder().setType(Type.FACE_DETECTION).build();

		AnnotateImageRequest request_face = AnnotateImageRequest.newBuilder().addFeatures(feat_face).setImage(img)
				.build();

		requests_face.add(request_face);

		try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {

			BatchAnnotateImagesResponse response_face = client.batchAnnotateImages(requests_face);
			List<AnnotateImageResponse> responses_face = response_face.getResponsesList();

			for (AnnotateImageResponse res2 : responses_face) {
				if (res2.hasError()) {
					System.out.printf("Error: %s\n", res2.getError().getMessage());

				}

				// For full list of available annotations, see http://g.co/cloud/vision/docs
				for (FaceAnnotation annotation : res2.getFaceAnnotationsList()) {
					System.out.printf("anger: %s\njoy: %s\nssorow: %s\nsurprise: %s", // \nposition: %s",
							annotation.getAngerLikelihood(), annotation.getJoyLikelihood(),
							annotation.getSorrowLikelihood(), annotation.getSurpriseLikelihood());// ,
					// annotation.getBoundingPoly());
				 
					face[0]= annotation.getAngerLikelihood().toString();  
					face[1]= annotation.getJoyLikelihood().toString();
					face[2]= annotation.getSorrowLikelihood().toString();
					face[3]= annotation.getSurpriseLikelihood().toString();
					resultMap.put("anger", annotation.getAngerLikelihood());
	                   resultMap.put("joy", annotation.getJoyLikelihood());
	                   resultMap.put("sorrow", annotation.getSorrowLikelihood());
	                   resultMap.put("surprise", annotation.getSurpriseLikelihood());
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(face[0].equals("UNLIKELY")) {
			tt= "회원님은 약간 화나있습니다.미련한 자는 당장 분노를 터뜨리지만, 슬기로운 자는 모욕을 당해도 참습니다. 화내지마요 .";
		}else if(face[0].equals("POSSIBLE")) {
			tt= "회원님은 화가 치밀어 오르고있습니다. 한번 성을 냄으로써 오래 쌓은 공덕이 한꺼번에 무너진다. 화내지말라고요 . ";
		}else if(face[0].equals("LIKELY")) {
			tt= "회원님은 화가 치밀어 오르다 못해 분노하고 있습니다. 경노는 본심을 빼앗아 갑니다. 웃어요 .";
		}else if(face[0].equals("LIKELY")) {
			tt= "회원님은 화가 치밀어 오르다 못해 분노하고 있습니다. 경노는 본심을 빼앗아 갑니다. 웃어요 . ";
		}else if(face[0].equals("VERY_LIKELY")) {
			tt= "회원님은 화가 치밀어 오르다 못해 분노하고 있습니다. 경노는 본심을 빼앗아 갑니다. 웃어요 . ";
		}
		if(face[1].equals("UNLIKELY")) {
			if(tt.equals("")) {
				tt="회원님 무슨 기쁜일이 있습니까? 행복을 나눠주세요 ~ 감사합니다 하하하하";
			}else {
				tt += "그리고 무슨 기쁜일이 있나요?. 행복을 나눠주세요 ~ 감사합니다 하하하하";
			}
			
		}else if(face[1].equals("POSSIBLE")) {
			if(tt.equals("")) {
				tt="회원님 미소를 살짝 짓는 모습이 멋있네요. 하하하하. 사랑합니다. ";
			}else {
				tt += "그리고 행복한 일이 있나요? 웃는 모습이 보기좋아요. 하하하하. 사랑합니다. ";
			}
		}else if(face[1].equals("LIKELY")) {
			if(tt.equals("")) {
				tt="회원님 행복한 일이 있나요? 저도 같이 웃어요. 하하하하하하하하하";
			}else {
				tt += "그리고 행복한 모습이 매우 보기 좋아요. 저도 같이 웃어요.!!";
			}
		}else if(face[1].equals("LIKELY")) {
			if(tt.equals("")) {
				tt="회원님 너무 웃어도 병이랍니다. 하하하하";
			}else {
				tt += "그리고 화내고 웃으면 병걸려요 .";
			}
		}else if(face[1].equals("VERY_LIKELY")) {
			if(tt.equals("")) {
				tt="회원님 너무 웃어도 병이랍니다. 하하하하";
			}else {
				tt += "그리고 화내고 웃으면 병걸려요 .";
			}
		}
		if(face[2].equals("UNLIKELY")) {
			if(tt.equals("")) {
				tt="회원님 적당한 슬픔은 인간에게 진지한 생각의 습관과 깊은 이해력, 그리고 부드러운 마음을 가져다줍니다. 화이팅 ! ";
			}else {
				tt += "그리고 회원님~ 적당한 슬픔은 인간에게 진지한 생각의 습관과 깊은 이해력, 또한 부드러운 마음을 가져다줍니다. 화이팅 !";
			}
		}else if(face[2].equals("POSSIBLE")) {
			if(tt.equals("")) {
				tt="회원님 바쁜 사람은 눈물을 흘릴 시간이 없습니다. 슬퍼하지마세요 ! ";
			}else {
				tt += "그리고 회원님 바쁜 사람은 눈물을 흘릴 시간이 없습니다. 슬퍼하지마세요 !";
			}
		}else if(face[2].equals("LIKELY")) {
			if(tt.equals("")) {
				tt="회원님 바쁜 사람은 눈물을 흘릴 시간이 없습니다. 슬퍼하지마세요 !";
			}else {
				tt += "회원님 바쁜 사람은 눈물을 흘릴 시간이 없습니다. 슬퍼하지마세요 !";
			}
		}else if(face[2].equals("VERY_LIKELY")) {
			if(tt.equals("")) {
				tt="회원님 바쁜 사람은 눈물을 흘릴 시간이 없습니다. 슬퍼하지마세요 ! 화이팅 !";
			}else {
				tt += "회원님 바쁜 사람은 눈물을 흘릴 시간이 없습니다. 슬퍼하지마세요 ! 화이팅 !";
			}
		}
		if(face[3].equals("UNLIKELY")) {
			if(tt.equals("")) {
				tt="회원님 놀라운일이 있나요 ? 저도 알려주세요 !";
			}else {
				tt += "그리고회원님 놀라운일이 있나요 ? 저도 알려주세요 !";
			}
		}else if(face[3].equals("POSSIBLE")) {
			if(tt.equals("")) {
				tt="회원님 놀라운일이 있나요 ? 저도 알려주세요 ! ";
			}else {
				tt += "그리고 회원님 놀라운일이 있나요 ? 저도 알려주세요 !";
			}
		}else if(face[3].equals("LIKELY")) {
			if(tt.equals("")) {
				tt="회원님 놀라운일이 있나요 ? 저도 알려주세요 ! ";
			}else {
				tt += "그리고 회원님 놀라운일이 있나요 ? 저도 알려주세요 !";
			}
		}else if(face[3].equals("VERY_LIKELY")) {
			if(tt.equals("")) {
				tt="회원님 놀라운일이 있나요 ? 저도 알려주세요 !";
			}else {
				tt += "그리고 회원님 놀라운일이 있나요 ? 저도 알려주세요 !";
			}
		}
	
		ModelAndView modelAndView = new ModelAndView("jsonView", resultMap);
		
		
	////mp3 시작
			StringBuffer reqStr = new StringBuffer();
			String clientId = "ws8W9dKlzCrzgFVYlgNf";// 애플리케이션 클라이언트 아이디값";
			String clientSecret = "mLWbRJXN4W";// 애플리케이션 클라이언트 시크릿값";
			if(face[0] !="") {
				System.out.println("음성시작");
			
			try {

				String text = URLEncoder.encode(tt, "UTF-8"); // 13자
				String apiURL = "https://openapi.naver.com/v1/voice/tts.bin";
				URL url = new URL(apiURL);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("X-Naver-Client-Id", clientId);
				con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
				// post request
				String postParams = "speaker=mijin&speed=0&text=" + text;
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(postParams);
				wr.flush();
				wr.close();
				int responseCode = con.getResponseCode();
				BufferedReader br;
				if (responseCode == 200) { // 정상 호출
					System.out.println("정상호출");
					InputStream is = con.getInputStream();
					int read = 0;
					byte[] bytes = new byte[1024];
					// 랜덤한 이름으로 mp3 파일 생성
					// String tempname = Long.valueOf(new Date().getTime()).toString();
					File f = new File("C:\\webcam\\test.wav");
					f.createNewFile();
					OutputStream outputStream = new FileOutputStream(f);
					while ((read = is.read(bytes)) != -1) {
						outputStream.write(bytes, 0, read);
					}
					is.close();
				} else { // 에러 발생
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
					String inputLine;
					StringBuffer response1 = new StringBuffer();
					while ((inputLine = br.readLine()) != null) {
						response1.append(inputLine);
					}
					br.close();
					System.out.println(response1.toString());
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			}
		
		return modelAndView;

	}

	@RequestMapping("/webcam.do")
	public String webcam() throws ClassNotFoundException, SQLException {
		return "home.webcam";
	}
}
