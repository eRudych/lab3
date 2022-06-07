package com.study.lab3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @Column
    private String description;

    @Column
    private Integer price;

    @Column
    private Integer bnPrice;

    @Column
    private String url;

    @Column
    private String image;

    @Column
    private String vendor;
}
