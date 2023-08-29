package com.tcc.moradiaestudantil.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.moradiaestudantil.domain.entity.Denuncia;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Long>{

}
