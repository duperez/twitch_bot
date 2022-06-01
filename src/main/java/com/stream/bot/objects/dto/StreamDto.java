package com.stream.bot.objects.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class StreamDto {
    Long id;
    String streamer;
    @OneToMany
    List<StatusDto> statusDtoList;
    String status = "ONLINE";

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void addNewStatus(StatusDto statusDto) {
        statusDtoList.add(statusDto);
        if (!statusDto.getStatus())
            status = "OFFLINE";
    }

}
