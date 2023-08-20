package com.example.leadsgen.repository;

import com.example.leadsgen.model.SalesFigures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesFiguresRepository extends JpaRepository<SalesFigures, Long> {

}
