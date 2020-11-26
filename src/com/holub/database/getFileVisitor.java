package com.holub.database;

import javax.swing.*;
import java.io.*;

public class getFileVisitor implements Visitor {
    String name;
    JTableExporter jTableExporter;
    Table table;

    public getFileVisitor(String name) {
        this.name = name;
    }

    public getFileVisitor(String name, Table table) {
        this.name = name;
        this.table = table;
    }

    public getFileVisitor(JTableExporter jTableExporter) {
        this.jTableExporter = jTableExporter;
    }


    @Override
    public void visit(CSVExporter csvExporter) throws IOException {
        System.out.println("Making csv");
        File file = new File(name);
        Writer out2 = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file), "UTF-8"));
        CSVExporter builder2 = new CSVExporter(out2);
        table.export(builder2);
        out2.close();

    }

    @Override
    public void visit(HTMLExporter htmlExporter, String[] tableHead, Object[][] tableData, int height, int width) throws IOException {
        System.out.println("Making html");

        File file = new File("c:/dp2020/" + name + ".html");
        Writer out = null;
        out = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file), "UTF-8"));
        out.write("<html><body><table border=\"1\">"
        );
        for (int i = 0; i < tableHead.length; i++) {
            out.write(
                    "<th>" + tableHead[i] + "</th>"

            );
        }
        for (int i = 0; i < height; i++) {
            out.write("<tr>");
            for (int j = 0; j < width; j++) {
                out.write(
                        "<td>" + tableData[i][j] + "</td>"
                );
            }
            out.write("</tr>");

        }
        out.write(
                "</table></body></html>"
        );
        out.close();

    }

    @Override
    public JScrollPane visit(JTableExporter jTableExporter) {
        System.out.println("Making jtable");
        JScrollPane jp = jTableExporter.accept2(new decorateVisitor(jTableExporter));
        javax.swing.JFrame frame = new javax.swing.JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(jp);
        frame.pack();
        frame.setVisible(true);
        return jp;
    }

    @Override
    public void visit(XMLExporter xmlExporter, String[] tableHead, Object[][] tableData, int height, int width, String tableName) throws IOException {
        System.out.println("Making xml");

        File file = new File("c:/dp2020/" + name + ".xml");
        Writer out = null;
        out = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file), "UTF-8"));
        out.write("<root><title>" + tableName + "</title>");
        for (int i = 0; i < height; i++) {
            out.write("<DATA>");
            for (int j = 0; j < width; j++) {
                out.write("<" + tableHead[j] + ">" + tableData[i][j] + "</" + tableHead[j] + ">");
            }
            out.write("</DATA>");
        }
        out.write("</root>");
        out.close();


    }
}
