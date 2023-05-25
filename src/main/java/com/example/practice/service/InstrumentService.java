package com.example.practice.service;

import com.example.practice.model.Instrument;
import com.example.practice.repository.IInstrumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InstrumentService implements IInstrumentService {

    private final IInstrumentRepository instrumentRepository; //создание объекта такого класса (репозитория)
    //private List<Instrument> instruments;
    public InstrumentService(IInstrumentRepository instrumentRepository) {
        this.instrumentRepository=instrumentRepository; //вызывает методы репозитория
    }
    private Long id = 0L;

    @Override
    public List<Instrument> getAllInstrument() {
        return instrumentRepository.getAllInstrument();
    }


    public Instrument getInstrumentById(Long id) {
        return instrumentRepository.getInstrumentById(id);
    }

    @Override
    public void createInstrument(Instrument instrument) {
        instrument.setId(++id);
        instrumentRepository.createInstrument(instrument);
       // instruments = instrumentRepository.getAllInstrument();
    }

    @Override
    public void deleteInstrument(Long id) {
        instrumentRepository.deleteInstrument(id);
        //instruments = instrumentRepository.getAllInstrument();
    }

    @Override
    public void updateInstrument(Instrument instrument) {
        instrumentRepository.updateInstrument(instrument);
      //  instruments = instrumentRepository.getAllInstrument();
    }
}
