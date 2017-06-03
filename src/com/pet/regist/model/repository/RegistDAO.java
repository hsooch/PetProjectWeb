package com.pet.regist.model.repository;

import java.util.List;

import com.pet.regist.model.domain.Regist;

public interface RegistDAO {
	public void insert(Regist regist);

	public void update(Regist regist);

	public void delete(int regist_id);

	public void deleteMember(int member_id);

	public Regist selectOne(int regist_id);

	public List selectAll();
}
