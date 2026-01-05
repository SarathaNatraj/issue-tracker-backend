package com.example.issue_tracker_backend.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Parameter;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/files")
public class FileController {

	private final String UPLOAD_DIR = "uploads/";

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<JsonObject> upload(
			@Parameter(description = "File to upload", required = true) @RequestPart("file") MultipartFile file)

			throws IOException {

		if (!file.getContentType().startsWith("image/")) {
			return ResponseEntity.badRequest()
					.body(Json.createObjectBuilder().add("error", "Only image files are allowed").build());
		}
		//Core Java code to perform the file copy
		Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
		Files.createDirectories(path.getParent());
		Files.write(path, file.getBytes());
		
		return ResponseEntity.ok(Json.createObjectBuilder().add("message", "File uploaded")
				.add("name", file.getOriginalFilename()).build());
	}

	@GetMapping("/{name}")
	public ResponseEntity<Resource> download(@PathVariable String name) throws IOException {

		Path path = Paths.get(UPLOAD_DIR + name);
		if (!Files.exists(path))
			return ResponseEntity.notFound().build();

		String mimeType = Files.probeContentType(path);
		if (mimeType == null) {
		    mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE; // fallback
		}

		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path)); //creating a new file same file name 

		return ResponseEntity.ok()
		        .contentType(MediaType.parseMediaType(mimeType))
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + name + "\"")
		        .body(resource);
	}

	@GetMapping("/report")
	public JsonObject getReport() {

		JsonArrayBuilder usersArray = Json.createArrayBuilder();

		usersArray.add(Json.createObjectBuilder().add("name", "John").add("email", "john@gmail.com"));

		usersArray.add(Json.createObjectBuilder().add("name", "Alice").add("email", "alice@gmail.com"));

		return Json.createObjectBuilder().add("count", 2).add("users", usersArray).build();
	}

}
