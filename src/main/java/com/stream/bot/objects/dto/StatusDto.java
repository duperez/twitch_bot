package com.stream.bot.objects.dto;


import com.stream.bot.objects.model.StatusModel;
import com.stream.bot.objects.stream.TwitchApiResponses.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto {

    Long id;
    String title;
    String game;
    String streamer;
    Boolean status;
    String date;

    public StatusDto(StatusModel statusModel) {
        this.id = statusModel.getId();
        this.title = statusModel.getTitle();
        this.game = statusModel.getGame();
        this.streamer = statusModel.getStreamer();
        this.status = statusModel.getStatus();
        this.date = statusModel.getDate();
    }

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
