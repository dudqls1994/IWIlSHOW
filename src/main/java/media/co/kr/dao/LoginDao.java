package media.co.kr.dao;

import java.sql.SQLException;
import java.util.List;

import media.co.kr.dto.UserDto;

public interface LoginDao {
	
	/*회원가입시 user db에 정보 추가*/
	public void insert_user(UserDto userDto) throws SQLException, ClassNotFoundException;
	public List<UserDto> Login(String email) throws SQLException, ClassNotFoundException;

}
