package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.record.RecordRequest;
import com.example.backend_rw.entity.dto.record.RecordResponse;
import com.example.backend_rw.service.RecordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/record")
public class RecordController {
    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid RecordRequest recordRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(recordService.saveRecord(recordRequest));
    }

    @GetMapping("/list-all/user")
    public ResponseEntity<?> listAllByUser(@RequestParam(value = "id") Integer userId) {
        List<RecordResponse> listRecords = recordService.listAllRecord(userId);
        if (listRecords.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listRecords);
    }

    @GetMapping("/list-all/user-contest")
    public ResponseEntity<?> listAllByUserAndContest(@RequestParam(value = "user") Integer userId,
                                                     @RequestParam(value = "contest") Integer contestId){
        List<RecordResponse> listRecords = recordService.listAllRecordByUserAndContest(userId, contestId);
        if(listRecords.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listRecords);
    }
}
