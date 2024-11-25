package com.nil.service;

import java.util.List;

import com.nil.dto.BookingDTO;
import com.nil.exception.CabBookingException;

public interface CabBookingService {

	public Integer bookCab(BookingDTO bookingDTO) throws CabBookingException;
	public List<BookingDTO> getDetailsByBookingType(String bookingType) throws CabBookingException;
}
