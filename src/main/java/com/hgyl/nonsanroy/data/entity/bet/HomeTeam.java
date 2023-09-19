package com.hgyl.nonsanroy.data.entity.bet;

import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "homeTeam")
public class HomeTeam extends BaseEntity{
}
