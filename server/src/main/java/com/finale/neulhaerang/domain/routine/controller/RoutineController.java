package com.finale.neulhaerang.domain.routine.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finale.neulhaerang.domain.routine.dto.request.RoutineCreateReqDto;
import com.finale.neulhaerang.domain.routine.service.RoutineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/routine")
public class RoutineController {
	private final RoutineService routineService;

	@PostMapping
	public ResponseEntity<Void> createRoutine(@RequestBody @Valid RoutineCreateReqDto routineCreateReqDto) {
		routineService.createRoutine(routineCreateReqDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping
	public ResponseEntity<String> findRoutineByMemberAndDate(@RequestParam LocalDate date) {
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}
