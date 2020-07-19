package com.frankmoley.landon.web.application;

import com.frankmoley.landon.data.entity.Guest;
import com.frankmoley.landonn.business.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping( value = "/guests")
public class GuestController {

    @Autowired
    GuestService guestService;

    @RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Guest createUser(@RequestBody Guest guest){
        return this.guestService.createGuest(guest);
    }
}
