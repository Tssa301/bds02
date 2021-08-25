package com.tssa301.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tssa301.bds02.dto.EventDTO;
import com.tssa301.bds02.entities.City;
import com.tssa301.bds02.entities.Event;
import com.tssa301.bds02.repositories.EventRepository;
import com.tssa301.bds02.services.exceptions.NotFoundException;

@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	public List<EventDTO> findAll() {
		List<Event> list = repository.findAll(Sort.by("name"));
		return list.stream().map(x -> new EventDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		try {
			Event entity = repository.getOne(id);
			entity.setNamne(dto.getName());
			entity.setDate(dto.getDate());
			entity.setUrl(dto.getUrl());
			entity.setCity(new City(dto.getCityId(), null));
			return new EventDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new NotFoundException("Id not found " + id);
		}
	}
}
