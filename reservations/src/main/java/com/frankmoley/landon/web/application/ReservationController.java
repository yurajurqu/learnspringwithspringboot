package com.frankmoley.landon.web.application;

import com.frankmoley.landon.business.ReservationRequest;
import com.frankmoley.landon.business.RoomReservation;
import com.frankmoley.landon.business.ServiceResponse;
import com.frankmoley.landonn.business.service.GuestService;
import com.frankmoley.landonn.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private GuestService guestService;

    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(@RequestParam(value="date",required = false) String dateString, Model model){
        List<RoomReservation> roomReservationList= this.reservationService.getRoomReservationsForDate(dateString);
        model.addAttribute("roomReservations",roomReservationList);
        model.addAttribute("guests",this.guestService.getAllGuests());
        return "reservations";
    }

    @RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ServiceResponse makeReservation( @RequestBody ReservationRequest reservationRequest){
        ServiceResponse response= new ServiceResponse();
        response.setStatus(this.reservationService.makeReservation(reservationRequest));
        return response;

    }


}
