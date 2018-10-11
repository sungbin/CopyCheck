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

public class Checker {

	public static void main(String[] args) {
		File f1 = new File("/Users/imseongbin/Desktop/SourceDir/Master.java");
		File f2 = new File("/Users/imseongbin/Desktop/SourceDir/Slave.java");
		File f3 = new File("/Users/imseongbin/Desktop/SourceDir/Example.java");
		File f4 = new File("/Users/imseongbin/Desktop/SourceDir/StackOfIntsLab.java");
		
		Analyzer analyzer = new Analyzer();
		OneFile of1 = analyzer.getContents(f1);
		OneFile of2 = analyzer.getContents(f2);
		
		Diff diff = new Diff(of1,of2);
		diff.getdiff();
		diff.printResult();
//		Generater gn = new Generater(of1);
//		gn.setRefinedCode();
//		System.out.println(gn.getRefinedCode());
		
		
		
//		for (String localVariable : of1.getLocalVariable()) {
//			System.out.println(localVariable);
//		}
//		Iterator<Entry<String, String>> itr = of1.getFieldVariable().entrySet().iterator();
//		while (itr.hasNext()) {
//			
//			Map.Entry<String, String> e = (Map.Entry<String, String>) itr.next();
//			System.out.println("Name : " + e.getKey() + ", Value : " + e.getValue());
//		}
	}

}
