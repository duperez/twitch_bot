package com.stream.bot.objects.stream.TwitchApiResponses;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect
public class Stream {
    @JsonProperty("id")
    String id;
    @JsonProperty("user_id")
    String userId;
    @JsonProperty("user_login")
    String userLogin;
    @JsonProperty("user_name")
    String userName;
    @JsonProperty("game_id")
    String game_id;
    @JsonProperty("game_name")
    String gameName;
    @JsonProperty("type")
    String type;
    @JsonProperty("title")
    String title;
    @JsonProperty("viewer_count")
    String viewerCount;
    @JsonProperty("started_at")
    String startedAt;
    @JsonProperty("language")
    String language;
    @JsonProperty("thumbnail_url")
    String thumbnailUrl;
    @JsonProperty("tag_ids")
    List<String> tagIds;
    @JsonProperty("is_mature")
    String isMature;
    Boolean status;

    public Stream(String game, String title, Boolean status) {
        this.gameName = game;
        this.title = title;
        this.status = status;
    }
}
