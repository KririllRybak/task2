package by.epam.training.blog.dao.api;

import by.epam.training.blog.domain.Entity;

public interface CrudDao<Type extends Entity>{
    Integer create(Type entity) ;

    Type read(Integer id) ;

    Integer update(Type entity) ;

    Integer delete(Integer id);

}
