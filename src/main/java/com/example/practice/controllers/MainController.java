package com.example.practice.controllers;

import com.example.practice.model.Instrument;
import com.example.practice.service.IInstrumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.List;

@Controller
public class MainController {
    private final IInstrumentService instrumentService;

    public MainController(IInstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping("/")
        public String home(Model model) {
        List<Instrument> instruments = instrumentService.getAllInstrument();
        model.addAttribute("title", "Главная страница");
        model.addAttribute("instruments", instruments);
        return "home";
    }
    @PostMapping("/home/delete/{id}")
    public String instrumentDelete(@PathVariable Long id) {
        instrumentService.deleteInstrument(id);
        return "redirect:/";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Cтраница про нас");
        return "about";
    }

    @GetMapping("/instrument")
    public String instrumentMain(){
        return "instrument-main";
    }
    @PostMapping("/instrument/add")
    public  String instrumentAdd(@RequestParam String title,
                                 @RequestParam String unit_of_measurement,
                                 @RequestParam int division_price,
                                 @RequestParam double sensitivity,
                                 @RequestParam int maximum_value,
                                 @RequestParam int number_of_box){
        Long id = 0L;
        Instrument instrument = new Instrument(id, title, unit_of_measurement, division_price, sensitivity, maximum_value, number_of_box);
        instrumentService.createInstrument(instrument);
        return "redirect:/";
    }
    @GetMapping("/instrument/{id}")
    public String instrument(@PathVariable Long id, Model model){
        Instrument instrument = instrumentService.getInstrumentById(id);
        model.addAttribute("instrument", instrument);
        return "instrument-update";
    }

    @PostMapping ("/instrument/{id}/update")
    public String instrumentUpdate(@PathVariable Long id,
                                   @RequestParam int number_of_box){
        Instrument instrument = instrumentService.getInstrumentById(id);
        instrument.setNumber_of_box(number_of_box);
        instrumentService.updateInstrument(instrument);
        return "redirect:/";
    }
}
