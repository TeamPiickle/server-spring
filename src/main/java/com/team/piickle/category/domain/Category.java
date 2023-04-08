package com.team.piickle.category.domain;

import com.team.piickle.common.domain.BaseEntity;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "categories")
public class Category extends BaseEntity {

    @Id private String id;
    private String title;
    private String content;
    private String imgurl;
    private List<String> cardIdList;
    private String unicode;
    private String gradation;
    private long order;
}
