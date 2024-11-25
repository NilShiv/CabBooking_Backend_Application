package com.nil.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nil.entity.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer>{

	List<Booking> findByBookingType(String bookingType);
}
