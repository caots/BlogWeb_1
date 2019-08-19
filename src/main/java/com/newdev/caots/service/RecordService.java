package com.newdev.caots.service;

import com.newdev.caots.entities.Record;

import java.util.List;

public interface RecordService {

     List<Record> findAllRecord();

     Record findByName(String name);

     boolean saveRecord(Record record);
}
