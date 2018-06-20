package com.gac.dimens;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class DimensTool {
	static float[] screenWidthDP= new float[]{320,360,384,400,432,480,533,600};
	public static float screenWidthPX =720;
	
	public static void createDirectory(String name) {
		File file = new File(name);
		if(file.exists()) {
			file.delete();
			
		}
		file.mkdir();
	}
	
	public static File createFile(String directory) {
		File file = new File(directory+"/"+"dimens.xml");
		if(file.exists()) {
			file.delete();
			
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
	public static void main(String[] args) {
		generateFiles();
	}
	public static void generateFiles() {
		for(int i = 0; i < screenWidthDP.length; i++) {
			String dir = "values-sw"+(int)screenWidthDP[i]+"dp";
			createDirectory(dir);
			writeFiles(createFile(dir),(int)screenWidthDP[i]);
			
		}
	}
	
	public static void writeFiles(File file,int screenDp) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			DecimalFormat   fnum  =   new  DecimalFormat("##0.00"); 
			StringBuffer sb = new StringBuffer();
			sb.append("<dimen name=\"basedpi\">"+screenDp+"dp</dimen>");
			sb.append(System.getProperty("line.separator"));  
			float base = screenDp/screenWidthPX;
			for(int i = 0; i <= screenWidthPX; i++) {
				sb.append("<dimen name=\"px_"+i+"\">"+fnum.format(base*i)+"dp</dimen>");
				sb.append(System.getProperty("line.separator"));  
			}
			PrintWriter pw  = new PrintWriter(fos);
			pw.write(sb.toString());
			pw.close();
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
