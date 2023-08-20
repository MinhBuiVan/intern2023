package com.example.leadsgen.service;

import com.example.leadsgen.model.SalesFigures;
import com.example.leadsgen.repository.SalesFiguresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SalesFiguresService {
    @Autowired
    private SalesFiguresRepository salesFiguresRepository;

    private double getRicePrice(int quantity) {
        double highQualityPrice = 20000;
        double lowQualityPrice = 2000;
        double lowQualityWeight = 0.3;

        double totalBadWeight = (quantity / 5) * lowQualityWeight;
        double totalPrice = totalBadWeight * highQualityPrice - totalBadWeight * lowQualityPrice;

        return totalPrice;
    }
    @Cacheable("week")
    public double calculateProfitByWeek(int weekNumber) {
        List<SalesFigures> salesFigures = salesFiguresRepository.findAll();
        double totalProfit = 0.0;

        for (SalesFigures data : salesFigures) {
            if (getWeekNumber(data.getDate()) == weekNumber) {
                totalProfit += getRicePrice(data.getQuantity());
            }
        }
        return totalProfit;
    }

    private int getWeekNumber(int dayNumber) {
        return (dayNumber - 1) / 7 + 1;
    }
    @Cacheable("allWeeks")
    public Map<Integer, Double> calculateProfitAllWeeks() {
        List<SalesFigures> salesFigures = salesFiguresRepository.findAll();
        Map<Integer, Double> profitByWeek = new HashMap<>();

        for (SalesFigures data : salesFigures) {
            int weekNumber = getWeekNumber(data.getDate());
            double profit = profitByWeek.getOrDefault(weekNumber, 0.0);
            profit += getRicePrice(data.getQuantity());
            profitByWeek.put(weekNumber, profit);
        }

        return profitByWeek;
    }
    @Cacheable("allDays")
    public Map<Integer, Double> calculateProfitAllDays() {
        List<SalesFigures> salesFigures = salesFiguresRepository.findAll();
        Map<Integer, Double> profitByDay = new HashMap<>();

        for (SalesFigures data : salesFigures) {
            int day = data.getDate();
            double profit = profitByDay.getOrDefault(day, 0.0);
            profit += getRicePrice(data.getQuantity());
            profitByDay.put(day, profit);
        }
        List<Map.Entry<Integer, Double>> sorted = new ArrayList<>(profitByDay.entrySet());
        sorted.sort(Map.Entry.<Integer, Double>comparingByValue().reversed());
        Map<Integer, Double> sortedProfitByDay = new LinkedHashMap<>();
        for (Map.Entry<Integer, Double> entry : sorted) {
            sortedProfitByDay.put(entry.getKey(), entry.getValue());
        }
        return sortedProfitByDay;
    }
}
