package media.co.kr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
* Handles requests for the application home page.
*/

@Controller

public class audioPlayer {

  @RequestMapping(value="/searchimage.do",method=RequestMethod.POST)
    public String showhome(){   
        return "home.imagesearch";
    }

  @RequestMapping(value="/PlayFile.do",method=RequestMethod.GET)
    public void playAudio(HttpServletRequest request,HttpServletResponse response){
            System.out.println("--playFile");
            File file = new File("C:\\webcam\\test.wav");
            FileInputStream fis;
            byte[] buffer=null;
            try {
                fis = new FileInputStream(file);
                buffer= new byte[fis.available()];
                fis.read(buffer);
                fis.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }        
          

           response.setContentType("audio/vnd.wave");
          
        try{                
            response.getOutputStream().write(buffer);              
        } catch (IOException e) {
            e.printStackTrace();
        }       

    }
}
