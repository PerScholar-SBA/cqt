package com.cqt.service;

import com.cqt.models.Employee;
import com.cqt.models.Product;
import com.cqt.models.Query;
import com.cqt.repository.ProductRepository;
import com.cqt.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {
    @Autowired
    private QueryRepository queryRepository;

    public List<Query> getAllQueries() {
        return queryRepository.findAll();
    }

    public Query getQueryById(Long id) {return queryRepository.findById(id).orElse(null); }

    public void saveQuery(Query query) {
         queryRepository.save(query);
    }

    public void deleteQuery(Long id) {
        queryRepository.deleteById(id);
    }
}
