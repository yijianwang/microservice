package com.sap.academy.sales.service.controller;

import com.sap.academy.sales.service.entities.SalesOrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrderHeader,String> {

}
