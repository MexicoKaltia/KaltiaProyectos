package mx.uniprotec.application.service;

import java.util.List;

import mx.uniprotec.application.entity.Region;


public interface IAplicacionService {
	
	public Region findRegion(Long idRegion);
	public List<Region> findAllRegiones();

}
