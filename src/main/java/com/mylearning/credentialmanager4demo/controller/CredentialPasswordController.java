package com.mylearning.credentialmanager4demo.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.opencsv.CSVReader;

@Controller
public class CredentialPasswordController {

	@PostMapping("/uploadPasswordFileBackup")

	public ModelAndView handleFileUpload(@RequestParam("passwordFile") MultipartFile file, Model model) {
		if (file.isEmpty()) {
			model.addAttribute("message", "Please select a CSV file to upload.");
			model.addAttribute("status", false);
		} else {
			String name = file.getOriginalFilename();
			try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

				// Logic to update passwords
//				CsvToBean<Credential> csvToBean = new CsvToBeanBuilder(reader).withType(Credential.class)
//						.withIgnoreLeadingWhiteSpace(true).build();
//
//				List<Credential> credentialPasswords = csvToBean.parse();
//
//				
//
//				model.addAttribute("credentialPasswords", credentialPasswords);

				List<String[]> list = new ArrayList<String[]>();
				CSVReader csvReader = new CSVReader(reader);
				String[] line;
				while ((line = csvReader.readNext()) != null) {
					list.add(line);
					System.out.println(line);

				}
				csvReader.close();

				model.addAttribute("status", true);

				// System.out.println("Uploaded file name:" + name);
				// byte[] bytes = file.getBytes();

				// String content = readInputFile(file.getInputStream());
				// System.out.println("Uploaded file content:" + content);

				// Files.write(new File(name).toPath(), bytes);
			} catch (Exception e) {

				model.addAttribute("message", "An error occurred while processing the CSV file.");
				model.addAttribute("status", false);
				e.printStackTrace();
			}
		}
		// return "redirect:/credentials";
		return new ModelAndView("credentialPasswords", model.asMap());
	}

	protected String readInputFile(InputStream fis) throws Exception {

		// FileInputStream fis = new FileInputStream(fileName);
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {

			while (reader.ready()) {
				String line = reader.readLine();
				sb.append(line + "<BR>");
			}
		}

		String content = sb.toString();
		return content;
	}

}
