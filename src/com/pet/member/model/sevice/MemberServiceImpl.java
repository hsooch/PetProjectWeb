package com.pet.member.model.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.member.model.domain.Member;
import com.pet.member.model.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public void insert(Member member) {
		memberDAO.insert(member);

	}

	@Override
	public void update(Member member) {
		memberDAO.update(member);
	}

	@Override
	public void delete(int member_id) {
		memberDAO.delete(member_id);
	}

	@Override
	public Member selectNick(String nick) {
		return memberDAO.selectNick(nick);
	}

	@Override
	public Member selectId(String id) {
		return memberDAO.selectId(id);
	}

	@Override
	public List selectAll() {
		return memberDAO.selectAll();
	}

}
