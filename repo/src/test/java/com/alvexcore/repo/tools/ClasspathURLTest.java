package com.alvexcore.repo.tools;

import junit.framework.TestSuite;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.*;

public class ClasspathURLTest extends TestSuite {

    private static File tmpFile;

    @BeforeClass
    public static void setUpBeforClass() throws IOException, NoSuchFieldException, IllegalAccessException {
        URL.setURLStreamHandlerFactory(new ClasspathURLStreamHandlerFactory(null));

        String tmpDir = System.getProperties().getProperty("java.io.tmpdir");

        File baseDir = new File(tmpDir);
        tmpFile = new File(baseDir, "ClasspathURLTest");

        FileOutputStream fileStream = new FileOutputStream(tmpFile);
        fileStream.write("Content".getBytes());

        fileStream.close();

    }

    @AfterClass
    public static void tearDownAfterClass()
    {
        tmpFile.delete();
    }

    @Test
    public void testClasspathURL() throws IOException {
        URL url = new URL("classpath://log4j.properties");
        InputStream stream = url.openStream();
        assert stream.available() > 0;
    }

    @Test
    public void testFileURL() throws IOException {
        URL url = new URL("file://" + tmpFile.getAbsolutePath());
        InputStream stream = url.openStream();

        assert stream.available() > 0;
    }

    @Test
    public void testInjection() throws NoSuchFieldException, IllegalAccessException {
        ClasspathURLStreamHandlerFactory.inject();
        ClasspathURLStreamHandlerFactory.inject();
    }

}
