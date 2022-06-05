package com.stream.bot.objects.model;

import com.stream.bot.objects.dto.StatusDto;
import com.stream.bot.objects.stream.TwitchApiResponses.Stream;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class StatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    Long id;
    String title;
    String game;
    String streamer;
    Boolean status;

    String date;

    public StatusModel(StatusDto status) {
        this.id = status.getId();
        this.title = status.getTitle();
        this.game = status.getGame();
        this.streamer = status.getStreamer();
        this.status = status.getStatus();
        this.date = status.getDate();
    }

}
