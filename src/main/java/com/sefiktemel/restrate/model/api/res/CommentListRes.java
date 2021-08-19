package com.sefiktemel.restrate.model.api.res;

import com.sefiktemel.restrate.model.entity.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommentListRes extends BaseRes{
    private List<Comment> comments;
}
