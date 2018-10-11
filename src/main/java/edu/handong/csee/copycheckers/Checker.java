package edu.handong.csee.copycheckers;

import java.io.File;

/*
 * Class:
 * OneFile: contain code, and variables.
 * Generater: to remove reference lines and unnecessary blank.
 * Analyzer: to get equal and different lines from two sources
 * RefinedSource: having result source after Analyzer.
 */

public class Checker {

	// 주석 제거하고, code에서만 variable 변경시기게 Logic
	
	public static void main(String[] args) {
		File f1 = new File("/Users/imseongbin/Desktop/SourceDir/Master.java");
		File f2 = new File("/Users/imseongbin/Desktop/SourceDir/Slave.java");
		File f3 = new File("/Users/imseongbin/Desktop/SourceDir/Example.java");

		Analyzer analyzer = new Analyzer();
		OneFile of1 = analyzer.getContents(f1);
		for(String localVariable : of1.getLocalVariable()) {
			System.out.println(localVariable);
		}

	}

	
}
