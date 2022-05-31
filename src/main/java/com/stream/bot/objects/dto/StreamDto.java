package com.stream.bot.objects.dto;

import com.stream.bot.objects.stream.Stream;
import lombok.*;

import javax.persistence.*;

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
    Long StreamId;
    String title;
    String game;
    String streamer;
    Boolean status;
    String date;

    public StreamDto(Stream stream) {
        this.title = stream.getTitle();
        this.game = stream.getGameName();
        this.streamer = stream.getUserName();
        this.status = stream.getStatus();
    }

}
