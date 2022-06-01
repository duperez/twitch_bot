package com.stream.bot.repositories;

import com.stream.bot.objects.dto.StatusDto;
import com.stream.bot.objects.dto.StreamDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreamRepository extends JpaRepository<StreamDto, Long> {

    @Query("SELECT s FROM StreamDto s where s.streamer = ?1 ORDER BY s.id DESC")
    List<StreamDto> getLastUpdate(String streamerName, Pageable pageable);

}
