package com.pet.gettable.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.gettable.domain.GetTable;
import com.pet.gettable.model.repository.GetTableDAO;

@Service
public class GetTableServiceImpl implements GetTableService {
	@Autowired
	GetTableDAO getTableDAO;

	@Override
	public List selectAll() {
		return getTableDAO.selectAll();
	}

	@Override
	public List selectMember(String member_id) {
		return getTableDAO.selectMember(member_id);
	}

	@Override
	public GetTable selectOne(int gettable_id) {
		return getTableDAO.selectOne(gettable_id);
	}

	@Override
	public void insert(GetTable getTable) {
		getTableDAO.insert(getTable);
	}

	@Override
	public void delete(int gettable_id) {
		getTableDAO.delete(gettable_id);
	}

	@Override
	public void deleteMember(String member_id) {
		getTableDAO.deleteMember(member_id);
	}

	@Override
	public void update(GetTable getTable) {
		getTableDAO.update(getTable);
	}

}
