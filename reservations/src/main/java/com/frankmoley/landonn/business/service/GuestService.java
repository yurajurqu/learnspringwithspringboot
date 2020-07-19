package com.frankmoley.landonn.business.service;

import com.frankmoley.landon.business.GuestModel;
import com.frankmoley.landon.data.entity.Guest;
import com.frankmoley.landon.data.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public List<GuestModel>  getAllGuests(){
        List<GuestModel> guestModels= new ArrayList<>();
        Iterable<Guest> guests=this.guestRepository.findAll();
        guests.forEach(guest -> {
            GuestModel guestModel = new GuestModel();
            guestModel.setFirstName(guest.getFirstName());
            guestModel.setLastName(guest.getLastName());
            guestModel.setId(guest.getId());
            guestModels.add(guestModel);
        });
        return guestModels;
    }
    public Guest createGuest(Guest guest){
        return this.guestRepository.save(guest);


    }

}
