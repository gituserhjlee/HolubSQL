package com.holub.database;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

public class HTMLExporter implements Table.Exporter {
    private final Writer out;
    private String[] tableHead;
    private Object[][] tableData;
    private int rowIndex = 0;
    private int height = 0;
    private int width = 0;
    int rowheight = 0;

    public HTMLExporter(Writer out) {
        this.out = out;
    }

    @Override
    public void accept(Visitor visitor) throws IOException {
        visitor.visit(this, tableHead, tableData, height, width);
    }

    @Override
    public void startTable() throws IOException {
        rowIndex = 0;
        out.write("<html><body>");

    }

    @Override
    public void storeMetadata(String tableName, int width, int height, Iterator columnNames) throws IOException {
        this.height = height;
        this.width = width;
        tableData = new Object[height][width];
        tableHead = new String[width];
        out.write(tableName == null ? "<anonymous>" : tableName);
        int index = 0;
        while (columnNames.hasNext())
            tableHead[index++] = columnNames.next().toString();

        out.write("<table border=\"1\">");

        for (int i = 0; i < tableHead.length; i++) {
            out.write(
                    "<th>" + tableHead[i] + "</th>"

            );
        }
    }


    @Override
    public void storeRow(Iterator data) throws IOException {
        int index = 0;
        int j;
        while (data.hasNext())
            tableData[rowIndex][index++] = data.next();
        ++rowIndex;

        out.write("<tr>");

        for (j = 0; j < width; j++) {
            out.write(
                    "<td>" + tableData[rowheight][j] + "</td>"
            );
        }

        out.write("</tr>");
        rowheight++;
    }


    @Override
    public void endTable() throws IOException {
        out.write(
                "</table></body></html>"
        );
        out.close();
    }

}
