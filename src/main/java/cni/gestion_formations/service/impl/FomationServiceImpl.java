package cni.gestion_formations.service.impl;

import cni.gestion_formations.dto.FormationDto;
import cni.gestion_formations.dto.PredictData;
import cni.gestion_formations.entity.Formation;
import cni.gestion_formations.entity.PredictResponse;
import cni.gestion_formations.repository.FormationRepository;
import cni.gestion_formations.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FomationServiceImpl implements FormationService {
    private final FormationRepository formationRepository;

    @Autowired
    public FomationServiceImpl(FormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }

    @Override
    public ResponseEntity<FormationDto> saveFormation(FormationDto dto) {

        FormationDto savedFormation = FormationRepository.save(dto);
        return ResponseEntity.ok(savedFormation);
    }

    @Override
    public ResponseEntity<Formation> getOne(Long id) {

        Optional<Formation> optionalFormation = formationRepository.findById(id);
        return optionalFormation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<FormationDto>> getAll() {
        List<Formation> formations = formationRepository.findAll();
        List<FormationDto> formationDtos = formations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(formationDtos);
    }

    @Override
    public ResponseEntity<Void> deleteFormation(Long id) {
        if (formationRepository.existsById(id)) {
            formationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override 
    // this function is used to take (resume, criteras) -> score from 0 to 100
    public ResponseEntity<PredictResponse> makePrediction(PredictData predictData) {
        String[] resume = predictData.getSplittedResume();
        String[] criteras = predictData.getCriteras();
        Map<String, Integer> occCriteras = new HashMap<>();

        // loop over criteras and compute occurence in the resume
        for (String critera : criteras) {
            int occ = 0;
            for (String word : resume) {
                if (critera == word) {
                    occ+=1;
                }
            }
            occCriteras.put(critera, occ);
        }
        // calculate totalScore which is sum(occurence)
        Integer totalScore = 0;
        for(Integer score : occCriteras.values()) {
            totalScore += score;
        }
        PredictResponse predictResponse = new PredictResponse();

        // if score is more than 100 set it to 100
        if (totalScore > 100) {
            totalScore = 100;
        }
        predictResponse.setScore(totalScore);
        // return response which contains the score from 0 to 100
        return ResponseEntity.ok(predictResponse);
    }


    // Utility method to convert FormationEntity to FormationDto
    private FormationDto convertToDto(Formation formationEntity) {

        FormationDto formationDto = new FormationDto();
        formationDto.setId(formationEntity.getId());
        formationDto.setTitle(formationEntity.getTitle());
        formationDto.setDescription(formationEntity.getDescription());
        formationDto.setDeadline(formationEntity.getDeadline());


        return formationDto;
    }
}
