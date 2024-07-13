package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.contest.ContestResponse;
import com.example.backend_rw.entity.dto.record.RecordReturnInRank;
import com.example.backend_rw.service.ContestService;
import com.example.backend_rw.service.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contest")
public class ContestController {
    private final ContestService contestService;
    private final RecordService recordService;

    public ContestController(ContestService contestService, RecordService recordService) {
        this.contestService = contestService;
        this.recordService = recordService;
    }

    //api này dùng cho admin vs học viên.
    @GetMapping("/list-all")
    public ResponseEntity<?> listAll(){
        List<ContestResponse> listContest = contestService.listAll();
        if(listContest.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listContest);
    }

    @GetMapping("/ranking/contest/{id}")
    public ResponseEntity<?> rank(@PathVariable(value = "id") Integer contestId){
        List<RecordReturnInRank> listRanks = recordService.ranking(contestId);
        if(listRanks.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listRanks);
    }

    @GetMapping("/join/{contest_id}")
    public ResponseEntity<?> join(@PathVariable(value = "contest_id") Integer contestId){
        return ResponseEntity.ok(contestService.joinTest(contestId));
    }
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "keyword") String keyword){
        List<ContestResponse> listContest = contestService.search(keyword);
        if(listContest.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listContest);
    }
}
