package com.huawei.StudentService.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFileUtil {

	private ZipFileUtil() {

	}

	/**
	 * 压缩文件
	 * 
	 * @param filePath
	 * @return 压缩后的文件
	 * @throws IOException
	 */
	public static void zip(String filePath, String zipPath) throws IOException {
		File source = new File(filePath);
		if (!source.exists()) {
			return;
		}
		String sourceName = source.getName();
		String zipName = "";
		if (source.isDirectory()) {
			zipName = sourceName + ".zip";
		} else {
			zipName = sourceName.substring(0, sourceName.indexOf(".")) + ".zip";
		}
		File target = new File(zipPath, zipName);
		if (target.exists()) {
			target.delete();
		}
		FileOutputStream fos = new FileOutputStream(target);
		ZipOutputStream zos = new ZipOutputStream(fos);
		// 添加对应的文件Entry，从根目录开始
		addEntry("", source, zos);
		closeAll(zos, fos);
	}

	/**
	 * 扫描添加文件Entry
	 * 
	 * @param base
	 * @param source
	 * @param zos
	 * @throws IOException
	 */
	private static void addEntry(String base, File source, ZipOutputStream zos) throws IOException {
		// 按目录分级，形如：/aaa/bbb.txt
		if (source.isDirectory()) {
			if (base.length() > 0) {
				base = base + File.separator;
			}
			for (File file : source.listFiles()) {
				addEntry(base + file.getName(), file, zos);
			}
		} else {
			if (base.length() == 0) {
				base = source.getName();
			}
			zos.putNextEntry(new ZipEntry(base));
			FileInputStream fis = new FileInputStream(source);
			BufferedInputStream bis = new BufferedInputStream(fis);
			byte[] read = new byte[1024 * 10];
			while (bis.read(read) != -1) {
				zos.write(read);
			}
			closeAll(bis, fis);
		}
	}

	/**
	 * 解压文件
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public static void unzip(String filePath, String targPath) throws IOException {
		File source = new File(filePath);
		if (!source.exists() && !filePath.endsWith(".zip")) {
			return;
		}
		String packName = filePath.substring(filePath.lastIndexOf(File.separator), filePath.lastIndexOf("."));
		ZipInputStream zis = null;
		BufferedOutputStream bos = null;
		zis = new ZipInputStream(new FileInputStream(source));
		ZipEntry entry = null;
		while ((entry = zis.getNextEntry()) != null && !entry.isDirectory()) {
			File target = new File(targPath + packName, entry.getName());
			if (!target.getParentFile().exists()) {
				target.getParentFile().mkdirs();
			}
			bos = new BufferedOutputStream(new FileOutputStream(target));
			byte[] read = new byte[1024 * 10];
			while (zis.read(read) != -1) {
				bos.write(read);
			}
		}
		closeAll(zis, bos);
	}
	
	/**
	 * 关闭系统资源
	 * 
	 * @param closeables
	 * @throws IOException
	 */
	private static void closeAll(Closeable...closeables) throws IOException {
		if(closeables == null) {
			return;
		}
		for(Closeable closeable : closeables) {
			closeable.close();
		}
	}
	
	
	
}
