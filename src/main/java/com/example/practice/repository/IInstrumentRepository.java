package com.example.practice.repository;

import com.example.practice.model.Instrument;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IInstrumentRepository {
    List<Instrument> getAllInstrument();
    Instrument getInstrumentById(Long id);
    Instrument createInstrument(Instrument instrument); //первое-класс, второе-конкретный объект
    void deleteInstrument(Long id);
    Instrument updateInstrument(Instrument instrument);
}
