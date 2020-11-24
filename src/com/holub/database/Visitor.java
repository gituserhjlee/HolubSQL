package com.holub.database;

import java.io.IOException;

public interface Visitor {
    void visit(CSVExporter csvExporter) throws IOException;

    void visit(HTMLExporter htmlExporter, String [] tableHead, Object [][] tableData, int height, int width ) throws IOException;

    void visit(JTableExporter jTableExporter);

    void visit(XMLExporter xmlExporter, String [] tableHead, Object [][] tableData, int height, int width, String tableName) throws IOException;



}
