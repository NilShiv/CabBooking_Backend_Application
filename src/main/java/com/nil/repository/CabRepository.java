package com.nil.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nil.entity.Cab;

@Repository
public interface CabRepository extends CrudRepository<Cab, Integer>{

	Optional<Cab> findByCabNo(Integer cabNo);
}
