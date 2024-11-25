package com.nil.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nil.dto.BookingDTO;
import com.nil.exception.CabBookingException;
import com.nil.service.CabBookingServiceImpl;

@RestController
@RequestMapping(value = "/api")
@Validated
public class CabBookingAPI {

	@Autowired
	private CabBookingServiceImpl cabBookingServiceImpl;
	
	@Autowired
	private Environment environment;
	
	@GetMapping(value = "/cab/{bookingType}")
	public ResponseEntity<List<BookingDTO>> getDetailsByBookingType(@PathVariable String bookingType) throws CabBookingException{
		List<BookingDTO> bookingDTOs = cabBookingServiceImpl.getDetailsByBookingType(bookingType);
		return new ResponseEntity<List<BookingDTO>>(bookingDTOs,HttpStatus.OK);
	}
	
	@PostMapping(value = "/cab")
	public ResponseEntity<String> bookCab(@RequestBody BookingDTO bookingDTO) throws CabBookingException{
		Integer a= cabBookingServiceImpl.bookCab(bookingDTO);
		String message = environment.getProperty("API.BOOKING_SUCCESS")+":"+a;
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
}
