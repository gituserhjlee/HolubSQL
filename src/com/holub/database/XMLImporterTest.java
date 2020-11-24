package com.holub.database;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class XMLImporterTest {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        String file = "c:/dp2020/people.xml"; //people.xml을 읽어보겠다.
        XMLImporter builder1 = new XMLImporter(file);
        builder1.startTable();
        assertThat(builder1.loadTableName(), is(equalTo("people")));
        assertThat(builder1.loadWidth(), is(equalTo(3)));

        String file2 = "c:/dp2020/university.xml"; //university.xml을 읽어보겠다.
        XMLImporter builder2 = new XMLImporter(file2);
        builder2.startTable();
        assertThat(builder2.loadTableName(), is(equalTo("university")));
        assertThat(builder2.loadWidth(), is(equalTo(2)));


    }

}