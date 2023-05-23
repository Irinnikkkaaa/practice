package com.example.practice.repository;

import com.example.practice.model.Instrument;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
@Repository
public interface IInstrumentRepository {
    List<Instrument> getAllInstrument();
    Instrument getInstrumentById(Long id);
    void createInstrument(Instrument instrument); //первое-класс, второе-конкретный объект
    void deleteInstrument(Long id);
    void updateInstrument(Instrument instrument) ;
}
