package com.wtp;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtp.model.Member;

@Repository("memberDAO")
public class MemberDAO extends AbstractDAO{
	private static final String NAMESPACE = "Member.";

	public void addMember(Member member) throws SQLException {
		master.insert(NAMESPACE + "add", member);
	}
	
	public void delete(int id) throws SQLException {
		master.delete(NAMESPACE + "delete", id);
	}

	public Member selectMember(Long id) throws SQLException {
		Member member = new Member();
		member.setId(id);
		System.out.println("selectMember:" + member.getId());
		return (Member) slave.queryForObject(NAMESPACE + "selectMemberList", member);
	}

	@SuppressWarnings("unchecked")
	public List<Member> selectMemberList() throws SQLException {
		return slave.queryForList(NAMESPACE + "selectMemberList", null);
	}

	public void update(Member member) throws SQLException {
		master.update(NAMESPACE + "update", member);
	}
}
