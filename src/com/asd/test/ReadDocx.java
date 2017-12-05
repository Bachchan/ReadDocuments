package com.asd.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;

public class ReadDocx {

	public static void main(String[] args) {

		String fileName="C:\\Users\\Asendra\\Desktop\\testDocx.docx";
		System.out.println("Docx Reading..................");
		readDocx(fileName);
		System.out.println("\n\nDocx Tables Reading..........................");
		readWordDocument(fileName);
		
	}

	// code to read .docx document
	public static void readDocx(String fileName) {
		try {
			FileInputStream fis = new FileInputStream(fileName);
			XWPFDocument docs = new XWPFDocument(fis);
			List<XWPFParagraph> paragraph = docs.getParagraphs();
			for (XWPFParagraph para : paragraph) {
				String line = para.getText();
				String str[] = line.split(" ");
				for (int i = 0; i < str.length; i++) {
					String string = str[i];
					System.out.println(string);

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// code to fread table
	public static void readWordDocument(String fileName) {
		try {
			
			if (!(fileName.endsWith(".doc") || fileName.endsWith(".docx"))) {
				throw new FileFormatException();
			} else {

				XWPFDocument doc = new XWPFDocument(new FileInputStream(fileName));

				List<XWPFTable> table = doc.getTables();

				for (XWPFTable xwpfTable : table) {
					List<XWPFTableRow> row = xwpfTable.getRows();
					for (XWPFTableRow xwpfTableRow : row) {
						List<XWPFTableCell> cell = xwpfTableRow.getTableCells();
						for (XWPFTableCell xwpfTableCell : cell) {
							if (xwpfTableCell != null) {
								System.out.println(xwpfTableCell.getText());
								List<XWPFTable> itable = xwpfTableCell.getTables();
								if (itable.size() != 0) {
									for (XWPFTable xwpfiTable : itable) {
										List<XWPFTableRow> irow = xwpfiTable.getRows();
										for (XWPFTableRow xwpfiTableRow : irow) {
											List<XWPFTableCell> icell = xwpfiTableRow.getTableCells();
											for (XWPFTableCell xwpfiTableCell : icell) {
												if (xwpfiTableCell != null) {
													System.out.println(xwpfiTableCell.getText());
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (FileFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
