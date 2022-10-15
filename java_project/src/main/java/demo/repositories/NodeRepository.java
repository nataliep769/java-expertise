package demo.repositories;

import demo.models.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<Node, Integer> {
}
