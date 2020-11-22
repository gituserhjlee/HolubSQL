package com.holub.database;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class XMLExporterTest {
    public static void main(String[] args) throws IOException {
        Table people = TableFactory.create( "people",
                new String[]{ "First", "Last","Id"	} );
        people.insert( new String[]{ "Allen",	"Holub" ,"1"	} );
        people.insert( new String[]{ "Ichabod",	"Crane" ,"2"	} );
        people.insert( new String[]{ "Rip",		"VanWinkle" ,"3"} );
        people.insert( new String[]{ "Goldie",	"Locks" ,"4"	} );
        XMLExporter builder1=new XMLExporter();
        people.export(builder1);
        builder1.getXML("people");

        StringBuffer stringBuffer=new StringBuffer();
        File file= new File("people.xml");
        FileReader fileReader=new FileReader(file);
        int index=0;
        while((index=fileReader.read())!=-1){
            stringBuffer.append((char)index);

        }
        assertThat(stringBuffer.toString(), is(equalTo("<root><title>people</title><DATA><First>Allen</First><Last>Holub</Last><Id>1</Id></DATA><DATA><First>Ichabod</First><Last>Crane</Last><Id>2</Id></DATA><DATA><First>Rip</First><Last>VanWinkle</Last><Id>3</Id></DATA><DATA><First>Goldie</First><Last>Locks</Last><Id>4</Id></DATA></root>")));
        fileReader.close();


        Table university = TableFactory.create( "university",
                new String[]{ "name", "location",} );
        university.insert( new String[]{ "chungang","seoul" 	} );
        university.insert( new String[]{ "seoul",	"seoul" 	} );
        university.insert( new String[]{ "woosong",	"daejeon" } );
        XMLExporter builder2=new XMLExporter();
        university.export(builder2);
        builder2.getXML("university");

        StringBuffer stringBuffer2=new StringBuffer();
        File file2= new File("university.xml");
        FileReader fileReader2=new FileReader(file2);
        int index2=0;
        while((index2=fileReader2.read())!=-1){
            stringBuffer2.append((char)index2);

        }
        assertThat(stringBuffer2.toString(), is(equalTo("<root><title>university</title><DATA><name>chungang</name><location>seoul</location></DATA><DATA><name>seoul</name><location>seoul</location></DATA><DATA><name>woosong</name><location>daejeon</location></DATA></root>")));
        fileReader2.close();
    }
}