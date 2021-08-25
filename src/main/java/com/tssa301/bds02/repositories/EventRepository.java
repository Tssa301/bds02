package com.tssa301.bds02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tssa301.bds02.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
