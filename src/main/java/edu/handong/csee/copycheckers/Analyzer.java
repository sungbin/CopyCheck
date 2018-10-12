package edu.handong.csee.copycheckers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class Analyzer {
	public OneFile getContents(File f) {
		OneFile oneFile = new OneFile();

		try {
			String lines = readFile(f);
			Set<String> localVariable = new HashSet<String>();
			HashMap<String, String> fieldVariable = new HashMap();

			ASTParser parser = ASTParser.newParser(AST.JLS10);

			parser.setSource(lines.toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);

			final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

			cu.accept(new ASTVisitor() {
				public boolean visit(FieldDeclaration node) {
					addFieldVariable(fieldVariable, node);
					return false; // do not continue to avoid usage info
				}

				public boolean visit(VariableDeclarationFragment node) {
					SimpleName name = node.getName();
					localVariable.add(name.toString());
					return false; // do not continue to avoid usage info
				}
			});

			oneFile.setCode(lines);
			oneFile.setLocalVariable(localVariable);
			oneFile.setFieldVariable(fieldVariable);
			oneFile.setFile(f);
		} catch (IOException e) {
			System.out.println("File not exist!");
			e.printStackTrace();
		}
		return oneFile;
	}

	private void addFieldVariable(HashMap<String, String> fieldVariable, FieldDeclaration node) {
		Iterator<Object> itr = node.fragments().iterator();
		Pattern p = Pattern.compile("((.+)=(.+)|.+)");

		while(itr.hasNext()) {
			String declaration = itr.next().toString();
			//System.out.println(declaration);
			divideAndPush(fieldVariable,declaration,p);
			
		}

		
	}

	private void divideAndPush(HashMap<String, String> fieldVariable, String declaration, Pattern p) {
		String name = null;
		String value = null;
		
		Matcher matcher = p.matcher(declaration);
		if(matcher.find()) {
			if(matcher.group(0).contains("=")) {
				fieldVariable.put(matcher.group(2), matcher.group(3));
			} else {
				fieldVariable.put(matcher.group(0), null);
			}
		} else {
			try {
				throw new Exception("Error: declaration");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private String readFile(File f) throws IOException {
		StringBuffer contents = new StringBuffer();

		FileReader rd = new FileReader(f);
		BufferedReader reader = new BufferedReader(rd);
		String line = "";
		while ((line = reader.readLine()) != null) {
			contents.append(line);
			contents.append("\n");
		}
		reader.close();

		return contents.toString();
	}
}
