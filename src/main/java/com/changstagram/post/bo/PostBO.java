package com.changstagram.post.bo;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.changstagram.common.FileManagerService;
import com.changstagram.post.dao.PostDAO;
import com.changstagram.post.model.Post;


@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public List<Post> getPostList(){
		return postDAO.selectPostList();
	}
	
	
	
	public int addPost(int userId, String loginId, String content, MultipartFile file) {
		String imagePath = null;
		
		if (file != null) {
			// TODO: 파일매니저 서비스 	input:MultipartFile		output:이미지의 주소
			try {
				imagePath = fileManagerService.saveFile(loginId, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// insert DAO
		return postDAO.insertPost(userId, content, imagePath);
	}
}
