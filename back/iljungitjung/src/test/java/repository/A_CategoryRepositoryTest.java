package repository;

import com.iljungitjung.domain.category.entity.Category;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class A_CategoryRepositoryTest extends AbstractRepositoryTest {


    @Test
    @Order(1)
    public void 카테고리_등록_레포지토리() throws Exception {

        //given
        Category category = new Category("커트", "#000000", "0130");

        //when
        Category savedCategory = categoryRepository.save(category);

        //then
        Assertions.assertEquals(category.getCategoryName(), savedCategory.getCategoryName());
        Assertions.assertEquals(category.getTime(), savedCategory.getTime());
        Assertions.assertEquals(category.getColor(), savedCategory.getColor());
    }
    @Test
    @Order(2)
    public void 카테고리_전체조회_레포지토리() throws Exception {

        //given

        //when
        List<Category> categoryList  = categoryRepository.findAll();

        //then
        Assertions.assertEquals(1, categoryList.size());
    }

    @Test
    @Order(3)
    public void 카테고리_상세조회_레포지토리() throws Exception {

        //given

        //when
        Optional<Category> categoryResult = categoryRepository.findById(1L);

        //then
        Assertions.assertEquals("커트", categoryResult.get().getCategoryName());
    }

    @Test
    @Order(4)
    public void 카테고리_이름조회_레포지토리() throws Exception {

        //given

        //when
        Optional<Category> categoryResult = categoryRepository.findByCategoryName("커트");

        //then
        Assertions.assertEquals("0130", categoryResult.get().getTime());
    }
}
