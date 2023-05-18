package com.sportingevents.field;

import com.sportingevents.common.constant.ApiEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.FIELDS)
public class FieldController {
    @Autowired
    private FieldService fieldService;

    @GetMapping
    public ResponseEntity<List<FieldEntity>> getAllFields(Pageable pageable) {
        return ResponseEntity.ok(fieldService.getAllFields(pageable));
    }

    @GetMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<FieldEntity> getField(@PathVariable("id") Integer fieldId) {
        return ResponseEntity.ok(fieldService.getField(fieldId));
    }

    @PostMapping
    public ResponseEntity<String> saveField(@Validated @RequestBody FieldRequestModel fieldRequestModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fieldService.saveField(fieldRequestModel));
    }

    @PutMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<String> updateField(@PathVariable("id") Integer fieldId, @Validated @RequestBody FieldRequestModel fieldRequestModel) {
        return ResponseEntity.ok(fieldService.updateField(fieldId, fieldRequestModel));
    }

    @DeleteMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<String> deleteField(@PathVariable("id") Integer fieldId) {
        return ResponseEntity.ok(fieldService.deleteField(fieldId));
    }
}
