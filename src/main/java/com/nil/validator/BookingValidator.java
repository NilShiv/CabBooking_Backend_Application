package com.nil.validator;

import com.nil.dto.BookingDTO;
import com.nil.exception.CabBookingException;

public class BookingValidator {

	public void validate(BookingDTO bookingDTO) throws CabBookingException{
		if(Boolean.FALSE.equals(validatePhoneNo(bookingDTO.getPhoneNo()))) throw new CabBookingException("Validator.INVALID_PHONENO");
	}
	
	public Boolean validatePhoneNo(Long phoneNo) {
		return phoneNo.toString().matches("[6-9]{1}[0-9]{9}");
	}
}
