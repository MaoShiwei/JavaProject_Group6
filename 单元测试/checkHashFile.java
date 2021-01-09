package test;
import Project.HashFile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;


public class checkHashFile {
    @BeforeAll

    private static InputStream getHashInput() throws IOException{
        InputStream in = new FileInputStream("D:\\javademo\\hashcal.java");
        return in;
    }
    @Test
    private static void isSHA1ChecksumRight() throws Exception{
        //assertEquals("723df3b2748ed3edf7cc48e0318f3ae6c31e151”,HashFile.SHA1Checksum("D:\\javademo", "D:\\javademo\\anliswitch.java")); //正常
        assertNotEquals("123123", HashFile.SHA1Checksum(getHashInput()));//异常

    }


    public static void main(String[] args)throws Exception{

        isSHA1ChecksumRight();
    }
}
