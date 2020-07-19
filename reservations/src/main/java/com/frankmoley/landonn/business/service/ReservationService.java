package com.frankmoley.landonn.business.service;

import com.frankmoley.landon.business.RoomReservation;
import com.frankmoley.landon.data.entity.Guest;
import com.frankmoley.landon.data.entity.Reservation;
import com.frankmoley.landon.data.entity.Room;
import com.frankmoley.landon.data.repository.GuestRepository;
import com.frankmoley.landon.data.repository.ReservationRepository;
import com.frankmoley.landon.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    private final DateFormat DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd");
    public Date createDateFromDateString(String dateString){
        Date date=null;
        if(null!=dateString){
            try {
                date=DATE_FORMAT.parse(dateString);
            } catch (ParseException e) {
                date=new Date();
            }
        } else {
                date=new Date();
        }
        return date;
    }

    public List<RoomReservation> getRoomReservationsForDate(String dateString){
        Date date = createDateFromDateString(dateString);
        Iterable<Room> rooms=this.roomRepository.findAll();
        Map<Long,RoomReservation> roomReservationMap= new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation= new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());
            roomReservationMap.put(room.getId(),roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));
        if(null!=reservations){
            reservations.forEach(reservation -> {
                Optional<Guest> guestResponse= this.guestRepository.findById(reservation.getGuestId());
                if(guestResponse.isPresent()){
                    Guest guest=guestResponse.get();
                    RoomReservation roomReservation=roomReservationMap.get(reservation.getRoomId());
                    roomReservation.setDate(date);
                    roomReservation.setFirstName(guest.getFirstName());
                    roomReservation.setLastName(guest.getLastName());
                    roomReservation.setGuestId(guest.getId());

                }
            });
        }
List<RoomReservation> roomReservations=new ArrayList<>();
        for (long roomId: roomReservationMap.keySet()){
            roomReservations.add(roomReservationMap.get(roomId));
        }
        return roomReservations;
    }
}

