package com.example.demo.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.random.RandomGeneratorFactory;

@Component
public class WSScheduler {

//check whether there is any update in file
    // if yes, push it to the UI

public static RandomAccessFile randomAccessFile;

long offset;

@Autowired
public SimpMessagingTemplate simpMessagingTemplate;

WSScheduler() throws IOException {
    //read the file
    randomAccessFile = new RandomAccessFile("/Users/raunakjohar/Desktop/demo/src/main/resources/log.txt","r");
    offset = findOffset();
}


long findOffset() throws IOException {
    long lineCount=0;

    randomAccessFile.seek(offset);
    long lengthBytes = randomAccessFile.length();

    while(randomAccessFile.getFilePointer() < lengthBytes){
        randomAccessFile.readLine(); //(checks if line end is there)
        lineCount++;
    }

    if(lineCount>10){
        return lineCount-10;
    }

    return lineCount;
}

@Scheduled(fixedDelay =500, initialDelay = 3000)
void checkUpdates() throws IOException {

    randomAccessFile.seek(offset);
    String line = "";

    while((line=randomAccessFile.readLine())!=null){
        String payload = "{content: " + line + "}";
        //get that line - push it to frontend
        System.out.println("Payload: "+  payload);
        simpMessagingTemplate.convertAndSend("/logs",payload);
    }

    offset=randomAccessFile.getFilePointer();
}

}
