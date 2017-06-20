package com.atguigu.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MyFileUploadUtil {

	public static List<String> imgs_upload(MultipartFile[] files) {

		List<String> image_list = new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
			try {

				// UUID randomUUID = UUID.randomUUID();

				String originalFilename = System.currentTimeMillis() + files[i].getOriginalFilename();// 包含图片格式
				files[i].transferTo(new File(
						"D:\\Mywork\\javacode\\WEB-SHOP\\Web_shop3\\src\\main\\webapp\\upload\\"
								+ originalFilename));// 图片的上传

				image_list.add(originalFilename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return image_list;
	}

	
	
}
