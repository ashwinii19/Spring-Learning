package com.aurionpro.app.config;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aurionpro.app.dto.CourseResponse;
import com.aurionpro.app.dto.CourseSummary;
import com.aurionpro.app.dto.StudentResponse;
import com.aurionpro.app.dto.StudentSummary;
import com.aurionpro.app.entity.Course;
import com.aurionpro.app.entity.Student;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		
		//ModelMapper Configuration
		// STRICT: Property names must exactly match.
		//setFieldMatchingEnabled(true): Allows mapping fields directly without getters/setters.
		//setSkipNullEnabled(true): Null values will not overwrite existing data during mapping. 
				
		ModelMapper mm = new ModelMapper();
		mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
										.setFieldMatchingEnabled(true)
										.setSkipNullEnabled(true);

		// Custom Converters
		// Converter for Student -> CourseSummary
		// This defines how to convert a Student object’s courses into CourseSummary objects.
		// It checks if Student or its courses are null before mapping.
		
		Converter<Student, Set<CourseSummary>> studentCourses = ctx -> toCourseSummaries(
				ctx.getSource() != null ? ctx.getSource().getCourses() : null);

		// Converter for Course -> StudentSummary
		// Similar to the above, but maps a Course’s students to StudentSummary
		
		Converter<Course, Set<StudentSummary>> courseStudents = ctx -> toStudentSummaries(
				ctx.getSource() != null ? ctx.getSource().getStudents() : null);

		// --- Type maps using those converters ---
		// (Mapping for Student -> StudentResponse)
		// This tells ModelMapper how to map a Student to a StudentResponse.
		// It uses the studentCourses converter for mapping courses.
		
		mm.typeMap(Student.class, StudentResponse.class)
				.addMappings(m -> m.using(studentCourses).map(src -> src, StudentResponse::setCourses));

		// Mapping for Course -> CourseResponse
		// Similarly, this maps a Course to a CourseResponse using courseStudents.
		mm.typeMap(Course.class, CourseResponse.class)
				.addMappings(m -> m.using(courseStudents).map(src -> src, CourseResponse::setStudents));

		return mm;
	}

	// helper methods
	// Convert Courses to Summaries
	// Filters out null courses and collects the result.
	private static Set<CourseSummary> toCourseSummaries(Set<Course> courses) {
		if (courses == null)
			return Set.of();
		return courses.stream().filter(Objects::nonNull)
				.map(c -> new CourseSummary(c.getId(), c.getCode(), 
						c.getTitle())).collect(Collectors.toSet());
	}

	// Convert Students to Summaries
	// Filters null values and collects them into a set.
	private static Set<StudentSummary> toStudentSummaries(Set<Student> students) {
		if (students == null)
			return Set.of();
		return students.stream().filter(Objects::nonNull).map(s -> new StudentSummary(s.getId(), s.getName()))
				.collect(Collectors.toSet());
	}
}
