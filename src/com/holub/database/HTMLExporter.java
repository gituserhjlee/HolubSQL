package com.holub.database;

import javax.swing.text.html.HTML;
import java.io.*;
import java.util.Iterator;

public class HTMLExporter implements Table.Exporter {
    private String [] tableHead;
    private Object[][] tableData;
    private int rowIndex=0;


    @Override
    public void startTable() throws IOException {
        rowIndex=0;
    }
    @Override
    public void storeMetadata(String tableName, int width, int height, Iterator columnNames) throws IOException {
        tableData=new Object[height][width];
        tableHead=new String[width];

        int index=0;
        while(columnNames.hasNext())
            tableHead[index++]=columnNames.next().toString();
    }

    @Override
    public void storeRow(Iterator data) throws IOException {
       int index=0;
       while(data.hasNext())
           tableData[rowIndex][index++]=data.next();
       ++rowIndex;
    }


    @Override
    public void endTable() throws IOException {

    }

    public void getHTML() throws IOException {
        File file=new File("c:/dp2020/test.html");
        Writer out=null;
        out=new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file), "UTF-8"));
        out.write("<html>" +
                        "<body>" +
                        "<table>" +
                        "<th>"+tableHead[0]+"</th>" +
                        "<th>"+tableHead[1]+"</th>" +
                        "<tr><td>"+tableData[0][0]+"</td><td>"+tableData[0][1]+"</td></tr>"+
                        "</table>" +
                        "</body>" +
                        "</html>"

                );
        out.close();

    }

//어떤 데이터가 들어오면 그걸 table로 만들어서 html파일로 바꿔서 보여주면 될듯.

}
