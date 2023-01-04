package com.team.piickle.card.domain;

import com.team.piickle.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "FILTER_ID"))
@Entity
public class Filter extends BaseEntity {

    @Column(name = "CONTENT")
    private String content;
}
