package fileIO;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {

	public static void main(String[] args) {

		System.out.println("Started");
		try(FileInputStream fin = new FileInputStream("/Users/yusuf/OBSS/day3/src/fileIO/100MB.bin")) {
			
			long start = System.currentTimeMillis();
		    while(fin.read() != -1);
		    System.out.println(System.currentTimeMillis() - start);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(BufferedInputStream bin = new BufferedInputStream(new FileInputStream("/Users/yusuf/OBSS/day3/src/fileIO/100MB.bin"))){
			long start = System.currentTimeMillis();
		    while(bin.read() != -1);
		    System.out.println(System.currentTimeMillis() - start);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("finished");
	}

}
