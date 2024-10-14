package uz.urinov.base.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomPage<T> {
    List<T> content = new ArrayList<>();
    Boolean isFirst;
    Boolean isLast;
    Integer pageNumber;
    Integer totalPages;
    Long totalElements;
}
