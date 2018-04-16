package com.wtp;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wtp.model.Member;

@Service
public class MemberBO {
	@Resource(name="memberDAO")
	private MemberDAO memberDAO;
	
	public void add(Member member) throws SQLException {
		memberDAO.addMember(member);
	}

	public void delete(int id) throws SQLException {
		memberDAO.delete(id);
	}

	public Member get(Long id) throws SQLException {
		return memberDAO.selectMember(id);
	}

	public List<Member> list() throws SQLException {
		return memberDAO.selectMemberList();
	}

	public void update(Member member) throws SQLException {
		memberDAO.update(member);
	}
}
