package com.holub.database;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

public class XMLExporter implements Table.Exporter {
    private final Writer out;
    private String[] tableHead;
    private Object[][] tableData;
    private String tableName;
    private int rowIndex = 0;
    private int height = 0;
    private int width = 0;
    private int rowheight = 0;

    public XMLExporter(Writer out) {
        this.out = out;
    }

    @Override
    public void accept(Visitor visitor) throws IOException {
        visitor.visit(this, tableHead, tableData, height, width, tableName);
    }

    @Override
    public void startTable() throws IOException {
        rowIndex = 0;
        out.write("<root>");

    }

    @Override
    public void storeMetadata(String tableName, int width, int height, Iterator columnNames) throws IOException {
        this.tableName = tableName;
        this.height = height;
        this.width = width;
        tableData = new Object[height][width];
        tableHead = new String[width];
        out.write("<title>" + tableName + "</title>");
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


        out.write("<DATA>");
        for (int j = 0; j < width; j++) {
            out.write("<" + tableHead[j] + ">" + tableData[rowheight][j] + "</" + tableHead[j] + ">");
        }
        out.write("</DATA>");
        rowheight++;
    }

    @Override
    public void endTable() throws IOException {
        out.write("</root>");
        out.close();
    }

}
