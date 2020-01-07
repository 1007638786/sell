package com.imooc.sell.repository;

        import com.imooc.sell.dateobject.ProductCategory;
        import org.springframework.data.annotation.Transient;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    @Transient
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
