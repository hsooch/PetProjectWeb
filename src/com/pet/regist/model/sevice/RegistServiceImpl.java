package com.pet.regist.model.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.regist.model.domain.Regist;
import com.pet.regist.model.repository.RegistDAO;

@Service
public class RegistServiceImpl implements RegistService {
	@Autowired
	private RegistDAO registDAO;

	@Override
	public void insert(Regist regist) {
		registDAO.insert(regist);
	}

	@Override
	public void update(Regist regist) {
		registDAO.update(regist);
	}

	@Override
	public void delete(int regist_id) {
		registDAO.delete(regist_id);
	}

	@Override
	public void deleteMember(int member_id) {
		registDAO.delete(member_id);
	}

	@Override
	public Regist selectOne(int regist_id) {
		return registDAO.selectOne(regist_id);
	}

	@Override
	public List selectAll() {
		return registDAO.selectAll();
	}

}
