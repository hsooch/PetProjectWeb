package com.pet.regist.model.sevice;

import java.util.List;

import com.pet.regist.model.domain.Regist;

public interface RegistService {
	public void insert(Regist regist);

	public void update(Regist regist);

	public void delete(int regist_id);

	public void deleteMember(int member_id);

	public Regist selectOne(int regist_id);

	public List selectAll();
}
