/**
 * 
 */
package cs544.project.share2care.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cs544.project.share2care.domain.Member;

import org.springframework.core.env.Environment;

/**
 * @author Dilip
 *
 */
@Controller
@RequestMapping("/rest")
public class FileUploadRestController {
	
	@Autowired
	Environment env;

	 @RequestMapping("/uploadfile")
	  public String index() {
	    return "/users/user/fileuploadrest";
	}
	 
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile, HttpSession session) {
	  Member member = (Member)session.getAttribute("member");
	  try {
	    // Get the filename and build the local file path (be sure that the 
	    // application have write permissions on such directory)
	    String filename = uploadfile.getOriginalFilename()+"_"+member.getMemberId();
	    String directory = env.getProperty("netgloo.paths.uploadedFiles");
	    String filepath = Paths.get(directory, filename).toString();
	    System.out.println(filepath+"-------> "+directory+" -------->"+filename);
	    // Save the file locally
	    BufferedOutputStream stream =
	        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    stream.write(uploadfile.getBytes());
	    stream.close();
	  }
	  catch (Exception e) {
	    System.out.println(e.getMessage());
	    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	  }
	  
	  return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
