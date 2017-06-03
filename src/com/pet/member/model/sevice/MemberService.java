package com.pet.member.model.sevice;

import java.util.List;

import com.pet.member.model.domain.Member;

public interface MemberService {
	public void insert(Member member);

	public void update(Member member);

	public void delete(int member_id);

	public Member selectNick(String nick);

	public Member selectId(String id);

	public List selectAll();
}
