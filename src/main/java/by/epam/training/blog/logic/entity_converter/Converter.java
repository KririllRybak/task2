package by.epam.training.blog.logic.entity_converter;

public interface Converter<DbEntity, ApplicationEntity> {
    public ApplicationEntity convertDbEntityToApplicationEntity(DbEntity dbEntity);
}
