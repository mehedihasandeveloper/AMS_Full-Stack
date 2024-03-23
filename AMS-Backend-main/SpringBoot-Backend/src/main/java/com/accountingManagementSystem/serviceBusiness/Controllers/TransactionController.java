package com.accountingManagementSystem.serviceBusiness.Controllers;

import com.accountingManagementSystem.serviceBusiness.Models.*;
import com.accountingManagementSystem.serviceBusiness.ReportService.BalanceStatementService;
import com.accountingManagementSystem.serviceBusiness.ReportService.OwnerEquityStatementService;
import com.accountingManagementSystem.serviceBusiness.ReportService.TrialBalanceService;
import com.accountingManagementSystem.serviceBusiness.Services.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/j")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class TransactionController {
    @Autowired
    public TransactionService service;


    @Autowired
    public CashService cashService;
    @Autowired
    public AccountReceivableService receivableService;
    @Autowired
    public EquipmentService equipmentService;
    @Autowired
    public SuppliesService suppliesService;

    @Autowired
    public ExpenseService expenseService;
    @Autowired
    public RevenueService revenueService;
    @Autowired
    public AccountsPayableService payableService;
    @Autowired
    public OwnerCapitalService capitalService;
    @Autowired
    public OwnerDrawingsService drawingsService;

    @Autowired
    public OwnerEquityStatementService OESService;

    @Autowired
    public TrialBalanceService TBService;

    @Autowired
    public BalanceStatementService BSService;

    @GetMapping("/expense")
    public List<Expense> getExpenseData() {
        return expenseService.getAllData();
    }

    @GetMapping("/searchExpense")
    public List<Expense> searchExpense(@RequestParam(value = "id", required = false) Long id,
                                           @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                           @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return expenseService.search(id, startDate, endDate);
    }

    @GetMapping("/revenue")
    public List<Revenue> getRevenueData() {
        return revenueService.getAllData();
    }
    @GetMapping("/searchRevenue")
    public List<Revenue> searchRevenue(@RequestParam(value = "id", required = false) Long id,
                                               @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                               @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return revenueService.search(id, startDate, endDate);
    }
    @GetMapping("/payable")
    public List<AccountsPayable> getPayableData() {
        return payableService.getAllData();
    }
    @GetMapping("/searchPayable")
    public List<AccountsPayable> searchPayable(@RequestParam(value = "id", required = false) Long id,
                                            @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                            @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return payableService.search(id, startDate, endDate);
    }

    @GetMapping("/capital")
    public List<OwnerCapital> getCapitalData() {
        return capitalService.getAllData();
    }
    @GetMapping("/searchCapital")
    public List<OwnerCapital> searchCapital(@RequestParam(value = "id", required = false) Long id,
                                       @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                       @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return capitalService.search(id, startDate, endDate);
    }


    @GetMapping("/drawings")
    public List<OwnerDrawings> getDrawingsData() {
        return drawingsService.getAllData();
    }
    @GetMapping("/searchDrawings")
    public List<OwnerDrawings> searchDrawings(@RequestParam(value = "id", required = false) Long id,
                                            @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                            @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return drawingsService.search(id, startDate, endDate);
    }

    @PostMapping
    public Transaction addData(@RequestBody Transaction transaction) {
        return service.addData(transaction);
    }

    @GetMapping("/supplies")
    public List<Supplies> getSuppliesData() {
        return suppliesService.getAllData();
    }
    @GetMapping("/searchSupplies")
    public List<Supplies> searchSupplies(@RequestParam(value = "id", required = false) Long id,
                                           @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                           @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return suppliesService.search(id, startDate, endDate);
    }

    @GetMapping("/equipment")
    public List<Equipment> getEquipmentData() {
        return equipmentService.getAllData();
    }
    @GetMapping("/searchEquipment")
    public List<Equipment> searchEquipment(@RequestParam(value = "id", required = false) Long id,
                                 @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                 @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return equipmentService.search(id, startDate, endDate);
    }
    @GetMapping("/cash")
    public List<Cash> getCashData() {
        return cashService.getAllData();
    }

    @GetMapping("/searchCash")
    public List<Cash> searchCash(@RequestParam(value = "id", required = false) Long id,
                                    @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                    @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return cashService.search(id, startDate, endDate);
    }

    @GetMapping("/receivable")
    public List<AccountReceivable> getReceivableData() {
        return receivableService.getAllData();
    }
    @GetMapping("/searchReceivable")
    public List<AccountReceivable> searchReceivable(@RequestParam(value = "id", required = false) Long id,
                                 @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                 @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return receivableService.search(id, startDate, endDate);
    }


    @GetMapping
    public List<Transaction> getAllData() {
        return service.getAllData();
    }


    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadInvoice() throws JRException, IOException {

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource((

                service.getAllData()

        ), false);
        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("total", "7000");

        JasperReport compileReport = JasperCompileManager
                .compileReport(new FileInputStream("src/main/resources/Transaction.jrxml"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);


        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

        System.err.println(data);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }


    @GetMapping("/search")
    public List<Transaction> search(@RequestParam(value = "id", required = false) Long id,
                                    @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                    @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return service.search(id, startDate, endDate);
    }

    @GetMapping("/{id}")
    public Transaction getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Transaction updateData(@PathVariable("id") Long id,
                                  @RequestBody Transaction transaction) {
        transaction.setId(id);
        return service.updateData(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable("id") Long id) {
        service.deleteData(id);
    }

    @PostMapping("/fileSystem/{jId}")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file,
                                                     @PathVariable("jId") Long jId) throws IOException {
        FileData fileData;

        String message;
        try {
            fileData = service.uploadImageToFileSystem(file, jId);
            return ResponseEntity.status(HttpStatus.OK).body(fileData);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }


    @PutMapping("/updateFile/{id}")
    public FileData updateFile(@PathVariable("id") Long id,
                              @RequestBody FileData fileData) {

        return service.updateFile(fileData);
    }


    @GetMapping("/report")
    public ResponseEntity<?> reportGenerate(@RequestParam(value ="startDate") String startDate, @RequestParam(value ="endDate") String endDate){

        return ResponseEntity.status(HttpStatus.OK)
                .body(revenueService.getData(startDate, endDate));
    }

    @GetMapping("/report/assetStatement")
    public ResponseEntity<?> reportGenerateAssetStatement(@RequestParam(value ="startDate") String startDate, @RequestParam(value ="endDate") String endDate){

        return ResponseEntity.status(HttpStatus.OK)
                .body(suppliesService.getData(startDate, endDate));
    }

    @GetMapping("/report/OEStatement")
    public ResponseEntity<?> reportGenerateOEStatement(@RequestParam(value ="startDate") String startDate, @RequestParam(value ="endDate") String endDate){

        return ResponseEntity.status(HttpStatus.OK)
                .body(OESService.getData(startDate, endDate));
    }

    @GetMapping("/report/TBStatement")
    public ResponseEntity<?> reportGenerateTrialBalance(@RequestParam(value ="startDate") String startDate, @RequestParam(value ="endDate") String endDate){

        return ResponseEntity.status(HttpStatus.OK)
                .body(TBService.getData(startDate, endDate));
    }

    @GetMapping("/report/BSStatement")
    public ResponseEntity<?> reportGenerateBalanceSheet(@RequestParam(value ="startDate") String startDate, @RequestParam(value ="endDate") String endDate){

        return ResponseEntity.status(HttpStatus.OK)
                .body(BSService.getData(startDate, endDate));
    }


}
