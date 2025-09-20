package com.aurionpro.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aurionpro.app.dto.ProfileCreateRequest;
import com.aurionpro.app.dto.ProfileUpdateRequest;
import com.aurionpro.app.dto.StudentCreateRequest;
import com.aurionpro.app.dto.StudentResponse;
import com.aurionpro.app.dto.StudentUpdateRequest;
import com.aurionpro.app.entity.Profile;
import com.aurionpro.app.entity.Student;
import com.aurionpro.app.exception.NotFoundException;
import com.aurionpro.app.mapper.StudentMapper;
import com.aurionpro.app.repo.StudentRepository;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	private final StudentRepository repo;

	public StudentServiceImpl(StudentRepository repo) {
		this.repo = repo;
	}

	@Override
	public StudentResponse create(StudentCreateRequest request) {
		Student saved = repo.save(StudentMapper.toNewEntity(request));
		return StudentMapper.toResponse(saved);
	}

	@Override
	@Transactional(readOnly = true)
	public StudentResponse get(Long id) {
		Student e = repo.findById(id).orElseThrow(() -> new NotFoundException("Student not found: " + id));
		return StudentMapper.toResponse(e);
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudentResponse> list() {
		return repo.findAll().stream().map(StudentMapper::toResponse).toList();
	}

	@Override
	public StudentResponse update(Long id, StudentUpdateRequest request) {
		Student e = repo.findById(id).orElseThrow(() -> new NotFoundException("Student not found: " + id));
		StudentMapper.applyUpdate(request, e);
		return StudentMapper.toResponse(repo.save(e));
	}

	@Override
	public void delete(Long id) {
		if (!repo.existsById(id))
			throw new NotFoundException("Student not found: " + id);
		repo.deleteById(id);
	}

	@Override
	public StudentResponse addProfile(Long studentId, ProfileCreateRequest dto) {
		Student s = repo.findById(studentId)
				.orElseThrow(() -> new NotFoundException("Student not found: " + studentId));
		if (s.getProfile() != null) {
			throw new IllegalStateException("Profile already exists for student " + studentId);
		}
		Profile p = new Profile();
		p.setEmail(dto.getEmail());
		p.setPhone(dto.getPhone());
		s.setProfile(p); // owning side sets FK
		return StudentMapper.toResponse(repo.save(s));
	}

	@Override
	public StudentResponse updateProfile(Long studentId, ProfileUpdateRequest dto) {
		Student s = repo.findById(studentId)
				.orElseThrow(() -> new NotFoundException("Student not found: " + studentId));
		Profile p = s.getProfile();
		if (p == null) {
			throw new NotFoundException("Profile not found for student " + studentId);
		}
		if (dto.getEmail() != null)
			p.setEmail(dto.getEmail());
		if (dto.getPhone() != null)
			p.setPhone(dto.getPhone());
		s.setProfile(p);
		return StudentMapper.toResponse(repo.save(s));
	}
}
