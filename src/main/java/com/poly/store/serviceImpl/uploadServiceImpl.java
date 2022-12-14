package com.poly.store.serviceImpl;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.poly.store.dao.productDao;
import com.poly.store.entity.product;
import com.poly.store.service.productService;
import com.poly.store.service.uploadService;

@Service
public class uploadServiceImpl implements uploadService {
	@Autowired
	ServletContext app;

	@Override
	public File save(MultipartFile file, String folder) {
		File dir =new File(app.getRealPath("/assets/"+ folder));
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String s = System.currentTimeMillis() + file.getOriginalFilename();
		String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
		try {
			File savedFile = new File(dir,name);
			file.transferTo(savedFile);
			System.out.println(savedFile.getAbsolutePath());
			return savedFile;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
