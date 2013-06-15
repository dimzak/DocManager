package org.noip2.noskamaru.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.noip2.noskamaru.dao.DocumentDao;
import org.noip2.noskamaru.dao.FlowDao;
import org.noip2.noskamaru.dao.UserDao;
import org.noip2.noskamaru.model.Document;
import org.noip2.noskamaru.model.Flow;
import org.noip2.noskamaru.model.User;
import org.noip2.noskamaru.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FlowService flowService;
	
	@Autowired
	private FlowDao flowDao;
	
	@Autowired
	private DocumentDao docDao;
	
	@RequestMapping(value="/user/doc*",method = RequestMethod.GET)
	public String index(Map<String, Object> map, Map<String, Object> map2,Model model, HttpServletRequest request,
			Map<String, Object> map3)	{
		try		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String name = auth.getName(); //get logged in username
	        int userid = flowService.getIdFromName(name);
	        //the new(a model) is for crud operations
	        model.addAttribute("username", name);
			map.put("document", new Document());
			map.put("documentList", flowService.listByUser(userid));
			map2.put("userList", userDao.list());
			map3.put("flow", new Flow());
			map3.put("flowList", flowDao.list());
			
		}
		catch(Exception e)	{
			e.printStackTrace();
		}
		return("/user/documents.html");//documents.html
	} 
	
	@RequestMapping(value="/user/alldoc*",method = RequestMethod.GET)
	public String allDoc(Map<String, Object> map, Model model, HttpServletRequest request)	{
		try		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String name = auth.getName(); //get logged in username
	        model.addAttribute("username", name);
			map.put("document", new Document());
			map.put("documentList", docDao.list());
		}
		catch(Exception e)	{
			e.printStackTrace();
		}
		return("/user/alldoc.html");
	} 
	
	
	
	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
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
            docDao.save(document);
        } catch(Exception e) {
            e.printStackTrace();
        }
         
        return "redirect:/user/documents.html";
    }
	
	@RequestMapping(value="/user/update/{documentId}",method=RequestMethod.POST)
    public String update(@ModelAttribute("document") Document document,
    		@RequestParam("file2") MultipartFile file2,
    		@RequestParam("description") String desc,
    		@PathVariable("documentId") Integer documentId)	{
    	    document = docDao.get(documentId);
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
            docDao.update(document);
        } catch(Exception e) {
            e.printStackTrace();
        }
         
        return "redirect:/user/documents.html";
    }

 
    @RequestMapping("/user/download/{documentId}")
    public String download(@PathVariable("documentId")
            Integer documentId, HttpServletResponse response) {
         
        Document doc = docDao.get(documentId);
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
 
    @RequestMapping("/user/remove/{documentId}")
    public String remove(@PathVariable("documentId")
            Integer documentId) {
         
        docDao.remove(documentId);
         
        return "redirect:/user/documents.html";
    }
	
    
    @RequestMapping(value = "/user/saveflow", method = RequestMethod.POST)
    public String saveFlow(
            @ModelAttribute("flow") Flow flow, @RequestParam("userid") int userid, @RequestParam("docid") int docid) {
        try {
        	flow.setDoc(docDao.get(docid));
        	flow.setUser(userDao.get(userid));
            flowDao.save(flow);
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
         
        return "redirect:/user/documents.html";
    }

}
