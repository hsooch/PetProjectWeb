package com.pet.registphoto.model.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.registphoto.model.domain.RegistPhoto;
import com.pet.registphoto.model.repository.RegistPhotoDAO;

@Service
public class RegistPhotoServiceImpl implements RegistPhotoService {
	@Autowired
	private RegistPhotoDAO registPhotoDAO;

	@Override
	public void insert(RegistPhoto registPhoto) {
		registPhotoDAO.insert(registPhoto);
	}

	@Override
	public void update(RegistPhoto registPhoto) {
		registPhotoDAO.update(registPhoto);
	}

	@Override
	public void delete(int registPhoto_id) {
		registPhotoDAO.delete(registPhoto_id);
	}

	@Override
	public RegistPhoto selectOne(int registPhoto_id) {
		return registPhotoDAO.selectOne(registPhoto_id);
	}

	@Override
	public List selectAll() {
		return registPhotoDAO.selectAll();
	}

}
