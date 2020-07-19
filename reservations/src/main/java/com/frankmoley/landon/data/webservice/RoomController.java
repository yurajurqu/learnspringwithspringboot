package com.frankmoley.landon.data.webservice;

import com.frankmoley.landon.business.RoomReservation;
import com.frankmoley.landon.data.entity.Room;
import com.frankmoley.landon.data.repository.RoomRepository;
import com.frankmoley.landonn.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//@RestController
public class RoomController  {
/*
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomRepository repository;

    @RequestMapping(value = "/rooms",method= RequestMethod.GET)
    List<Room> findAll(@RequestParam(required = false) String roomNumber){
        List<Room> rooms=new ArrayList<>();
        System.out.println("Start");
        if(roomNumber==null){
            Iterable<Room> results=this.repository.findAll();
            results.forEach(room -> {System.out.println("adding");rooms.add(room);});
            System.out.println("added");
        }
        else {
            Room room=this.repository.findByNumber(roomNumber);
            if(room!=null){
                rooms.add(room);
            }
            System.out.println("ByNumber");
        }
        return rooms;
    }
    @RequestMapping(value = "/reservationsTest",method= RequestMethod.GET)
    List<RoomReservation> findReservations() throws ParseException {
        List<RoomReservation> roomsReservations=new ArrayList<>();
        roomsReservations=reservationService.getRoomReservationsForDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01"));
        return roomsReservations;
    }
    */

}
