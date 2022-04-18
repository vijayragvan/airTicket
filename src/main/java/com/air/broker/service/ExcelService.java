package com.air.broker.service;

import com.air.broker.helper.ExcelHelper;
import com.air.broker.model.Ticket;
import com.air.broker.repositary.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    TicketRepository repository;
    public void save(MultipartFile file) {
        try {
            List<Ticket> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public List<Ticket> getAllTutorials() {
        return repository.findAll();
    }
}
