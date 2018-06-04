package media.co.kr.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import media.co.kr.dao.BoardDao;
import media.co.kr.dao.VolDao;
import media.co.kr.dto.BoardDto;
import media.co.kr.dto.UserDto;
import media.co.kr.dto.VolDto;



/**
 * Handles requests for the application home page.
 */
@Controller


public class VoiceController  {
   @Autowired
   private SqlSession sqlsession;
   
   @RequestMapping("/Voice.do")
   public String voice(Model model) throws ClassNotFoundException, SQLException{
      BoardDao Dao = sqlsession.getMapper(BoardDao.class);
      List<BoardDto> list = Dao.viewboard();
      model.addAttribute("list", list);
      return "home.voice";
   }
   @RequestMapping("/userVoice.do")
   public String uservoice(String name, Model model) throws ClassNotFoundException, SQLException{
      BoardDao Dao = sqlsession.getMapper(BoardDao.class);
      System.out.println(name);
      model.addAttribute("value",name);
      model.addAttribute("name",name);
      List<BoardDto> list = Dao.viewboard();
      
      model.addAttribute("list", list);
      return "loginsuccess.voice";
   }
   @RequestMapping("/write.do")
   public String voicewrite(String name, Model model) throws ClassNotFoundException, SQLException{
      model.addAttribute("value",name);
      model.addAttribute("name",name);
      return "loginsuccess.voicewrite";
   }
   @RequestMapping("/goboarduser.do")
   public String goboarduser(String name,int idx ,Model model) throws ClassNotFoundException, SQLException{
      BoardDao Dao = sqlsession.getMapper(BoardDao.class);
      List<BoardDto> list = Dao.boarddetail(idx);
      
      model.addAttribute("value",name);
      model.addAttribute("name",name);
      model.addAttribute("list", list);
      return "loginsuccess.voicedetail";
   }
   
   @RequestMapping("/goboard.do")
   public String goboard(int idx ,Model model) throws ClassNotFoundException, SQLException{
      BoardDao Dao = sqlsession.getMapper(BoardDao.class);
      List<BoardDto> list = Dao.boarddetail(idx);
      
      
      model.addAttribute("list", list);
      return "home.voicedetail";
   }
    private WebApplicationContext context = null;
       @RequestMapping("/download.do")
       public ModelAndView download(@RequestParam("path")String path,
                                     @RequestParam("fileName")String fileName){
            
           String fullPath = path + "/" + fileName;
       
           File file = new File(fullPath);
      
           return new ModelAndView("download", "downloadFile", file);
       }
    

   @RequestMapping(value = "writecomplete.do", method = RequestMethod.POST)
   public String writeBoard(MultipartHttpServletRequest request, HttpServletResponse response, Model model ,String email) throws SQLException, ClassNotFoundException, IOException{
      String fn = request.getParameter("filename");
      System.out.println(fn);
      // 받는다.
      String originalFilename = null ;
      Iterator<String> itr = request.getFileNames();
      String filePath = "C:/test"; // 설정파일로 뺀다.
      while (itr.hasNext()) { // 받은 파일들을 모두 돌린다.
            MultipartFile mpf = request.getFile(itr.next());
            String originFileName = mpf.getOriginalFilename();
            System.out.println("FILE_INFO: " + originFileName); // 받은 파일 리스트 출력'
            
            // MultipartFile mpf = multipartRequest.getFile(itr.next());

            originalFilename = mpf.getOriginalFilename(); // 파일명

            String fileFullPath = filePath + "/" + originalFilename; // 파일 전체 경로
   
            
            try {
               // 파일 저장
                  mpf.transferTo(new File(fileFullPath)); // 파일저장 실제로는 service에서 처리
                  FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("C:\\test" + mpf.getOriginalFilename()));

            } catch (Exception e) {
                  System.out.println("postTempFile_ERROR======>" + fileFullPath);
                  e.printStackTrace();
            }

      }
      BoardDao Dao = sqlsession.getMapper(BoardDao.class);
      String name = request.getParameter("name");
      System.out.println("C:/test/"+name);
      
      String contents =request.getParameter("contents");
      Date d =new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String date = sdf.format(d);
      System.out.println(name + " " + email + " " + contents + " " + date+" "+originalFilename);
   
   //   BoardDto dto = new BoardDto(name, email, contents,date);      
      BoardDto dto = new BoardDto(name,email,contents,date,fn);
      Dao.insert_board(dto);
      model.addAttribute("value",name);
      return "loginsuccess.main";
   }
   @RequestMapping("/volounteer.do")
   public String volentee(Model model) throws ClassNotFoundException, SQLException{

      return "home.voice";
   }
   @RequestMapping("/Volounteer.do")
   public String volunteer(Model model) throws ClassNotFoundException, SQLException{
      VolDao Dao = sqlsession.getMapper(VolDao.class);
      List<VolDto> list = Dao.viewboard();
      model.addAttribute("list", list);
      return "home.volunteer";
   }
   @RequestMapping("/userVol.do")
   public String uservol(String name, Model model) throws ClassNotFoundException, SQLException{
      VolDao Dao = sqlsession.getMapper(VolDao.class);
      System.out.println(name);
      model.addAttribute("value",name);
      model.addAttribute("name",name);
      List<VolDto> list = Dao.viewboard();
      
      model.addAttribute("list", list);
      return "loginsuccess.volunteer";
   }
   @RequestMapping("/volwrite.do")
   public String volwrite(String name, Model model) throws ClassNotFoundException, SQLException{
      model.addAttribute("value",name);
      model.addAttribute("name",name);
      return "loginsuccess.volwrite";
   }
   @RequestMapping("/volgoboarduser.do")
   public String volgoboarduser(String name,int idx ,Model model) throws ClassNotFoundException, SQLException{
      VolDao Dao = sqlsession.getMapper(VolDao.class);
      List<VolDto> list = Dao.boarddetail(idx);
      
      model.addAttribute("value",name);
      model.addAttribute("name",name);
      model.addAttribute("list", list);
      return "loginsuccess.voldetail";
   }
   
   @RequestMapping("/volgoboard.do")
   public String volgoboard(int idx ,Model model) throws ClassNotFoundException, SQLException{
      VolDao Dao = sqlsession.getMapper(VolDao.class);
      List<VolDto> list = Dao.boarddetail(idx);
      
      
      model.addAttribute("list", list);
      return "home.volunteerdetail";
   }


   @RequestMapping(value = "volwritecomplete.do", method = RequestMethod.POST)
   public String volwriteBoard(MultipartHttpServletRequest request, HttpServletResponse response, Model model ,String email) throws SQLException, ClassNotFoundException, IOException{
      String fn = request.getParameter("filename");
      System.out.println(fn);
      // 받는다.
      String originalFilename = null ;
      Iterator<String> itr = request.getFileNames();
      String filePath = "C:/test"; // 설정파일로 뺀다.
      while (itr.hasNext()) { // 받은 파일들을 모두 돌린다.
            MultipartFile mpf = request.getFile(itr.next());
            String originFileName = mpf.getOriginalFilename();
            System.out.println("FILE_INFO: " + originFileName); // 받은 파일 리스트 출력'
            
            // MultipartFile mpf = multipartRequest.getFile(itr.next());

            originalFilename = mpf.getOriginalFilename(); // 파일명

            String fileFullPath = filePath + "/" + originalFilename; // 파일 전체 경로
   
            
            try {
               // 파일 저장
                  mpf.transferTo(new File(fileFullPath)); // 파일저장 실제로는 service에서 처리
                  FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("C:\\test" + mpf.getOriginalFilename()));

            } catch (Exception e) {
                  System.out.println("postTempFile_ERROR======>" + fileFullPath);
                  e.printStackTrace();
            }

      }
      VolDao Dao = sqlsession.getMapper(VolDao.class);
      String name = request.getParameter("name");
      System.out.println("C:/test/"+name);
      
      String contents =request.getParameter("contents");
      Date d =new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String date = sdf.format(d);
      System.out.println(name + " " + email + " " + contents + " " + date+" "+originalFilename);
   
   //   BoardDto dto = new BoardDto(name, email, contents,date);      
      VolDto dto = new VolDto(name,email,contents,date,fn);
      Dao.insert_board(dto);
      model.addAttribute("value",name);
      return "loginsuccess.main";
   }
}