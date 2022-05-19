package com.hingehealth.demo.repositories;

import com.hingehealth.demo.models.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeRepository extends JpaRepository<Node, Integer> {

     List<Node> findByParentId(Integer parentId);
}
