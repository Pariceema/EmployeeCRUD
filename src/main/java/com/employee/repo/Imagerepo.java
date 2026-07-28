package com.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.entity.Image;

public interface Imagerepo extends JpaRepository<Image, Long>{

}
