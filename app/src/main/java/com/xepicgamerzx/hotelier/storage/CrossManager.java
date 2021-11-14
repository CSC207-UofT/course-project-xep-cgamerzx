package com.xepicgamerzx.hotelier.storage;

import com.xepicgamerzx.hotelier.objects.CrossRef;
import com.xepicgamerzx.hotelier.objects.NonUniqueEntity;
import com.xepicgamerzx.hotelier.objects.UniqueEntity;

import java.util.List;

/**
 * Manager for CrossReferences.
 *
 * @param <T> CrossRef type being managed.
 * @param <N> NonUniqueEntity type of CrossRef.
 * @param <U> UniqueEntity type of CrossRef.
 */
public interface CrossManager<T extends CrossRef, N extends NonUniqueEntity, U extends UniqueEntity> extends Manager <T, Void>{
    /**
     * Create and insert relationship between <N> and <U> into <T> database.
     *
     * @param nonUniqueEntity <N> being assigned to uniqueEntity.
     * @param uniqueEntity <U> being assigned to nonUniqueEntity.
     * @return <T> crossRef created.
     */
    T createRelationship(N nonUniqueEntity, U uniqueEntity);

    /**
     * Get all <N> associated with uniqueEntity.
     *
     * @param uniqueEntity <U> key to search with.
     * @return List<N> associated with UniqueEntity.
     */
    List<N> getRelated(U uniqueEntity);

    /**
     * Get all <U> associated with nonUniqueEntity.
     *
     * @param nonUniqueEntity <N> key to search with.
     * @return List<U> associated with nonUniqueEntity.
     */
    List<U> getRelated(N nonUniqueEntity);

    /**
     * Get all <T> cross references associated with uniqueEntity
     *
     * @param uniqueEntity <U> key to search with.
     * @return List<T> cross references associated with <U>
     */
    List<T> getRelatedCross(U uniqueEntity);

    /**
     * Get all <T> cross references associated with nonUniqueEntity
     *
     * @param nonUniqueEntity <N> key to search with.
     * @return List<T> cross references associated with <N>
     */
    List<T> getRelatedCross(N nonUniqueEntity);
}
