package mx.uniprotec.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.uniprotec.application.entity.Region;

public interface IRegionDao  extends JpaRepository<Region, Long>{

}
