package com.pet.registphoto.model.repository;

import java.util.List;

import com.pet.registphoto.model.domain.RegistPhoto;

public interface RegistPhotoDAO {
	public void insert(RegistPhoto registPhoto);

	public void update(RegistPhoto registPhoto);

	public void delete(int registPhoto_id);

	public RegistPhoto selectOne(int registPhoto_id);

	public List selectAll();
}
