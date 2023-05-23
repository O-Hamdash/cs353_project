package com.api.gobooking.booking;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BookingService {

    private BookingRepository bookingRepository;

    // Constructor

    @Transactional
    public boolean createBooking(Booking booking) {


        return bookingRepository.insert(booking);


    }

    @Transactional
    public boolean updateBooking(Booking booking) {

        return bookingRepository.update(booking);
    }

    @Transactional
    public boolean deleteBookingById(int id) {

        return bookingRepository.deleteById(id);
    }

    public Booking findBookingById(int id) {

        return bookingRepository.findById(id);
    }

    public List<Booking> findAllBookingByBookerId(int booker_id) {

        return bookingRepository.findAllByBookerId(booker_id);
    }

    public List<Booking> findAllBookingByPropertyId(int property_id) {

        return bookingRepository.findAllByPropertyId(property_id);
    }

    public List<Booking> findAllBookings() {

        return bookingRepository.findAll();
    }
}
