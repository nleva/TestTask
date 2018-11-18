package ru.sendto.crxmarkets.dto;


import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.sendto.dto.Dto;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonTypeName("fholes")
public class FilledHoles extends Dto {

    List<Integer> levels;
}
