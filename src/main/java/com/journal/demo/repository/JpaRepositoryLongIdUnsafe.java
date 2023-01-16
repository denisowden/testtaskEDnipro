package com.journal.demo.repository;

import static com.journal.demo.utils.ReflectionUtils.actualTypeArgument;

import com.journal.demo.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaRepositoryLongIdUnsafe <T> extends JpaRepository<T, Long> {

    default T findByIdUnsafe(Long id) {
        return findById(id)
                .orElseThrow(() -> new NotFoundException(actualTypeArgument(this)));
    }

}
