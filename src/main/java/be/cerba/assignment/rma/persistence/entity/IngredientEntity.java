package be.cerba.assignment.rma.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Entity
@Getter
@Accessors(chain = true)
@NoArgsConstructor
public class IngredientEntity extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter
    private UUID id;

    @Setter
    private String name;
    @Setter
    private String quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    @Setter
    private RecipeEntity recipe;
}