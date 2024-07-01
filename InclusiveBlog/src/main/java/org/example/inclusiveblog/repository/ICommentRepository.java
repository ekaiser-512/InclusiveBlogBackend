package org.example.inclusiveblog.repository;

import org.example.inclusiveblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Integer> {
}
