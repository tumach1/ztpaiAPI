package com.ztpai.api.Mappers;

import com.ztpai.api.dao.MediaDao;
import com.ztpai.api.dto.MediaDto;

import java.util.List;

public class MediaMapper {
    public static MediaDto toDto(MediaDao media) {
        if (media == null) {
            return null;
        }
        MediaDto mediaDto = new MediaDto();
        mediaDto.setId(media.getId());
        mediaDto.setFilePath(media.getFilePath());
        mediaDto.setFileType(media.getFileType());
        mediaDto.setPostId(media.getPost().getId());
        return mediaDto;
    }

    public static List<MediaDto> toDto(List<MediaDao> media) {
        if (media == null) {
            return null;
        }
        return media.stream().map(MediaMapper::toDto).toList();
    }

    public static MediaDao toDao(MediaDto dto) {
        if (dto == null) {
            return null;
        }
        MediaDao media = new MediaDao();
        media.setId(dto.getId());
        media.setFilePath(dto.getFilePath());
        media.setFileType(dto.getFileType());
        return media;
    }
}
