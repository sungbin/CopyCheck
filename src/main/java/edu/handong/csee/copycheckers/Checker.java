package edu.handong.csee.copycheckers;

import java.io.File;

/*
 * Class:
 * OneFile: contain code, and variables.
 * Generater: to ignore reference lines, unnecessary blank, and variable.
 * Analyzer: to get Tokens of Variable (Student can change just variable name)
 * RefinedSource: having result source after Analyzer.
 * Difference: have different lines from two sources
 */


/*
 * Metric1: remove variable name from all code.
 * Metric2: measure intervals between Variable Declarations. (implement: including Vriable type)
 */

/*
 * next job: remove function name
 */

public class Checker {

	public static void main(String[] args) {
		String path = "/Users/imseongbin/Desktop/SourceDir/";
		File dirFile=new File(path);
		File []fileList=dirFile.listFiles();
		
		/* File Checking
		for(File tempFile : fileList) {
		  if(tempFile.isFile()) {
			  System.out.println(tempFile.getAbsolutePath());
		  }
		}
		File f1 = new File("/Users/imseongbin/Desktop/SourceDir/Master.java");
		File f2 = new File("/Users/imseongbin/Desktop/SourceDir/Slave.java");
		File f3 = new File("/Users/imseongbin/Desktop/SourceDir/Example.java");
		File f4 = new File("/Users/imseongbin/Desktop/SourceDir/StackOfIntsLab.java");
		*/
		
		Diff diff= null;
		
		for(int i = 0; i<fileList.length;i++) {
			for(int j =0; j<fileList.length;j++) {
				diff = calDiff(fileList[i],fileList[j]);
				diff.printResult();
			}
		}
		
		
	}
	
	private static Diff calDiff(File f1, File f2) {
		Analyzer analyzer = new Analyzer();
		OneFile of1 = analyzer.getContents(f1);
		OneFile of2 = analyzer.getContents(f2);
		
		Diff diff = new Diff(of1,of2);
		diff.run();
		return diff;
	}
}
