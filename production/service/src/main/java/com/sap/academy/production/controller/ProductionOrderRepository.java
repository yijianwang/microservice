package com.sap.academy.production.controller;

import com.sap.academy.production.entities.ProductionOrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductionOrderRepository extends JpaRepository<ProductionOrderHeader,String> {

}
