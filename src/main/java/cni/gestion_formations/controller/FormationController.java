package cni.gestion_formations.controller;


import cni.gestion_formations.dto.FormationDto;
import cni.gestion_formations.dto.PredictData;
import cni.gestion_formations.entity.Formation;
import cni.gestion_formations.entity.PredictResponse;
import cni.gestion_formations.service.FormationService;

import org.antlr.v4.runtime.dfa.DFAState.PredPrediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

import static cni.gestion_formations.utils.Constants.CANDIDATURE_ENDPOINT;
import static cni.gestion_formations.utils.Constants.FORMATION_ENDPOINT;


@RequestMapping(FORMATION_ENDPOINT)
@RestController
public class FormationController {

    private final FormationService formationService;

    @Autowired
    public FormationController(FormationService formationService) {
        this.formationService = formationService;
    }

    @PostMapping
    public ResponseEntity<FormationDto> createFormation(@RequestBody FormationDto formationDto) {
        return formationService.saveFormation(formationDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formation> getFormation(@PathVariable Long id) {
        return formationService.getOne(id);
    }

    @GetMapping
    public ResponseEntity<List<FormationDto>> getAllFormations() {
        return formationService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable Long id) {
        return formationService.deleteFormation(id);
    }
    // prediction POST request which takes resume as string, list of criteras
    // returns a score from 0 to 100
    //TODO : IN FRONT NEED TO TRANSFORM THE PDF TO STRING / CRITERAS NEED TO BE AN ARRAY 
    @PostMapping("/predict")
    public ResponseEntity<PredictResponse> getPrediction(@RequestBody PredictData predictData) {
        return formationService.makePrediction(predictData);
    }


}
