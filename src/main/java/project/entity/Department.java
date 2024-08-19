package project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.enums.DepartmentType;

@Getter
@Setter
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DepartmentType type;

}