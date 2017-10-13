package com.hcw.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcw.tool.String_operate;
import com.hcw.vo.FilePath;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.CreateFolderRequest;
import com.qcloud.cos.request.DelFileRequest;
import com.qcloud.cos.request.DelFolderRequest;
import com.qcloud.cos.request.GetFileLocalRequest;
import com.qcloud.cos.request.ListFolderRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;

@Service
public class CosService {

	static final String bucketName = "wp";
	String_operate so = new String_operate();

	public static COSClient getCOSClient() {
		// 初始化秘钥信息
		long appId = 1254133551;
		String secretId = "AKIDssgIDoVcWefaP7y0FIArPnzFZjhzr991";
		String secretKey = "oQq9OTN8j8LSWhhV7YEnBQTL4adaO3Zv";

		// 设置要操作的bucket
		// String bucketName = "goods";
		// 初始化秘钥信息
		Credentials cred = new Credentials(appId, secretId, secretKey);
		// 初始化客户端配置(如设置园区)
		// 初始化客户端配置
		ClientConfig clientConfig = new ClientConfig();
		// 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
		clientConfig.setRegion("gz");
		// 生成客户端
		// 初始化cosClient
		COSClient cosClient = new COSClient(clientConfig, cred);

		return cosClient;
	}
	// 登陆时使用
		public String loginlist(FilePath path) {
			COSClient cosClient = getCOSClient();

			ListFolderRequest listFolderRequest = new ListFolderRequest(bucketName, "/" + path.getCosFilePath() + "/");
			String listFolderRet = cosClient.listFolder(listFolderRequest);
			cosClient.shutdown();
			return listFolderRet;
		}
	
	
	// 注册时创建个人网盘文件夹
	public String createGreateFolder(String username) {

		COSClient cosClient = getCOSClient();

		CreateFolderRequest request = new CreateFolderRequest(bucketName, "/" + username + "/");
		String createFolder = cosClient.createFolder(request);
		cosClient.shutdown();
		return createFolder;
	}
	
	//注册时新建文件夹
	public String registerFolder(String username) {

		COSClient cosClient = getCOSClient();

		CreateFolderRequest request = new CreateFolderRequest(bucketName, "/"+username+"/"+"新建文件夹/");
		String createFolder = cosClient.createFolder(request);
		cosClient.shutdown();
		return createFolder;
	}

	// 文件上传
	public String uploadFile(FilePath path) {
		
		UploadFileRequest uploadFilerequest = new UploadFileRequest(bucketName, "/"+path.getCosFilePath()+"/" + so.splitByF(path.getLocalPathDown()),
				path.getLocalPathDown());

		COSClient cosClient = getCOSClient();
		String uploadFileRet = cosClient.uploadFile(uploadFilerequest);
		cosClient.shutdown();
		return uploadFileRet;
	}

	// list目录, 获取目录下的成员
	public String listFolder(FilePath path) {
		COSClient cosClient = getCOSClient();

		ListFolderRequest listFolderRequest = new ListFolderRequest(bucketName, "/" + path.getCosFilePath() + "/" + path.getFoldername() + "/");
		String listFolderRet = cosClient.listFolder(listFolderRequest);
		cosClient.shutdown();
		return listFolderRet;
	}

	// 下载文件
	public String getFolder(FilePath path) {
		COSClient cosClient = getCOSClient();
		GetFileLocalRequest getFileLocalRequest = new GetFileLocalRequest(bucketName, path.getCosFilePath(),
				path.getLocalPathDown() + "\\" + so.splitByR(path.getCosFilePath()));
		getFileLocalRequest.setUseCDN(false);
		getFileLocalRequest.setReferer("*.myweb.cn");
		String getFileResult = cosClient.getFileLocal(getFileLocalRequest);
		cosClient.shutdown();
		return getFileResult;
	}

	//创建文件夹
	public String newFolder(FilePath path) {

		CreateFolderRequest request = new CreateFolderRequest(bucketName,
				"/"+path.getCosFilePath() + "/" + path.getFoldername() + "/");
		COSClient cosClient = getCOSClient();
		String createFolder = cosClient.createFolder(request);
		cosClient.shutdown();
		return createFolder;
	}

	// 删除文件夹
	public String deleteFolder(FilePath path) {
		DelFolderRequest request = new DelFolderRequest(bucketName, "/" + path.getCosFilePath() + "/");
		COSClient cosClient = getCOSClient();
		String delFolderRet = cosClient.delFolder(request);
		cosClient.shutdown();
		return delFolderRet;
	}

	// 删除文件
	public String deleteFile(FilePath path) {

		DelFileRequest delFileRequest = new DelFileRequest(bucketName, "/"+path.getCosFilePath());
		COSClient cosClient = getCOSClient();
		String delFileRet = cosClient.delFile(delFileRequest);
		cosClient.shutdown();
		return delFileRet;

	}
}
