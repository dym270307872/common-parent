package cn.dyaoming.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtil {
	
	/**
	 * 创建固定大小的文件
	 * @param file
	 * @param length
	 * @throws IOException
	 */
	public static void createFixLengthFile(File file, long length) throws IOException{
		long start = System.currentTimeMillis();
		FileOutputStream fos = null;
		FileChannel output = null;
		try {
			fos = new FileOutputStream(file);
			output = fos.getChannel();
			output.write(ByteBuffer.allocate(1), length-1);
		} finally {
			try {
				if (output != null) {
					output.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("total times "+(end-start));
	}
	
	
	//创建一个占位文件
	public static void create(File file, long length) throws IOException {
		long start = System.currentTimeMillis();
		RandomAccessFile r = null;
		try {
			r = new RandomAccessFile(file, "rw");
			r.setLength(length);
		} finally{
			if (r != null) {
				try {
					r.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	
	
	public static void main(String[] args){
	
		try{
			File file = new File("F:/test/1234.txt");
//			if(file)
			createFixLengthFile(file,500000000L);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
