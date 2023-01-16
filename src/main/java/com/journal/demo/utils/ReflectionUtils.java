package com.journal.demo.utils;

import static java.util.Optional.of;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.repository.JpaRepository;

@UtilityClass
public final class ReflectionUtils {

    public static Class<?> actualTypeArgument(JpaRepository<?, ?> repository) {
        return of(repository.getClass())
                .map(Class::getGenericInterfaces)
                .map(SubUtils::takeFirst)
                .map(SubUtils::castToClass)
                .map(Class::getGenericInterfaces)
                .map(SubUtils::takeFirst)
                .map(SubUtils::castToParameterizedType)
                .map(ParameterizedType::getActualTypeArguments)
                .map(SubUtils::takeFirst)
                .map(SubUtils::castToClass)
                .orElseThrow(AssertionError::new);
    }

    private static class SubUtils {
        static Type takeFirst(Type[] types) {
            return types[0];
        }

        static Class<?> castToClass(Type type) {
            if (type instanceof Class<?>) {
                return (Class<?>) type;
            }
            return ((Class<?>) ((TypeVariable<?>) type).getAnnotatedBounds()[0].getType());
        }

        static ParameterizedType castToParameterizedType(Type type) {
            return (ParameterizedType) type;
        }
    }
}
