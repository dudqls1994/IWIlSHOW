package media.co.kr.dao;

import java.sql.SQLException;
import java.util.List;

import media.co.kr.dto.BoardDto;
import media.co.kr.dto.UserDto;

public interface BoardDao {
   
   /*회원가입시 user db에 정보 추가*/
   public void insert_board(BoardDto boardDto) throws SQLException, ClassNotFoundException;
   public List<BoardDto> viewboard() throws SQLException, ClassNotFoundException;
   public List<BoardDto> boarddetail(int idx);
}