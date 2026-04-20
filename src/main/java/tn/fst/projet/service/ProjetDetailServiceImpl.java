package tn.fst.projet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.fst.projet.entity.ProjetDetail;
import tn.fst.projet.repository.ProjetDetailRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetDetailServiceImpl implements ProjetDetailService {

    @Autowired
    private ProjetDetailRepository projetDetailRepository;

    @Override
    public List<ProjetDetail> getAll() {
        return projetDetailRepository.findAll();
    }

    @Override
    public Optional<ProjetDetail> getById(Long id) {
        return projetDetailRepository.findById(id);
    }

    @Override
    public ProjetDetail create(ProjetDetail projetDetail) {
        return projetDetailRepository.save(projetDetail);
    }

    @Override
    public ProjetDetail update(Long id, ProjetDetail projetDetail) {
        if (!projetDetailRepository.existsById(id)) {
            throw new RuntimeException("ProjetDetail not found with id: " + id);
        }
        projetDetail.setId(id);
        return projetDetailRepository.save(projetDetail);
    }

    @Override
    public void delete(Long id) {
        if (!projetDetailRepository.existsById(id)) {
            throw new RuntimeException("ProjetDetail not found with id: " + id);
        }
        projetDetailRepository.deleteById(id);
    }

    @Override
    public List<ProjetDetail> getByTechnologieLike(String technologie) {
        return projetDetailRepository.findByTechnologieLike(technologie);
    }

    @Override
    public List<ProjetDetail> getByTechnologieContains(String technologie) {
        return projetDetailRepository.findByTechnologieContains(technologie);
    }

    @Override
    public List<ProjetDetail> getByTechnologieContaining(String technologie) {
        return projetDetailRepository.findByTechnologieContaining(technologie);
    }
}