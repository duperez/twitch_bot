package com.stream.bot.repositories;

import com.stream.bot.objects.model.StreamModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreamRepository extends JpaRepository<StreamModel, Long> {

    @Query("SELECT s FROM StreamModel s where s.streamer = ?1 ORDER BY s.id DESC")
    List<StreamModel> getLastUpdate(String streamerName, Pageable pageable);

}
