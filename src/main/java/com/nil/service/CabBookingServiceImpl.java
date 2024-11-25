package com.nil.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nil.dto.BookingDTO;
import com.nil.dto.CabDTO;
import com.nil.entity.Booking;
import com.nil.entity.Cab;
import com.nil.exception.CabBookingException;
import com.nil.repository.BookingRepository;
import com.nil.repository.CabRepository;
import com.nil.validator.BookingValidator;

@Service
public class CabBookingServiceImpl implements CabBookingService{

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private CabRepository cabRepository;
	
	@Override
	public List<BookingDTO> getDetailsByBookingType(String bookingType) throws CabBookingException {
		List<Booking> bookings= bookingRepository.findByBookingType(bookingType);
		if(bookings.isEmpty()) {
			throw new CabBookingException("Service.NO_DETAILS_FOUND");
		}
		List<BookingDTO> bookingDTOs = new ArrayList<BookingDTO>();
		for(Booking booking : bookings) {
			BookingDTO bookingDTO = new BookingDTO();
			bookingDTO.setBookingId(booking.getBookingId());
			bookingDTO.setBookingType(booking.getBookingType());
			bookingDTO.setCustomerName(booking.getCustomerName());
			bookingDTO.setPhoneNo(booking.getPhoneNo());
			CabDTO cabDTO = new CabDTO();
			cabDTO.setCabNo(booking.getCab().getCabNo());
			cabDTO.setAvailability(booking.getCab().getAvailability());
			cabDTO.setDriverPhoneNo(booking.getCab().getDriverPhoneNo());
			cabDTO.setModelName(booking.getCab().getModelName());
			bookingDTO.setCabDTO(cabDTO);
			bookingDTOs.add(bookingDTO);
		}
		return bookingDTOs;
	}
	
	@Override
	public Integer bookCab(BookingDTO bookingDTO) throws CabBookingException {
		BookingValidator bookingValidator = new BookingValidator();
		bookingValidator.validate(bookingDTO);
		Optional<Cab> cabs= cabRepository.findByCabNo(bookingDTO.getCabDTO().getCabNo());
		Cab cab= cabs.orElseThrow(() -> new CabBookingException("Service.CAB_NOT_FOUND"));
		if(bookingDTO.getCabDTO().getAvailability().equals("No")) {
			throw new CabBookingException("Service.CAB_NOT_AVAILABLE");
		}
		Booking booking = new Booking();
		booking.setBookingType(bookingDTO.getBookingType());
		cab.setAvailability("No");
		booking.setCab(cab);
		booking.setCustomerName(bookingDTO.getCustomerName());
		booking.setPhoneNo(bookingDTO.getPhoneNo());
		bookingRepository.save(booking);
		
		return booking.getBookingId();
	}

}
