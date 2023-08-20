package com.example.leadsgen.controller;

import com.example.leadsgen.service.SalesFiguresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/profit/bad")
public class SalesFiguresController {
    @Autowired
    private SalesFiguresService salesFiguresService;

    @GetMapping("/weekly/{weekNumber}")
    public Double calculateProfitByWeek(@PathVariable int weekNumber) {
        return salesFiguresService.calculateProfitByWeek(weekNumber);
    }

    @GetMapping("/weekly")
    public Map<Integer, Double> calculateProfitAllWeeks() {
        return salesFiguresService.calculateProfitAllWeeks();
    }

    @GetMapping("/daily")
    public Map<Integer, Double> calculateProfitAllDays() {
        return salesFiguresService.calculateProfitAllDays();
    }
}
