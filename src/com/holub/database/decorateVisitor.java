package com.holub.database;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class decorateVisitor implements Visitor {
    String name;
    JTableExporter jTableExporter;

    public decorateVisitor(String name) {
        this.name = name;
    }

    public decorateVisitor(JTableExporter jTableExporter) {
        this.jTableExporter = jTableExporter;
    }

    @Override
    public void visit(CSVExporter csvExporter) throws IOException {
        System.out.println("decorating CSV");

    }

    @Override
    public void visit(HTMLExporter htmlExporter, String[] tableHead, Object[][] tableData, int height, int width) throws IOException {
        System.out.println("decorating HTML");
        StringBuffer stringBuffer = new StringBuffer();
        File file = new File("c:/dp2020/" + name + ".html");
        FileReader fileReader = new FileReader(file);
        int index = 0;
        while ((index = fileReader.read()) != -1) {
            stringBuffer.append((char) index);

        }
        String content = stringBuffer.toString();
        content = content.replaceAll("border=\"1\"", "border=\"1\" bordercolor=\"blue\"");
        File file2 = new File("c:/dp2020/" + name + ".html");
        Writer out = null;
        out = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file2), "UTF-8"));
        out.write(content);
        out.close();

    }


    @Override
    public JScrollPane visit(JTableExporter jTableExporter) {
        System.out.println("decorating JTable");
        JTable jtable = jTableExporter.getJTable();
        jtable.setBackground(Color.orange);
        JScrollPane p = new JScrollPane(jtable);
        p.getViewport().setBackground(Color.pink);
        return p;
    }

    @Override
    public void visit(XMLExporter xmlExporter, String[] tableHead, Object[][] tableData, int height, int width, String tableName) throws IOException {
        System.out.println("decorating XML");

    }
}
