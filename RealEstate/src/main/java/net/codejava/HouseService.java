package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseService {
	@Autowired
	private HouseRepository repo;
	
	public List<House> listAll() {		
		return repo.findAll();
	}
	
	public void save(House house) {
		repo.save(house);
	}
	
	public House get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
