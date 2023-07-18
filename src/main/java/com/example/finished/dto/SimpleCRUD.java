package com.example.finished.dto;

public interface SimpleCRUD<K, V> {
    ResponseDto<V> create(V dto);
    ResponseDto<V> get(K entityId);
    ResponseDto<V> update(V dto, K entityId);
    ResponseDto<V> delete(K entityId);
}
