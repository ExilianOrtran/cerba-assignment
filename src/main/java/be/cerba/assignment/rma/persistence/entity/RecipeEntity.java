package be.cerba.assignment.rma.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@Table(indexes = @Index(columnList = "name", name = "idx_name"))
public class RecipeEntity extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter
    private UUID id;

    @Setter
    private String name;
    @Setter
    private String description;
    @Setter
    private String cookingInstruction;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    @OrderBy("name ASC")
    private List<IngredientEntity> ingredientEntities;
}