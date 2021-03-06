package com.codegym.demo.service.impl;

import com.codegym.demo.model.AttachService;
import com.codegym.demo.repository.AttachServiceRepository;
import com.codegym.demo.service.AttachServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachServiceServiceImpl implements AttachServiceService {
    @Autowired
    private AttachServiceRepository attachServiceRepository;

    @Override
    public List<AttachService> findAll() {
        return attachServiceRepository.findAll();
    }

    @Override
    public AttachService findById(Long id) {
        return attachServiceRepository.findById(id).orElse(null);
    }

    @Override
    public void save(AttachService attachService) {
        attachServiceRepository.save(attachService);
    }

    @Override
    public void delete(Long id) {
        attachServiceRepository.deleteById(id);
    }
}
