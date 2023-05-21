package com.example.practice.service;

import com.example.practice.model.Instrument;

import java.util.List;

public interface IInstrumentService {
    List<Instrument> getAllInstrument();
    Instrument getInstrumentById(Long id);
    Instrument createInstrument(Instrument instrument); //первое-класс, второе-конкретный объект
    void deleteInstrument(Long id);
    Instrument updateInstrument(Instrument instrument);
}
