package com.study.lab3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    private Long id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = true)
    private Category parent;

    @Column(nullable = false)
    private String name;

    public Category(Long id) {
        this.id = id;
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
