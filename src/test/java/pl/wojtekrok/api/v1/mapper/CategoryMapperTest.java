package pl.wojtekrok.api.v1.mapper;

import org.junit.jupiter.api.Test;
import pl.wojtekrok.api.v1.model.CategoryDTO;
import pl.wojtekrok.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTO() {

        //given
        Category category = new Category();
        category.setName("Joe");
        category.setId(1L);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(1L, categoryDTO.getId());
        assertEquals("Joe", categoryDTO.getName());
    }
}