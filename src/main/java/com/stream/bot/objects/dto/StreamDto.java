package com.stream.bot.objects.dto;

import com.stream.bot.objects.model.StatusModel;
import com.stream.bot.objects.model.StreamModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StreamDto {

    Long id;

    String streamer;
    List<StatusDto> statusModelList = new ArrayList<>();
    String status = "ONLINE";


    public StreamDto(StreamModel streamModel) {
        this.id = streamModel.getId();
        this.streamer = streamModel.getStreamer();
        this.status = streamModel.getStatus();
        this.statusModelList = streamModel.getStatusModelList().stream().map(StatusDto::new).collect(Collectors.toList());
    }

    public void addNewStatus(StatusDto statusDto) {
        statusModelList.add(statusDto);
        if (!statusDto.getStatus())
            status = "OFFLINE";
    }

}
