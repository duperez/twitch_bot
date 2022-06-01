package com.stream.bot.objects.dto;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class StreamDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    Long id;

    String streamer;
    @OneToMany(cascade=CascadeType.PERSIST)
    @JoinColumn(name="status_list")
    List<StatusDto> statusDtoList = new ArrayList<>();
    String status = "ONLINE";

    public void addNewStatus(StatusDto statusDto) {
        statusDtoList.add(statusDto);
        if (!statusDto.getStatus())
            status = "OFFLINE";
    }
}
