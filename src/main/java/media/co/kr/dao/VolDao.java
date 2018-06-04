package media.co.kr.dao;

import java.sql.SQLException;
import java.util.List;

import media.co.kr.dto.BoardDto;
import media.co.kr.dto.UserDto;
import media.co.kr.dto.VolDto;

public interface VolDao {
   
   /*회원가입시 user db에 정보 추가*/
   public void insert_board(VolDto boardDto) throws SQLException, ClassNotFoundException;
   public List<VolDto> viewboard() throws SQLException, ClassNotFoundException;
   public List<VolDto> boarddetail(int idx);
}