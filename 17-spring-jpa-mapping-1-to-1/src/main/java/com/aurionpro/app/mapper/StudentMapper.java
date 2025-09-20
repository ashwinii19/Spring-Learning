package com.aurionpro.app.mapper;

import com.aurionpro.app.dto.ProfileCreateRequest;
import com.aurionpro.app.dto.ProfileUpdateRequest;
import com.aurionpro.app.dto.StudentCreateRequest;
import com.aurionpro.app.dto.StudentResponse;
import com.aurionpro.app.dto.StudentUpdateRequest;
import com.aurionpro.app.entity.Profile;
import com.aurionpro.app.entity.Student;

public final class StudentMapper {
	private StudentMapper() {
	}

// Entity → Response
	public static StudentResponse toResponse(Student e) {
		if (e == null)
			return null;
		StudentResponse r = new StudentResponse();
		r.setId(e.getId());
		r.setName(e.getName());
		r.setDob(e.getDob());
		r.setProfile(toDto(e.getProfile()));
		return r;
	}

	private static ProfileCreateRequest toDto(Profile p) {
		if (p == null)
			return null;
		ProfileCreateRequest d = new ProfileCreateRequest();
		d.setId(p.getId());
		d.setEmail(p.getEmail());
		d.setPhone(p.getPhone());
		return d;
	}

// Create → Entity
	public static Student toNewEntity(StudentCreateRequest req) {
		Student s = new Student();
		s.setName(req.getName());
		s.setDob(req.getDob());
		if (req.getProfile() != null) {
			s.setProfile(toNewProfile(req.getProfile())); // ProfileCreateRequest
		}
		return s;
	}

	private static Profile toNewProfile(ProfileCreateRequest d) {
		Profile p = new Profile();
		p.setEmail(d.getEmail());
		p.setPhone(d.getPhone());
		return p;
	}

// Update
	public static void applyUpdate(StudentUpdateRequest req, Student e) {
		if (req.getName() != null)
			e.setName(req.getName());
		if (req.getDob() != null)
			e.setDob(req.getDob());
		if (req.getProfile() != null) {
			Profile p = e.getProfile();
			if (p == null)
				p = new Profile();
			ProfileUpdateRequest u = req.getProfile();
			if (u.getEmail() != null)
				p.setEmail(u.getEmail());
			if (u.getPhone() != null)
				p.setPhone(u.getPhone());
			e.setProfile(p);
		}
	}
}
