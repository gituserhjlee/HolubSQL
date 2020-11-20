package com.holub.database;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class HTMLExporterTest {
    public static void main(String[] args) throws IOException {
        Table people=TableFactory.create("people", new String[]{"last", "first"});
        people.insert(new String[]{"lee", "hj"});
        HTMLExporter builder=new HTMLExporter();
        people.export(builder);

        builder.getHTML();
        StringBuffer stringBuffer=new StringBuffer();
        File file= new File("test.html");
        FileReader fileReader=new FileReader(file);
        int index=0;
        while((index=fileReader.read())!=-1){
           stringBuffer.append((char)index);

        }
        assertThat(stringBuffer.toString(), is(equalTo("<html><body><table><th>last</th><th>first</th><tr><td>lee</td><td>hj</td></tr></table></body></html>")));
        fileReader.close();
    }

}