package com.pet.gettable.model.repository;

import java.util.List;

import com.pet.gettable.domain.GetTable;

public interface GetTableDAO {
	public List selectAll();

	public List selectMember(String member_id);

	public GetTable selectOne(int gettable_id);

	public void insert(GetTable getTable);

	public void delete(int gettable_id);

	public void deleteMember(String member_id);

	public void update(GetTable getTable);
}
