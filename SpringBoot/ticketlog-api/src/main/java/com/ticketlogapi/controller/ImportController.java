package com.ticketlogapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ticketlogapi.entities.Cidade;
import com.ticketlogapi.entities.Estado;
import com.ticketlogapi.services.CidadeService;
import com.ticketlogapi.services.EstadoService;

@RestController
public class ImportController {

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(value = "/import-excel", method = RequestMethod.POST)
	public ResponseEntity<Cidade> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);

		int b = worksheet.getPhysicalNumberOfRows();

		System.out.println(b);
		for (int index = 1; index < worksheet.getPhysicalNumberOfRows(); index++) {
			if (index < worksheet.getPhysicalNumberOfRows()) {

				XSSFRow row = worksheet.getRow(index);
				Integer estadoId = (int) row.getCell(1).getNumericCellValue();
				String cidadeName = ((row.getCell(2).getStringCellValue()));
				Integer cidadePopulacao = (int) row.getCell(3).getNumericCellValue();

				Estado estado = estadoService.findById(estadoId).orElseThrow(() -> new EntityNotFoundException());

				Cidade cidade = new Cidade(null, cidadeName, estado, cidadePopulacao);

				cidadeService.addCidade(cidade);
				estadoService.sumPopulacao(cidade.getEstadoId());

			}

		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
