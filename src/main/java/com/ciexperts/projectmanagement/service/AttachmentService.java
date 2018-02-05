package com.ciexperts.projectmanagement.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.ciexperts.projectmanagement.dao.AttachmentDao;
import com.ciexperts.projectmanagement.entity.AppUser;
import com.ciexperts.projectmanagement.entity.ProjectAttachment;
import com.ciexperts.projectmanagement.entity.RequestAttachment;

@Service
public class AttachmentService {
	@Autowired
	ServletContext servletContext;
	
	private AttachmentDao attachmentDao = new AttachmentDao();
	
	//methods for attachments on requests
	public List<String> getReqAttachmentListByReqNo(Integer reqNo){
		List<String> fileNames = new ArrayList<String>();
		List<RequestAttachment> reqAttachments = attachmentDao.getReqAttachmentListByReqNo(reqNo);
		for(RequestAttachment reqAttachment : reqAttachments ){
			fileNames.add(reqAttachment.getFileName());
		}
		return fileNames;
	}
	
	public void uploadReqAttachments(@RequestParam Integer reqNo,MultipartHttpServletRequest mRequest,HttpServletRequest hRequest ) throws Exception{
		HttpSession session = hRequest.getSession();
		Iterator<String> itrator = mRequest.getFileNames();
	    MultipartFile multiFile = mRequest.getFile(itrator.next());
	        try {
	            // just to show that we have actually received the file
	            System.out.println("File Length:" + multiFile.getBytes().length);
	            String fileName= multiFile.getOriginalFilename();
	            System.out.println("File Name:" +fileName);
	            String fileExt = FilenameUtils.getExtension(fileName);
	            System.out.println("File Extension:" + fileExt);
	            String path= mRequest.getServletContext().getRealPath("/");
	            System.out.println("Path:" + path);
	            
	            //making directories for our required path.
	            byte[] bytes = multiFile.getBytes();
	            File directory =  new File(path + "/uploads/reqAttachments");
	            System.out.println("directory: " + directory);
	            directory.mkdirs();
	            // saving the file
	            File file= new File(directory.getAbsolutePath()+System.getProperty("file.separator")+ fileName);
	            BufferedOutputStream stream = new BufferedOutputStream(
	            new FileOutputStream(file));
	            stream.write(bytes);
	            stream.close();
	            
		        RequestAttachment reqAttachment = attachmentDao.getReqAttachmentByFileName(fileName);	
				if (reqAttachment == null) {
					RequestAttachment newReqAttach = new RequestAttachment();
					System.out.println("reqNo: " + reqNo);
					newReqAttach.setReqNo(reqNo);
					newReqAttach.setFileName(fileName);
					newReqAttach.setFileExt(fileExt);
					AppUser user = (AppUser) session.getAttribute("appUser");
					newReqAttach.setLastUser(user.getFullName());
					attachmentDao.saveReqAttachment(newReqAttach);
				}
	            
			} catch (Exception e) {
			 e.printStackTrace();
			 throw new Exception("Error while loading the file");
			}
		
	}
	
	//methods for attachments on projects
		public List<String> getProjectAttachmentListByProjNo(Integer projNo){
			List<String> fileNames = new ArrayList<String>();
			List<ProjectAttachment> projAttachments = attachmentDao.getProjAttachmentListByReqNo(projNo);
			for(ProjectAttachment projAttachment : projAttachments ){
				fileNames.add(projAttachment.getFileName());
			}
			return fileNames;
		}
		
		public void uploadProjAttachments(@RequestParam Integer projNo,MultipartHttpServletRequest mRequest,HttpServletRequest hRequest ) throws Exception{
			HttpSession session = hRequest.getSession();
			Iterator<String> itrator = mRequest.getFileNames();
		    MultipartFile multiFile = mRequest.getFile(itrator.next());
		        try {
		            // just to show that we have actually received the file
		            System.out.println("File Length:" + multiFile.getBytes().length);
		            String fileName= multiFile.getOriginalFilename();
		            System.out.println("File Name:" +fileName);
		            String fileExt = FilenameUtils.getExtension(fileName);
		            System.out.println("File Extension:" + fileExt);
		            String path= mRequest.getServletContext().getRealPath("/");
		            System.out.println("Path:" + path);
		            
		            //making directories for our required path.
		            byte[] bytes = multiFile.getBytes();
		            File directory =  new File(path + "/uploads/projAttachments");
		            directory.mkdirs();
		            // saving the file
		            File file= new File(directory.getAbsolutePath()+System.getProperty("file.separator")+ fileName);
		            BufferedOutputStream stream = new BufferedOutputStream(
		            new FileOutputStream(file));
		            stream.write(bytes);
		            stream.close();
		            
			        ProjectAttachment projAttachment = attachmentDao.getProjAttachmentByFileName(fileName);
					if (projAttachment == null) {
						ProjectAttachment newProjAttach = new ProjectAttachment();
						newProjAttach.setProjNo(projNo);
						newProjAttach.setFileName(fileName);
						newProjAttach.setFileExt(fileExt);
						AppUser user = (AppUser) session.getAttribute("appUser");
						newProjAttach.setLastUser(user.getFullName());
						attachmentDao.saveProjAttachment(newProjAttach);
					}
		            
				} catch (Exception e) {
				 e.printStackTrace();
				 throw new Exception("Error while loading the file");
				}
			
	
	

		}
}
