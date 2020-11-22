package com.holub.database;

import javax.swing.text.html.HTML;
import java.io.*;
import java.util.Iterator;

public class HTMLExporter implements Table.Exporter {
    private String[] tableHead;
    private Object[][] tableData;
    private int rowIndex = 0;
    private int height = 0;
    private int width = 0;

    @Override
    public void startTable() throws IOException {
        rowIndex = 0;
    }

    @Override
    public void storeMetadata(String tableName, int width, int height, Iterator columnNames) throws IOException {
        this.height = height;
        this.width = width;
        tableData = new Object[height][width];
        tableHead = new String[width];

        int index = 0;
        while (columnNames.hasNext())
            tableHead[index++] = columnNames.next().toString();
    }

    @Override
    public void storeRow(Iterator data) throws IOException {
        int index = 0;
        while (data.hasNext())
            tableData[rowIndex][index++] = data.next();
        ++rowIndex;
    }


    @Override
    public void endTable() throws IOException {

    }

    public void getHTML(String name) throws IOException {
        File file = new File(name + ".html");
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


}
