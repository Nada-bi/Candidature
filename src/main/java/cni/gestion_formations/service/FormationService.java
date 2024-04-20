package cni.gestion_formations.service;

import cni.gestion_formations.dto.FormationDto;
import cni.gestion_formations.dto.PredictData;
import cni.gestion_formations.entity.Formation;
import cni.gestion_formations.entity.PredictResponse;

import org.antlr.v4.runtime.dfa.DFAState.PredPrediction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FormationService {

    ResponseEntity<FormationDto> saveFormation(FormationDto dto) ;

    ResponseEntity<Formation> getOne(Long id);

    ResponseEntity<List<FormationDto>> getAll();

    ResponseEntity<Void> deleteFormation(Long id);

    ResponseEntity<PredictResponse> makePrediction(PredictData predictData);

}
