package com.sth.congestionservice.service;

import com.sth.congestionservice.model.dto.PopulationDTO;
import com.sth.congestionservice.model.entity.Population;
import com.sth.congestionservice.repository.PopulationRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PopulationService {
    private final PopulationRepositoy populationRepositoy;

    // 인구 조회
    public List<PopulationDTO> listPopulation() {
        List<Population> list = new ArrayList<>();
        List<PopulationDTO> resultList = new ArrayList<>();

        list = populationRepositoy.findAll();
        for(Population population : list) {
            resultList.add(population.toDto());
        }

        return resultList;
    }

    // 인구 추가
    public void addPopulation(PopulationDTO populationDTO) {
        populationRepositoy.save(populationDTO.toEntity());
    }

}
