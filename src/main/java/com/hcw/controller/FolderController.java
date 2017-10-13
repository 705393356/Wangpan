package com.hcw.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hcw.service.CosService;
import com.hcw.vo.FilePath;

import net.sf.json.JSONObject;

@Controller
public class FolderController {

	@Autowired
	private CosService cosService;
	
	//文件上传
	@ResponseBody
	@RequestMapping(value = "upFile", method = RequestMethod.POST)
	public JSONObject upFile(@RequestParam(required=false) MultipartFile[] files,@RequestParam String cospath,HttpServletRequest req,HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		String serverpath = null;
		FilePath path = new FilePath();
		int i = 1;
		for(MultipartFile file:files) {
			if(file.isEmpty()) continue;
			
			String realpath = req.getSession().getServletContext().getRealPath("/file/");
			FileUtils.copyInputStreamToFile(file.getInputStream(),new File(realpath + file.getOriginalFilename()));
			serverpath = realpath + file.getOriginalFilename();
			path.setLocalPathDown(serverpath);
			path.setCosFilePath(cospath);
			json.put(i, cosService.uploadFile(path));
			i++;
			File delefile = new File(serverpath);
			delefile.delete();
		}
		return json;
		
	}
	
	//新建文件夹
	@ResponseBody
	@RequestMapping(value = "newFolder", method = RequestMethod.POST)
	public JSONObject newFolder(@RequestBody FilePath path){
		JSONObject json = new JSONObject();
		String message = cosService.newFolder(path);
		json.put("message", message);
		
		return json;
	}
	
	//下载文件
	@ResponseBody
	@RequestMapping(value = "getFile", method = RequestMethod.POST)
	public JSONObject getFile(@RequestBody FilePath path){
		JSONObject json = new JSONObject();
		json.put("message",cosService.getFolder(path));
		return json;
	}
	
	//删除文件
	@ResponseBody
	@RequestMapping(value = "deleFile", method = RequestMethod.POST)
	public JSONObject deleFile(@RequestBody FilePath path){
		JSONObject json = new JSONObject();
		json.put("message",cosService.deleteFile(path));
		return json;
	}
	
	//删除文件夹
	@ResponseBody
	@RequestMapping(value = "deleFolder", method = RequestMethod.POST)
	public JSONObject deleFolder(@RequestBody FilePath path){
		JSONObject json = new JSONObject();
		json.put("message",cosService.deleteFolder(path));
		return json;
	}
	
	//list某文件夹
	@ResponseBody
	@RequestMapping(value = "listFolder", method = RequestMethod.POST)
	public JSONObject listFolder(@RequestBody FilePath path){
		JSONObject json = new JSONObject();
		json.put("message",cosService.listFolder(path));
		return json;
	}
	
}
