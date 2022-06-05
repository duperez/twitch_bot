package com.stream.bot.objects.model;

import com.stream.bot.objects.dto.StatusDto;
import com.stream.bot.objects.dto.StreamDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class StreamModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    Long id;

    String streamer;
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    List<StatusModel> statusModelList = new ArrayList<>();
    String status;
    public StreamModel(StreamDto streamDto) {
        this.id = streamDto.getId();
        this.streamer = streamDto.getStreamer();
        this.status = streamDto.getStatus();
        this.statusModelList = streamDto.getStatusModelList().stream().map(StatusModel::new).collect(Collectors.toList());
    }
}
