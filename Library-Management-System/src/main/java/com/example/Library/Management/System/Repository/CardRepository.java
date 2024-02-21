package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Entities.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<LibraryCard,Integer> {
    // jpa helps us in interacting with database, and gives us some function

}
