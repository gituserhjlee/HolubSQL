package com.holub.database;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class HTMLExporterTest {
    public static void main(String[] args) throws IOException {
        Table people = TableFactory.create( "people",
                new String[]{ "First", "Last","Id"	} );
        people.insert( new String[]{ "Allen",	"Holub" ,"1"	} );
        people.insert( new String[]{ "Ichabod",	"Crane" ,"2"	} );
        people.insert( new String[]{ "Rip",		"VanWinkle" ,"3"} );
        people.insert( new String[]{ "Goldie",	"Locks" ,"4"	} );
        HTMLExporter builder1=new HTMLExporter();
        people.export(builder1);
        builder1.accept(new getFileVisitor("people"));

        StringBuffer stringBuffer=new StringBuffer();
        File file= new File("c:/dp2020/people.html");
        FileReader fileReader=new FileReader(file);
        int index=0;
        while((index=fileReader.read())!=-1){
           stringBuffer.append((char)index);

        }
        assertThat(stringBuffer.toString(), is(equalTo("<html><body><table border=\"1\"><th>First</th><th>Last</th><th>Id</th><tr><td>Allen</td><td>Holub</td><td>1</td></tr><tr><td>Ichabod</td><td>Crane</td><td>2</td></tr><tr><td>Rip</td><td>VanWinkle</td><td>3</td></tr><tr><td>Goldie</td><td>Locks</td><td>4</td></tr></table></body></html>")));
        fileReader.close();

        Table university = TableFactory.create( "university",
                new String[]{ "name", "location",} );
        university.insert( new String[]{ "chungang","seoul" 	} );
        university.insert( new String[]{ "seoul",	"seoul" 	} );
        university.insert( new String[]{ "woosong",	"daejeon" } );
        HTMLExporter builder2=new HTMLExporter();
        university.export(builder2);
        builder2.accept(new getFileVisitor("university"));

        StringBuffer stringBuffer2=new StringBuffer();
        File file2= new File("c:/dp2020/university.html");
        FileReader fileReader2=new FileReader(file2);
        int index2=0;
        while((index2=fileReader2.read())!=-1){
            stringBuffer2.append((char)index2);

        }
        assertThat(stringBuffer2.toString(), is(equalTo("<html><body><table border=\"1\"><th>name</th><th>location</th><tr><td>chungang</td><td>seoul</td></tr><tr><td>seoul</td><td>seoul</td></tr><tr><td>woosong</td><td>daejeon</td></tr></table></body></html>")));
        fileReader2.close();

    }

}