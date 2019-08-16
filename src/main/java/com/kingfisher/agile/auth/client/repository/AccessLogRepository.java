package com.kingfisher.agile.auth.client.repository;

import org.springframework.stereotype.Repository;

import com.kingfisher.agile.auth.client.model.AccessLog;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, String> {

}
