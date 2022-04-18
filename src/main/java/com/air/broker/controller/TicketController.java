package com.air.broker.controller;

import com.air.broker.helper.ExcelHelper;
import com.air.broker.message.ResponseMessage;
import com.air.broker.model.Ticket;
import com.air.broker.service.ExcelService;
import com.air.broker.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    ExcelService fileService;
    @Autowired
    TicketService ticketService;

    @PostMapping("uploadExcel")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @PostMapping("/ticket")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        try {
            Ticket _ticket = ticketService.saveTicket(ticket);
                return new ResponseEntity<>(_ticket, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ticket/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") Long id, @RequestBody Ticket ticket) {
        try {
            Optional<Ticket> ticketData = ticketService.getTicket(id);
            if(ticketData.isPresent()){
                Ticket _ticket = ticketData.get();
                _ticket.setDestinationTerminal(ticket.getDestinationTerminal());
                _ticket.setArrivalTime(ticket.getArrivalTime());
                _ticket.setDestination(ticket.getDestination());
                _ticket.setDepartureTime(ticket.getDepartureTime());
                _ticket.setAirline(ticket.getAirline());
                _ticket.setSource(ticket.getSource());
                _ticket.setSourceTerminal(ticket.getSourceTerminal());
                _ticket.setFlightNumber(ticket.getSource());
                return new ResponseEntity<>(ticketService.saveTicket(_ticket), HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllTickets")
    public ResponseEntity<List<Ticket>> getAllTutorials() {
        try {
            List<Ticket> tutorials = fileService.getAllTutorials();
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<HttpStatus> deleteTicket(@PathVariable("id") Long id) {
        try {
            ticketService.deleteTicket(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable("id") Long id) {
        try {
            Optional<Ticket> ticket = ticketService.getTicket(id);
            if(ticket.isPresent())
                return new ResponseEntity<>(ticket.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
