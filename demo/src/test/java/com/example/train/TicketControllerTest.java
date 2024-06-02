import com.example.train.controller.TicketController;
import com.example.train.model.Ticket;
import com.example.train.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class TicketControllerTest {

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketController ticketController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPurchaseTicket() {
        Ticket ticket = new Ticket(1, "London", "France", "John", "Doe", "john@example.com", 20.0, "A12", "Section A");
        when(ticketService.purchaseTicket(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(ticket);

        ResponseEntity<?> responseEntity = ticketController.purchaseTicket("from", "to", "firstName", "lastName", "email", "seat", "section");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ticket, responseEntity.getBody());
    }

    @Test
    public void testGetReceipt() {
        Ticket ticket = new Ticket(1, "London", "France", "John", "Doe", "john@example.com", 20.0, "A12", "Section A");
        when(ticketService.getReceipt(anyInt())).thenReturn(Optional.of(ticket));

        ResponseEntity<?> responseEntity = ticketController.getReceipt(123);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ticket, responseEntity.getBody());
    }

    @Test
    public void testGetUsersBySection() {
        List<Ticket> ticketList = new ArrayList<>();
        // Add some ticket objects to ticketList
        when(ticketService.getUsersBySection(anyString())).thenReturn(ticketList);

        ResponseEntity<?> responseEntity = ticketController.getUsersBySection("section");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ticketList, responseEntity.getBody());
    }

    @Test
    public void testRemoveUser() {
        ResponseEntity<?> responseEntity = ticketController.removeUser(123);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testModifySeat() {
        ResponseEntity<?> responseEntity = ticketController.modifySeat(123, "newSeat", "newSection");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
