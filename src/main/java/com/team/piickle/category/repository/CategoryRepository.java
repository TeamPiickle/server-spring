package com.team.piickle.category.repository;

import com.team.piickle.category.domain.Category;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findAllByTitleIn(List<String> titles);

    Optional<Category> findById(String id);

    @Aggregation(pipeline = "{'$sort' : {'order' : 1}}")
    List<Category> findByOrderByOrder();
}
