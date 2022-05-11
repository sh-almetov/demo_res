package com.example.demo_res;


import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@ComponentScan
@EnableAutoConfiguration
public class MyRunner implements CommandLineRunner {

    @Value("classpath:data.txt")
    private Resource res;

    @Autowired
    private CountWords countWords;

    @Autowired
    ApplicationContext appContext;


    @Override
    public void run(String... args) throws Exception {


        Resource resource = new ClassPathResource("data.txt");

        System.out.println("---------------------------------------");
// this will work when running the application, but will fail when running as jar
        Resource rs = appContext.getResource("classpath:testfolder/message.txt");
        System.out.println("rs: " + rs.getURI());
        System.out.println("res: " + res.getURI());
        System.out.println("---------------------------------------");

        File file = getResourceFile("data.txt");
        file = getResourceFile("data/data.txt");
    }

    private File getResourceFile(final String fileName) {
        URL url = this.getClass()
                .getClassLoader()
                .getResource(fileName);
        File file = null;
        if (url == null) {
            System.out.println(fileName + " is not found 1");
        } else {
            file = new File(url.getFile());
            System.out.println(fileName + " is found");
            System.out.println(url.getPath());

            try (InputStream in = url.openStream()) {
                int read;
                byte[] bytes = new byte[1024];
                System.out.println("**************************");
                while ((read = in.read(bytes)) != -1) {
                    //outputStream.write(bytes, 0, read);
                    System.out.write(bytes, 0, read);
                }
                System.out.println();
                System.out.println("**************************");
            } catch (IOException e) {
                e.printStackTrace();
            }
//            try {
//                FileReader fr=new FileReader(file);   //reads the file
//                BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
//                StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
//                String line;
//                while((line=br.readLine())!=null)
//                {
//                    sb.append(line);      //appends line to string buffer
//                    sb.append("\n");     //line feed
//                }
//                fr.close();    //closes the stream and release the resources
//                System.out.println("Contents of File: ");
//                System.out.println(sb.toString());
//
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//                e.printStackTrace(System.out);
//            }
        }
        return file;
    }
}