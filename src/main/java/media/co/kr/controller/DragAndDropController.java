package media.co.kr.controller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.LocationInfo;
import com.google.cloud.vision.v1.WebDetection;
import com.google.cloud.vision.v1.WebDetection.WebEntity;
import com.google.cloud.vision.v1.WebDetection.WebImage;
import com.google.cloud.vision.v1.WebDetection.WebLabel;
import com.google.cloud.vision.v1.WebDetection.WebPage;
import com.google.gson.JsonObject;
import com.google.instrumentation.trace.Annotation;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.protobuf.ByteString;

/**
 * Handles requests for the application home page.
 */

@Controller
public class DragAndDropController {

	@RequestMapping(value = "/searchimage.do", method = RequestMethod.GET)
	public String dragAndDrop(Model model) {
		return "home.imagesearch";
	}

	@RequestMapping(value = "/drag.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public ModelAndView upload(MultipartHttpServletRequest multipartRequest, Model model, HttpServletResponse response)
			throws IOException { // Multipart로
		String tt = "";
		String entities[] = new String[11];
		String korean_label="";
		String clientId = "ws8W9dKlzCrzgFVYlgNf";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "mLWbRJXN4W";// 애플리케이션 클라이언트 시크릿값";
		// 받는다.
		Map resultMap = new HashMap();
		Map result = new HashMap();
		String originalFilename = null;
		Iterator<String> itr = multipartRequest.getFileNames();
		String filePath = "C:/test"; // 설정파일로 뺀다.
		while (itr.hasNext()) { // 받은 파일들을 모두 돌린다.
			MultipartFile mpf = multipartRequest.getFile(itr.next());
			String originFileName = mpf.getOriginalFilename();
			System.out.println("FILE_INFO: " + originFileName); // 받은 파일 리스트 출력'

			// MultipartFile mpf = multipartRequest.getFile(itr.next());

			originalFilename = mpf.getOriginalFilename(); // 파일명

			String fileFullPath = filePath + "/" + originalFilename; // 파일 전체 경로

			try {

				// 파일 저장
				mpf.transferTo(new File(fileFullPath)); // 파일저장 실제로는 service에서 처리
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("C:\\test" + mpf.getOriginalFilename()));

			} catch (Exception e) {
				System.out.println("postTempFile_ERROR======>" + fileFullPath);
				e.printStackTrace();
			}

		}
		try {

			String imageFilePath = "C:\\test\\" + originalFilename;
			List<AnnotateImageRequest> requests_text = new ArrayList<>();
			List<AnnotateImageRequest> requests_face = new ArrayList<>();
			List<AnnotateImageRequest> requests_landmark = new ArrayList<>();
			List<AnnotateImageRequest> requests_logo = new ArrayList<>();
			List<AnnotateImageRequest> requests_label = new ArrayList<>();
			List<AnnotateImageRequest> requests_web = new ArrayList<>();

			ByteString imgBytes = ByteString.readFrom(new FileInputStream(imageFilePath));

			Image img = Image.newBuilder().setContent(imgBytes).build();

			Feature feat_text = Feature.newBuilder().setType(Type.TEXT_DETECTION).build();
			Feature feat_face = Feature.newBuilder().setType(Type.FACE_DETECTION).build();
			Feature feat_landmark = Feature.newBuilder().setType(Type.LANDMARK_DETECTION).build();
			Feature feat_logo = Feature.newBuilder().setType(Type.LOGO_DETECTION).build();
			Feature feat_label = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
			Feature feat_web = Feature.newBuilder().setType(Type.WEB_DETECTION).build();

			AnnotateImageRequest request_text = AnnotateImageRequest.newBuilder().addFeatures(feat_text).setImage(img)
					.build();
			AnnotateImageRequest request_face = AnnotateImageRequest.newBuilder().addFeatures(feat_face).setImage(img)
					.build();
			AnnotateImageRequest request_landmark = AnnotateImageRequest.newBuilder().addFeatures(feat_landmark)
					.setImage(img).build();
			AnnotateImageRequest request_logo = AnnotateImageRequest.newBuilder().addFeatures(feat_logo).setImage(img)
					.build();
			AnnotateImageRequest request_label = AnnotateImageRequest.newBuilder().addFeatures(feat_label).setImage(img)
					.build();
			AnnotateImageRequest request_web = AnnotateImageRequest.newBuilder().addFeatures(feat_web).setImage(img)
					.build();

			requests_text.add(request_text);
			requests_face.add(request_face);
			requests_landmark.add(request_landmark);
			requests_logo.add(request_logo);
			requests_label.add(request_label);
			requests_web.add(request_web);

			try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {

				BatchAnnotateImagesResponse response_text = client.batchAnnotateImages(requests_text);
				BatchAnnotateImagesResponse response_face = client.batchAnnotateImages(requests_face);
				BatchAnnotateImagesResponse response_landmark = client.batchAnnotateImages(requests_landmark);
				BatchAnnotateImagesResponse response_logo = client.batchAnnotateImages(requests_logo);
				BatchAnnotateImagesResponse response_label = client.batchAnnotateImages(requests_label);
				BatchAnnotateImagesResponse response_web = client.batchAnnotateImages(requests_web);

				List<AnnotateImageResponse> responses_text = response_text.getResponsesList();
				List<AnnotateImageResponse> responses_face = response_face.getResponsesList();
				List<AnnotateImageResponse> responses_landmark = response_landmark.getResponsesList();
				List<AnnotateImageResponse> responses_logo = response_logo.getResponsesList();
				List<AnnotateImageResponse> responses_label = response_label.getResponsesList();
				List<AnnotateImageResponse> responses_web = response_web.getResponsesList();

				for (AnnotateImageResponse res : responses_text) {

					if (res.hasError()) {
						System.out.printf("Error: %s\n", res.getError().getMessage());
					}

					if (!(res.getTextAnnotationsList().isEmpty())) {
						// Text_Detection
						// System.out.println("Text : ");
						// System.out.println(res.getTextAnnotationsList().get(0).getDescription());
						tt += (res.getTextAnnotationsList().get(0).getDescription() + "\n");
						System.out.println(tt);

					} else {
						break;
						// System.out.println("Text 없음\n");
						// tt += "Text 없음\n";
					}
					// tt += res.getTextAnnotationsList().get(0).getDescription();

					// For full list of available annotations, see http://g.co/cloud/vision/docs
					/*
					 * for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
					 * 
					 * //System.out.printf("Text: %s\n", annotation.getDescription());
					 * //System.out.printf("Position : %s\n", annotation.getBoundingPoly()); }
					 */
				}

				for (AnnotateImageResponse res : responses_face) {

					if (res.hasError()) {
						System.out.printf("Error: %s\n", res.getError().getMessage());

					}

					// For full list of available annotations, see http://g.co/cloud/vision/docs
					for (FaceAnnotation annotation : res.getFaceAnnotationsList()) {
						System.out.printf("anger: %s\njoy: %s\nsorrow: %s\nsurprise: %s\n\n", // \nposition: %s",
								annotation.getAngerLikelihood(), annotation.getJoyLikelihood(),
								annotation.getSorrowLikelihood(), annotation.getSurpriseLikelihood());// ,
						// annotation.getBoundingPoly());
						resultMap.put("anger", annotation.getAngerLikelihood());
						resultMap.put("joy", annotation.getJoyLikelihood());
						resultMap.put("sorrow", annotation.getSorrowLikelihood());
						resultMap.put("surprise", annotation.getSurpriseLikelihood());
					}

				}

				for (AnnotateImageResponse res : responses_landmark) {

					if (res.hasError()) {
						System.out.printf("Error: %s\n", res.getError().getMessage());

					}

					// For full list of available annotations, see http://g.co/cloud/vision/docs
					for (EntityAnnotation annotation : res.getLandmarkAnnotationsList()) {
						LocationInfo info = annotation.getLocationsList().listIterator().next();
						System.out.printf("Landmark: %s\n", annotation.getDescription());// , info.getLatLng());
						resultMap.put("Landmark", annotation.getDescription());
					}
				}

				for (AnnotateImageResponse res : responses_logo) {
					if (res.hasError()) {
						System.out.printf("Error: %s\n", res.getError().getMessage());

					}

					// For full list of available annotations, see http://g.co/cloud/vision/docs
					for (EntityAnnotation annotation : res.getLogoAnnotationsList()) {
						System.out.println(annotation.getDescription());
						resultMap.put("logo", annotation.getDescription());
					}
				}

				/*
				 * for (AnnotateImageResponse res : responses_label) { if (res.hasError()) {
				 * System.out.printf("Error: %s\n", res.getError().getMessage());
				 * 
				 * }
				 * 
				 * // For full list of available annotations, see http://g.co/cloud/vision/docs
				 * for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
				 * annotation.getAllFields().forEach((k, v) -> System.out.printf("%s : %s\n", k,
				 * v.toString())); } }
				 */

				for (AnnotateImageResponse res : responses_web) {
					if (res.hasError()) {
						System.out.printf("Error: %s\n", res.getError().getMessage());
					}

					// Search the web for usages of the image. You could use these signals later
					// for user input moderation or linking external references.
					// For a full list of available annotations, see http://g.co/cloud/vision/docs
					WebDetection annotation = res.getWebDetection();
					System.out.println("Entity:Id:Score");
					System.out.println("===============");
					int i = 1;

					for (WebEntity entity : annotation.getWebEntitiesList()) {

						System.out.println(
								entity.getDescription() + " : " + entity.getEntityId() + " : " + entity.getScore());
								
						resultMap.put("Entity" + i, entity.getDescription());
						resultMap.put("Id" + i, entity.getEntityId());
						resultMap.put("Score" + i, entity.getScore());
						entities[i]= entity.getDescription();
						i++;

					}
					
					for (WebLabel label : annotation.getBestGuessLabelsList()) {
						System.out.format("\nBest guess label: %s", label.getLabel());
						entities[0] = label.getLabel();
						resultMap.put("label", label.getLabel());
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ArrayList<String> a = new ArrayList<>();
		 for(int i = 0; i<11;i++) {
		        try {
		           String text = URLEncoder.encode(entities[i], "UTF-8");
		            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
		            URL url = new URL(apiURL);
		            HttpURLConnection con = (HttpURLConnection)url.openConnection();
		            con.setRequestMethod("POST");
		            con.setRequestProperty("X-Naver-Client-Id", clientId);
		            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		            // post request
		            //String[] postParams = new String[10];
		            //for (int i = 0; i < 10; i++) postParams[i] = "source=en&target=ko&text=" + text[i];
		            String postParams = "source=en&target=ko&text=" + text;
		            con.setDoOutput(true);
		            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		            //for (int i = 0; i < 10; i++) wr.writeBytes(postParams[i]);
		            wr.writeBytes(postParams);
		            wr.flush();
		            wr.close();
		            int responseCode = con.getResponseCode();
		            BufferedReader br;
		            if(responseCode==200) { // 정상 호출
		                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		            } else {  // 에러 발생
		                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		            }
		            String inputLine;
		            StringBuffer response3 = new StringBuffer();
		            while ((inputLine = br.readLine()) != null) {
		                response3.append(inputLine);
		            }
		            br.close();
		            System.out.println(response3.substring(151, response3.length()-3));
		            resultMap.put("korean" + i,response3.substring(152, response3.length()-4) );
		            a.add(response3.substring(152, response3.length()-4));
		            //response.substring(30, 50);
		        } catch (Exception e) {
		            System.out.println(e);
		        }
		        }
		 System.out.println(a.get(0) + "kore " + a.get(1));
		 String a0 = entities[0];
		 String a1 =a.get(1);
		 String a2 =a.get(2);
		 String a3 =a.get(3);
		 String asdf = "분석된 사진의 제목은 " + a0 + "입니다 . 사진의 제목은 "+ a1 +" 입니다. 사진에는 " +a2+ " 과 " +a3+ "과 관련된 정보들이 보입니다."  ;
		 String ntt =  asdf+ tt;
			resultMap.put("tt", ntt);
		 ModelAndView modelAndView = new ModelAndView("jsonView", resultMap);
		
		////mp3 시작
		StringBuffer reqStr = new StringBuffer();
		
		if(ntt !="") {
			System.out.println("음성시작");
		
		try {

			String text = URLEncoder.encode(ntt, "UTF-8"); // 13자
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
				File f = new File("C:\\test\\test.wav");
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

}
