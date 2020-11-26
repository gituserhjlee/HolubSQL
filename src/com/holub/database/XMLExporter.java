package com.holub.database;
import java.io.IOException;
import java.util.Iterator;

public class XMLExporter implements Table.Exporter {
    private String[] tableHead;
    private Object[][] tableData;
    private String tableName;
    private int rowIndex = 0;
    private int height = 0;
    private int width = 0;

    @Override
    public void accept(Visitor visitor) throws IOException {
        visitor.visit(this, tableHead, tableData, height, width, tableName);
    }

    @Override
    public void startTable() throws IOException {
        rowIndex = 0;
    }

    @Override
    public void storeMetadata(String tableName, int width, int height, Iterator columnNames) throws IOException {
        this.tableName = tableName;
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
//    public void getXML(String name)throws IOException{
//        File file = new File(name + ".xml");
//        Writer out = null;
//        out = new BufferedWriter(
//                new OutputStreamWriter(
//                        new FileOutputStream(file), "UTF-8"));
//        out.write("<root><title>"+tableName+"</title>");
//        for(int i=0;i<height;i++){
//            out.write("<DATA>");
//            for(int j=0;j<width;j++){
//                out.write("<"+tableHead[j]+">"+tableData[i][j]+"</"+tableHead[j]+">");
//            }
//            out.write("</DATA>");
//        }
//        out.write("</root>");
//        out.close();
//    }
}
