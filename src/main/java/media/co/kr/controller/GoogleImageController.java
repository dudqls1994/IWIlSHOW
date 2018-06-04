package media.co.kr.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.protobuf.ByteString;

import media.co.kr.dto.fileDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Handles requests for the application home page.
 */
@Controller
public class GoogleImageController{
	static public String tt="";
	@Autowired
	private SqlSession sqlsession;
	

	@RequestMapping("/searchimage.do")
	public String Home1() throws ClassNotFoundException, SQLException{
		return "home.imagesearch";
	}
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public String fileSubmit(fileDto dto ,Model model) {
		System.out.println(dto.getUploadfile());
		MultipartFile uploadfile = dto.getUploadfile();
		if (uploadfile != null) {
			String fileName = uploadfile.getOriginalFilename();
			System.out.println("filename " + fileName);
			try {
				// 1. FileOutputStream ���
				// byte[] fileData = file.getBytes();
				// FileOutputStream output = new FileOutputStream("C:/images/" + fileName);
				// output.write(fileData);

				// 2. File ���
				File file = new File(
						"C:\\Users\\YB\\Desktop\\media_project\\Mp\\src\\main\\webapp\\images\\" + fileName);
				uploadfile.transferTo(file);

				try {

					String imageFilePath = "C:\\Users\\YB\\Desktop\\media_project\\Mp\\src\\main\\webapp\\images\\"
							+ fileName;
					 List<AnnotateImageRequest> requests_text = new ArrayList<>();
			         List<AnnotateImageRequest> requests_face = new ArrayList<>();
			               
			         ByteString imgBytes = ByteString.readFrom(new FileInputStream(imageFilePath));
			      
			         Image img = Image.newBuilder().setContent(imgBytes).build();
			         Feature feat_text = Feature.newBuilder().setType(Type.TEXT_DETECTION).build();
			         Feature feat_face = Feature.newBuilder().setType(Type.FACE_DETECTION).build();
			         
			         AnnotateImageRequest request_text = AnnotateImageRequest.newBuilder().addFeatures(feat_text).setImage(img).build();
			         AnnotateImageRequest request_face = AnnotateImageRequest.newBuilder().addFeatures(feat_face).setImage(img).build();
			         requests_text.add(request_text);
			         requests_face.add(request_face);
			         
			         try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
			            BatchAnnotateImagesResponse response_text = client.batchAnnotateImages(requests_text);
			            BatchAnnotateImagesResponse response_face = client.batchAnnotateImages(requests_face);
			             List<AnnotateImageResponse> responses_text = response_text.getResponsesList();
			             List<AnnotateImageResponse> responses_face = response_face.getResponsesList();
			                  
			             for (AnnotateImageResponse res : responses_text) {
			                 if (res.hasError()) {
			                    System.out.printf("Error: %s\n", res.getError().getMessage());
			                 
			                 }
			                 
			                 if (!(res.getTextAnnotationsList().isEmpty())) {
			                 //Text_Detection
			                 System.out.println("Text : ");
			                 System.out.println(res.getTextAnnotationsList().get(0).getDescription());
			                 tt += (res.getTextAnnotationsList().get(0).getDescription()+"&lt;br /&gt;");
			                 model.addAttribute("fileName", fileName);
			                 model.addAttribute("tt", tt);
			                 }
			                 else {
			                    System.out.println("Text 없음\n");
			                    tt += "Text 없음\n";
			                 }
			                 //tt += res.getTextAnnotationsList().get(0).getDescription();
			                 
			                 // For full list of available annotations, see http://g.co/cloud/vision/docs
			                 /*for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
			                      
			                   //System.out.printf("Text: %s\n", annotation.getDescription());
			                   //System.out.printf("Position : %s\n", annotation.getBoundingPoly());
			                }*/
			              }
			             
			             for (AnnotateImageResponse res : responses_face) {
			                if (res.hasError()) {
			                   System.out.printf("Error: %s\n", res.getError().getMessage());
			               
			                }
			      
			                // For full list of available annotations, see http://g.co/cloud/vision/docs
			                 for (FaceAnnotation annotation : res.getFaceAnnotationsList()) {
			                   System.out.printf(
			                       "anger: %s\njoy: %s\nssorow: %s\nsurprise: %s",//\nposition: %s",
			                       annotation.getAngerLikelihood(),
			                       annotation.getJoyLikelihood(),
			                       annotation.getSorrowLikelihood(),
			                       annotation.getSurpriseLikelihood());//,
			                       //annotation.getBoundingPoly());
			                 }
			             }
			             
			         }
			      } catch(Exception e) {
			         e.printStackTrace();
			      }

			} catch (IOException e) {
				e.printStackTrace();
			} // try - catch
		} // if

		return "home.imagesearchcomplete";
	}
	
}