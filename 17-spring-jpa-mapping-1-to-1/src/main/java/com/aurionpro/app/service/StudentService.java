package com.aurionpro.app.service;

import java.util.List;

import com.aurionpro.app.dto.ProfileCreateRequest;
import com.aurionpro.app.dto.ProfileUpdateRequest;
import com.aurionpro.app.dto.StudentCreateRequest;
import com.aurionpro.app.dto.StudentResponse;
import com.aurionpro.app.dto.StudentUpdateRequest;

public interface StudentService {
	StudentResponse create(StudentCreateRequest request);

	StudentResponse get(Long id);

	List<StudentResponse> list();

	StudentResponse update(Long id, StudentUpdateRequest request);

	void delete(Long id);

	StudentResponse addProfile(Long studentId, ProfileCreateRequest profile);

	StudentResponse updateProfile(Long studentId, ProfileUpdateRequest profile);
}
