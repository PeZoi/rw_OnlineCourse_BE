package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.contest.ContestRequest;
import com.example.backend_rw.entity.dto.contest.ContestResponse;
import com.example.backend_rw.entity.dto.record.RecordReturnInRank;
import com.example.backend_rw.service.ContestService;
import com.example.backend_rw.service.RecordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> listAll() {
        List<ContestResponse> listContest = contestService.listAll();
        if (listContest.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listContest);
    }

    @GetMapping("/ranking/contest/{id}")
    public ResponseEntity<?> rank(@PathVariable(value = "id") Integer contestId) {
        List<RecordReturnInRank> listRanks = recordService.ranking(contestId);
        if (listRanks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listRanks);
    }

    @GetMapping("/join/{contest_id}")
    public ResponseEntity<?> join(@PathVariable(value = "contest_id") Integer contestId) {
        return ResponseEntity.ok(contestService.joinTest(contestId));
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "keyword") String keyword) {
        List<ContestResponse> listContest = contestService.search(keyword);
        if (listContest.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listContest);
    }

    @PostMapping("/create")
    public ResponseEntity<ContestResponse> save(@RequestBody @Valid ContestRequest contestRequest) {
        return new ResponseEntity<>(contestService.save(contestRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContestResponse> update(@PathVariable(value = "id") Integer contestId, @RequestBody @Valid ContestRequest contestRequest) {
        return ResponseEntity.ok(contestService.update(contestId, contestRequest));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getInAdministration(@PathVariable(value = "id") Integer contestId){
        return ResponseEntity.ok(contestService.get(contestId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Integer contestId) {
        return ResponseEntity.ok(contestService.delete(contestId));
    }

    @PostMapping("/switch-enabled")
    public ResponseEntity<?> switchEnabledOfContest(@RequestParam(value = "id") Integer contestId,
                                                    @RequestParam(value = "enabled") boolean enabled){
        return ResponseEntity.ok(contestService.switchEnabled(contestId, enabled));
    }
}
