package com.example.ojbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.ojbackend.judger.complier.CPlusCompiler;

import java.io.FileNotFoundException;

@SpringBootTest
class OjbackendApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void testCompiler() throws FileNotFoundException {
        CPlusCompiler cPlusCompiler = new CPlusCompiler();
        cPlusCompiler.complier();
        System.out.println(cPlusCompiler.getRes());
    }
}
