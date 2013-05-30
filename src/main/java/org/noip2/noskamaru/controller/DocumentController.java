package org.noip2.noskamaru.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.noip2.noskamaru.dao.DocumentDao;
import org.noip2.noskamaru.model.Document;
import org.noip2.noskamaru.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class DocumentController {
	
	@Autowired
	private DocumentDao documentDao;
	
	@Autowired
	private UserDao userDao;
	
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map, Map<String, Object> map2)	{
		try		{
			map.put("document", new Document());
			map.put("documentList", documentDao.list());
			map2.put("userList", userDao.list());
			
		}
		catch(Exception e)	{
			e.printStackTrace();
		}
		return("documents");//documents.html
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(
            @ModelAttribute("document") Document document,
            @RequestParam("file") MultipartFile file) {
         
         
        System.out.println("Name:" + document.getName());
        System.out.println("Desc:" + document.getDescription());
        System.out.println("File:" + file.getName());
        System.out.println("ContentType:" + file.getContentType());
         
        try {
            Blob blob = Hibernate.createBlob(file.getInputStream());
 
            document.setFilename(file.getOriginalFilename());
            document.setContent(blob);
            document.setContentType(file.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        try {
            documentDao.save(document);
        } catch(Exception e) {
            e.printStackTrace();
        }
         
        return "redirect:/index.html";
    }
	
	@RequestMapping(value="/update/{documentId}",method=RequestMethod.POST)
    public String update(@ModelAttribute("document") Document document,
    		@RequestParam("file2") MultipartFile file2,
    		@RequestParam("description") String desc,
    		@PathVariable("documentId") Integer documentId)	{
    	    document = documentDao.get(documentId);
    		int newStatus=document.getStatus() + 1;
    	try		{
    		
    		Blob blob2 = Hibernate.createBlob(file2.getInputStream());
            document.setFilename(file2.getOriginalFilename());
            
            //document.setContentType(file2.getContentType());
    		document.setStatus(newStatus);
    		document.setDescription(desc);
    		document.setContent(blob2);
    		 System.out.println("Name:" + document.getName());
    		 System.out.println("Name:" + document.getFilename());
    	        System.out.println("Desc:" + document.getDescription());
    	        System.out.println("File:" + file2.getName());
    	        System.out.println("ContentType:" + file2.getContentType());
        } 
    	catch (Exception e) {
            e.printStackTrace();
        }
         
        try {
            documentDao.update(document);
        } catch(Exception e) {
            e.printStackTrace();
        }
         
        return "redirect:/index.html";
    }

 
    @RequestMapping("/download/{documentId}")
    public String download(@PathVariable("documentId")
            Integer documentId, HttpServletResponse response) {
         
        Document doc = documentDao.get(documentId);
        try {
            response.setHeader("Content-Disposition", "inline;filename=\"" +doc.getFilename()+ "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(doc.getContentType());
            IOUtils.copy(doc.getContent().getBinaryStream(), out);
            out.flush();
            out.close();
         
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
         
         
        return null;
    }
 
    @RequestMapping("/remove/{documentId}")
    public String remove(@PathVariable("documentId")
            Integer documentId) {
         
        documentDao.remove(documentId);
         
        return "redirect:/index.html";
    }
    
    
}
