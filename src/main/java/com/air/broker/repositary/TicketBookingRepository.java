package com.air.broker.repositary;

import com.air.broker.model.TicketBookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketBookingRepository extends JpaRepository<TicketBookingRequest, Long> {
}
