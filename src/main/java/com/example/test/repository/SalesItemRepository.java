package com.example.test.repository;

import com.example.test.entity.SalesItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesItemRepository extends JpaRepository<SalesItemEntity, Long> {
List<SalesItemEntity> findTop10ByIdDEsc();

}
