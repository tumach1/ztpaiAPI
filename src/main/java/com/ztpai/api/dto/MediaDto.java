package com.ztpai.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MediaDto {
    private Long id;
    private String filePath;
    private String fileType;
    private Long postId;
}
