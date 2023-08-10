package com.dream.framework.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.hutool.extra.ftp.Ftp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FtpHelper {

	public static String upload(InputStream fileStream, String fileName, String ip) {
		// 匿名登录（无需帐号密码的FTP服务器）
		Ftp ftp = new Ftp(ip);
		// 进入远程目录
		ftp.cd("/opt/upload");
		// 上传本地文件
		ftp.upload("/opt/upload", fileName, fileStream);
		// 下载远程文件
		// ftp.download("/opt/upload", "test.jpg", FileUtil.file("e:/test2.jpg"));
		// 关闭连接
		try {
			ftp.close();
		} catch (IOException e) {
			log.info(fileName + "发送ftp失败");
		}
		return "发送成功";
	}

	public static String upload(String url, String fileName, String ip) {
		try {
			HttpURLConnection httpUrl = (HttpURLConnection) new URL(url).openConnection();
			httpUrl.connect();
			InputStream ins;
			ins = httpUrl.getInputStream();
			return upload(ins, fileName, ip);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "发送失败";
	}
}