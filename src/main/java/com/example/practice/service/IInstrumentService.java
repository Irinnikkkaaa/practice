package com.example.practice.service;

import com.example.practice.model.Instrument;

import java.util.List;

public interface IInstrumentService {
    List<Instrument> getAllInstrument();
    Instrument getInstrumentById(Long id);
    void createInstrument(Instrument instrument); //первое-класс, второе-конкретный объект
    void deleteInstrument(Long id);
    void updateInstrument(Instrument instrument);
}
