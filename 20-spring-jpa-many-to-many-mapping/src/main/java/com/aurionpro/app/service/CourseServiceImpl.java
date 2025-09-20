package com.aurionpro.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aurionpro.app.dto.CourseCreateRequest;
import com.aurionpro.app.dto.CourseResponse;
import com.aurionpro.app.dto.CourseUpdateRequest;
import com.aurionpro.app.entity.Course;
import com.aurionpro.app.exception.NotFoundException;
import com.aurionpro.app.repository.CourseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
//generates a private static final org.slf4j.Logger log = LoggerFactory.getLogger(CourseServiceImpl.class); under the hood.
public class CourseServiceImpl implements CourseService {
	private final CourseRepository courseRepo;
	private final ModelMapper mm;

	@Override
	@Transactional
	public CourseResponse create(CourseCreateRequest req) {
		Course c = Course.builder().code(req.getCode()).title(req.getTitle()).build();
		c = courseRepo.save(c);
		log.info("Created course id={}", c.getId());
		return mm.map(c, CourseResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public CourseResponse get(Long id) {
		Course c = courseRepo.findById(id).orElseThrow(() -> new NotFoundException("Course not found: " + id));
		return mm.map(c, CourseResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<CourseResponse> list(int page, int size, String sortBy, String direction) {
		Sort sort = "desc".equalsIgnoreCase(direction) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		return courseRepo.findAll(pageable).map(c -> mm.map(c, CourseResponse.class));
	}

	@Override
	@Transactional
	public CourseResponse update(Long id, CourseUpdateRequest req) {
		Course c = courseRepo.findById(id).orElseThrow(() -> new NotFoundException("Course not found: " + id));

		// only non-null fields from req will overwrite the entity.
		mm.map(req, c);

		log.info("Updated (patched) course id={} code={} title={}", c.getId(), c.getCode(), c.getTitle());
		return mm.map(c, CourseResponse.class);
	}
}
