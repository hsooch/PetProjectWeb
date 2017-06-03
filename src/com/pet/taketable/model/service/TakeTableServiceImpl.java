package com.pet.taketable.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.taketable.domain.TakeTable;
import com.pet.taketable.model.repository.TakeTableDAO;

@Service
public class TakeTableServiceImpl implements TakeTableService {
	@Autowired
	TakeTableDAO takeTableDAO;

	@Override
	public List selectAll() {
		return takeTableDAO.selectAll();
	}

	@Override
	public List selectMember(String member_id) {
		return takeTableDAO.selectMember(member_id);
	}

	@Override
	public TakeTable selectOne(int taketable_id) {
		return takeTableDAO.selectOne(taketable_id);
	}

	@Override
	public void delete(int taketable_id) {
		takeTableDAO.delete(taketable_id);
	}

	@Override
	public void deleteMember(String member_id) {
		takeTableDAO.deleteMember(member_id);
	}

	@Override
	public void update(TakeTable takeTable) {
		takeTableDAO.update(takeTable);
	}

	@Override
	public void insert(TakeTable takeTable) {
		takeTableDAO.insert(takeTable);
	}

}
