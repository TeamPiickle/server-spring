package com.team.piickle.card.tag;

import com.team.piickle.common.domain.BaseEntity;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "TAG_ID"))
@Entity
public class Tag extends BaseEntity {

    @Column
    private String content;
}
