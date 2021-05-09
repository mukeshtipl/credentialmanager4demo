package com.mylearning.credentialmanager4demo.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mylearning.credentialmanager4demo.model.Credential;
import com.mylearning.credentialmanager4demo.service.ICredentialService;
import com.opencsv.CSVReader;

@Controller
public class CredentialController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ICredentialService credentialService;

	@GetMapping("/credentials")
	public ModelAndView showCredentials() {

		List<Credential> credentials = credentialService.findAll();

		Map<String, Object> params = new HashMap<>();
		params.put("credentials", credentials);

		return new ModelAndView("credentials", params);
	}

	/*
	 * https://www.baeldung.com/opencsv
	 * 
	 */
	@PostMapping("/uploadPasswordFile")
	public ModelAndView handleFileUpload(@RequestParam("passwordFile") MultipartFile file, Model model) {
		if (file.isEmpty()) {
			model.addAttribute("message", "Please select a CSV file to upload.");
			model.addAttribute("status", false);
		} else {
			String name = file.getOriginalFilename();
			try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

//				CsvToBean<Credential> csvToBean = new CsvToBeanBuilder(reader).withType(Credential.class)
//						.withIgnoreLeadingWhiteSpace(true).build();

//				List<Credential> credentialPasswords = csvToBean.parse();
//
//				// Logic to update passwords
//
//				for (Iterator iterator = credentialPasswords.iterator(); iterator.hasNext();) {
//					Credential credential = (Credential) iterator.next();
//					credentialService.updatePassword(credential.getId(), credential.getEncryptedPassword());
//
//				}
//
//				model.addAttribute("credentialPasswords", credentialPasswords);

				List<String[]> list = new ArrayList<String[]>();
				CSVReader csvReader = new CSVReader(reader);
				csvReader.skip(1);

				String[] line;
				while ((line = csvReader.readNext()) != null) {
					list.add(line);
					logger.info("Id->" + line[0] + ", Pwd:" + line[3]);
					logger.info(String.join(",", line));
					credentialService.updatePassword(Integer.valueOf(line[0]), line[3]);

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
