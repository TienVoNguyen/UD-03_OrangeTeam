package com.orange.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "district")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class District extends BaseEntity{

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private City city;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "district")
    @JsonIgnore
    private Set<Village> villages;
}