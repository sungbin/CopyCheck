package edu.handong.csee.copycheckers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
			oneFile.setCode(lines);
			Set<String> localVariable = new HashSet<String>();
			HashMap<String, String> fieldVariable = new HashMap();

			ASTParser parser = ASTParser.newParser(AST.JLS10);

			parser.setSource(lines.toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);

			final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

			cu.accept(new ASTVisitor() {

				// Set names = new HashSet();

				public boolean visit(FieldDeclaration node) {
					//System.out.print(node);
					//System.out.println(node.fragments());
					return false; // do not continue to avoid usage info
				}

				public boolean visit(VariableDeclarationFragment node) {
					SimpleName name = node.getName();
					// this.names.add(name.getIdentifier());
					localVariable.add(name.toString());
					// System.out.println("Declaration of '" + name + "' at line" +
					// cu.getLineNumber(name.getStartPosition()));
					return false; // do not continue to avoid usage info
				}
			});

			oneFile.setLocalVariable(localVariable);
		} catch (IOException e) {
			System.out.println("File not exist!");
			e.printStackTrace();
		}
		return oneFile;
	}

	private static String readFile(File f) throws IOException {
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
