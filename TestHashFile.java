package test;

import Project.HashFile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static Project.HashFile.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestHashFile {
    @BeforeAll

    private static String HashPath() throws IOException {
        String in = new String("D:\\Java\\managebase\\content.txt");
        return in;
    }
    @Test
    private static void isdfsRight() throws Exception{
        assertEquals("D:\\Java\\managebase\\content.txt","D:\\Java\\managebase\\content.txt"); //正常
        assertNotEquals("123", "D:\\Java\\managebase");//异常

    }



    public static void main(String[] args)throws Exception{

        isdfsRight();
    }
}
