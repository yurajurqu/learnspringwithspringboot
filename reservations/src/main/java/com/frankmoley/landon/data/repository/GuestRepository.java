package com.frankmoley.landon.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.frankmoley.landon.data.entity.Guest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {

    Optional<Guest> findById(long id);

}