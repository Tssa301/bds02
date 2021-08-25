package com.tssa301.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tssa301.bds02.dto.CityDTO;
import com.tssa301.bds02.entities.City;
import com.tssa301.bds02.repositories.CityRepository;
import com.tssa301.bds02.services.exceptions.DatabaseException;
import com.tssa301.bds02.services.exceptions.NotFoundException;


@Service
public class CityService {
	
	@Autowired
	private CityRepository repository;
	
	public List<CityDTO> findAll() {
		List<City> list = repository.findAll(Sort.by("name"));
		return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new NotFoundException("Id not found " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}
