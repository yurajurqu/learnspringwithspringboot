package com.frankmoley.landon.web.application;

import com.frankmoley.landon.business.RoomReservation;
import com.frankmoley.landonn.business.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {
    @MockBean
    private ReservationService reservationService;
    @Autowired
    private MockMvc mockMvc;
    private static final DateFormat DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd");
    @Test
    public void getReservations() throws Exception {
        String dateString = "2017-01-01";
        Date date= DATE_FORMAT.parse(dateString);
        List<RoomReservation> mockReservationList= new ArrayList<>();
        RoomReservation mockReservation= new RoomReservation();
        mockReservation.setLastName("Test");
        mockReservation.setFirstName("Junit");
        mockReservation.setDate(date);
        mockReservation.setGuestId(1);
        mockReservation.setRoomNumber("11");
        mockReservation.setRoomId(100);
        mockReservation.setRoomName("Junit room");
        mockReservationList.add(mockReservation);
        given(reservationService.getRoomReservationsForDate(dateString)).willReturn(mockReservationList);
        this.mockMvc.perform(get("/reservations?date="+dateString))
                .andExpect(status().isOk()).andExpect(content().string(containsString("Test, Junit")));
    }
}
