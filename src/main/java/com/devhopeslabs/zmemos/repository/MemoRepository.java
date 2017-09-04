package com.devhopeslabs.zmemos.repository;

import com.devhopeslabs.zmemos.models.Memo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemoRepository extends CrudRepository<Memo,String> {
    List<Memo> findByUserId(String user_id);
    Memo findById(String id);
    Memo findByName(String name);
    Memo findBycreatedDate(String createdDate);
    Memo findByLocation(String location);
    Memo findByStatus(String status);
}
