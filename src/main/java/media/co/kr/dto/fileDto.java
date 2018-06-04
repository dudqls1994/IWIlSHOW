package media.co.kr.dto;

import org.springframework.web.multipart.MultipartFile;

public class fileDto {
	private MultipartFile uploadfile;

	public MultipartFile getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}

}
