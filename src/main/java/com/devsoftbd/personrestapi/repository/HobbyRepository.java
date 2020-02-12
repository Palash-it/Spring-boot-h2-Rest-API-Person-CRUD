package com.devsoftbd.personrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsoftbd.personrestapi.model.HobbyModel;

public interface HobbyRepository extends JpaRepository<HobbyModel, Long> {

}
