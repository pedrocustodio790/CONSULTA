package com.example.consultorio.Repository;

import com.example.consultorio.Entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    boolean existsByCrm(String crm);
}
