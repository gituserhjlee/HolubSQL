/*  (c) 2004 Allen I. Holub. All rights reserved.
 *
 *  This code may be used freely by yourself with the following
 *  restrictions:
 *
 *  o Your splash screen, about box, or equivalent, must include
 *    Allen Holub's name, copyright, and URL. For example:
 *
 *      This program contains Allen Holub's SQL package.<br>
 *      (c) 2005 Allen I. Holub. All Rights Reserved.<br>
 *              http://www.holub.com<br>
 *
 *    If your program does not run interactively, then the foregoing
 *    notice must appear in your documentation.
 *
 *  o You may not redistribute (or mirror) the source code.
 *
 *  o You must report any bugs that you find to me. Use the form at
 *    http://www.holub.com/company/contact.html or send email to
 *    allen@Holub.com.
 *
 *  o The software is supplied <em>as is</em>. Neither Allen Holub nor
 *    Holub Associates are responsible for any bugs (or any problems
 *    caused by bugs, including lost productivity or data)
 *    in any of this code.
 */
package com.holub.database;

import java.io.*;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/***
 *	Pass this exporter to a {@link Table#export} implementation to
 *	create a comma-sparated-value version of a {@link Table}.
 *	For example:
 *	<PRE>
 *	Table people  = TableFactory.create( ... );
 *	//...
 *	Writer out = new FileWriter( "people.csv" );
 *	people.export( new CSVExporter(out) );
 *	out.close();
 *	</PRE>
 *	The output file for a table called "name" with
 *	columns "first," "last," and "addrId" would look
 *	like this:
 *	<PRE>
 *	name
 *	first,	last,	addrId
 *	Fred,	Flintstone,	1
 *	Wilma,	Flintstone,	1
 *	Allen,	Holub,	0
 *	</PRE>
 *	The first line is the table name, the second line
 *	identifies the columns, and the subsequent lines define
 *	the rows.
 *
 * @include /etc/license.txt
 * @see Table
 * @see Table.Exporter
 * @see CSVImporter
 */

public class CSVExporter implements Table.Exporter {
    private final Writer out;
    private int width;

    public CSVExporter(Writer out) {
        this.out = out;
    }

    @Override
    public void accept(Visitor visitor) throws IOException {
        visitor.visit(this);
    }

    public void storeMetadata(String tableName,
                              int width,
                              int height,
                              Iterator columnNames) throws IOException {
        this.width = width;
        out.write(tableName == null ? "<anonymous>" : tableName);
        out.write("\n");
        storeRow(columnNames); // comma separated list of columns ids
    }

    public void storeRow(Iterator data) throws IOException {
        int i = width;
        while (data.hasNext()) {
            Object datum = data.next();

            // Null columns are represented by an empty field
            // (two commas in a row). There's nothing to write
            // if the column data is null.
            if (datum != null)
                out.write(datum.toString());

            if (--i > 0)
                out.write(",\t");
        }
        out.write("\n");

    }

    public void startTable() throws IOException {/*nothing to do*/}

    public void endTable() throws IOException {/*nothing to do*/}

    public static class Test {
        @org.junit.Test
        public static void main(String[] args) throws IOException {
            Table testcsv = TableFactory.create("testcsv",
                    new String[]{"Id", "hello"});
            testcsv.insert(new String[]{"1", "안녕하세요"});
            testcsv.insert(new String[]{"2", "헬로"});
            testcsv.insert(new String[]{"3", "구텐탁"});
            testcsv.insert(new String[]{"4", "니하오"});
            Writer out = new FileWriter("c:/dp2020/testcsv");
            CSVExporter builder1 = new CSVExporter(out);
            testcsv.export(builder1);
            out.close();

            StringBuffer stringBuffer = new StringBuffer();
            File file = new File("c:/dp2020/testcsv");
            FileReader fileReader = new FileReader(file);
            int index = 0;
            while ((index = fileReader.read()) != -1) {
                stringBuffer.append((char) index);
            }
//            CSVExporter테스트
            assertThat(stringBuffer.toString(), is(equalTo("testcsv\nId,\thello\n1,\t안녕하세요\n2,\t헬로\n3,\t구텐탁\n4,\t니하오\n")));
            fileReader.close();

            builder1.accept(new getFileVisitor("c:/dp2020/testcsv.csv", testcsv));
            File file1 = new File("c:/dp2020/testcsv.csv");
            StringBuffer stringBuffer1 = new StringBuffer();
            FileReader fileReader1 = new FileReader(file1);
            int index1 = 0;
            while ((index1 = fileReader1.read()) != -1) {
                stringBuffer1.append((char) index1);

            }
//            getFileVisitor테스트
            assertThat(stringBuffer1.toString(), is(equalTo("testcsv\nId,\thello\n1,\t안녕하세요\n2,\t헬로\n3,\t구텐탁\n4,\t니하오\n")));


        }
    }
}
