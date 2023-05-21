package com.example.practice.service;

import com.example.practice.model.Instrument;
import com.example.practice.repository.IInstrumentRepository;

import java.util.List;

public class InstrumentService implements IInstrumentService {
    private final IInstrumentRepository instrumentRepository; //создание объекта такого класса
    public InstrumentService(IInstrumentRepository iInstrumentRepository) {
        this.instrumentRepository=iInstrumentRepository; //вызывает методы репозитория
    }

    @Override
    public List<Instrument> getAllInstrument() {
        return instrumentRepository.getAllInstrument();
    }

    @Override
    public Instrument getInstrumentById(Long id) {
        return instrumentRepository.getInstrumentById(id);
    }

    @Override
    public Instrument createInstrument(Instrument instrument) {
        return instrumentRepository.createInstrument(instrument);
    }

    @Override
    public void deleteInstrument(Long id) {
        instrumentRepository.deleteInstrument(id);
    }

    @Override
    public Instrument updateInstrument(Instrument instrument) {
        return instrumentRepository.updateInstrument(instrument);
    }
}
