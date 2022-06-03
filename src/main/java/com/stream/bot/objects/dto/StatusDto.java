package com.stream.bot.objects.dto;

import com.stream.bot.objects.stream.TwitchApiResponses.Stream;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class StatusDto {
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

    public StatusDto(Stream stream) {
        this.title = stream.getTitle();
        this.game = stream.getGameName();
        this.streamer = stream.getUserName();
        this.status = stream.getStatus();
    }

    public Boolean equals(StatusDto statusDto) {
        this.title = this.title == null ? "" : this.title;
        this.game = this.game == null ? "" : this.game;
        return this.title.equals(statusDto.getTitle()) &&
                this.game.equals(statusDto.getGame()) &&
        this.status == statusDto.getStatus();
    }

}
