package com.example.iems.controller;

import com.example.iems.dto.Company.CreateCompanyRequest;
import com.example.iems.dto.Supply.CreateSupplyRequest;
import com.example.iems.model.Product;
import com.example.iems.model.Supply;
import com.example.iems.service.SupplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/appSupply")
public class SupplyController {

    private final SupplyService supplyService;

    public SupplyController(SupplyService supplyService){
        this.supplyService=supplyService;
    }

    @PostMapping("/manager/createSupply")
    public ResponseEntity<Map<String, String>> createSupply(@RequestBody CreateSupplyRequest request) {
        return supplyService.createSupply(request);
    }

    @DeleteMapping("/manager/deleteSupply")
    public ResponseEntity<String> DeleteSupply(Long id){
        return supplyService.deleteSupply(id);
    }


    @GetMapping("/free/getSupply/all")
    public List<Supply> getAllSupplies(){
        return supplyService.getAllSupplies();
    }

    @GetMapping("/free/getSupply")
    public Optional<Supply> getSupplyById(Long id){
        return supplyService.getSupplyById(id);
    }

    @GetMapping("/free/getSupply/open")
    public List<Supply> getSupplyOpen(Long companyId){
        return supplyService.getSupplyOpen(companyId);
    }

    @GetMapping("/free/getSupply/close")
    public List<Supply> getSupplyClose(Long companyId){
        return supplyService.getSupplyClose(companyId);
    }

    @GetMapping("/free/getSupply/close-confirmed")
    public List<Supply> getSupplyCloseAndConfirmed(){
        return supplyService.getSupplyCloseAndConfirmed();
    }

    @GetMapping("/free/getSupply/pending")
    public List<Supply> getSupplyPending(Long companyId){
        return supplyService.getSupplyPending(companyId);
    }

    @GetMapping("/free/getSupply/duration")
    public List<Supply> getSupplyDuration(Long companyId){
        return supplyService.getSupplyDuration(companyId);
    }

    @GetMapping("/free/getSupply/complete")
    public List<Supply> getSupplyComplete(Long companyId){
        return supplyService.getSupplyComplete(companyId);
    }

    @GetMapping("/free/getSupply/unsuccessful")
    public List<Supply> getSupplyUnsuccessful(Long companyId){
        return supplyService.getSupplyUnsuccessful(companyId);
    }

    @GetMapping("/free/getSupply/confirmed")
    public List<Supply> getSupplyConfirmed(Long companyId){
        return supplyService.getSupplyConfirmed(companyId);
    }

    @GetMapping("/manager/publicationSupply")
    public ResponseEntity<String> publicationSupply(Long id){
        return supplyService.publicationSupply(id);
    }

    @GetMapping("/supplier/closingSupply")
    public ResponseEntity<String> closingSupply(Long id){
        return supplyService.closingSupply(id);
    }

    @GetMapping("/manager/closingSupply")
    public ResponseEntity<String> closingSupplyForManager(Long id){
        return supplyService.closingSupply(id);
    }

    @GetMapping("/manager/toPendingSupply")
    public ResponseEntity<String> toPendingSupply(Long id){
        return supplyService.toPendingSupply(id);
    }

    @GetMapping("/supplier/toDurationSupply")
    public ResponseEntity<String> toDurationSupply(Long id){
        return supplyService.toDurationSupply(id);
    }

    @GetMapping("/supplier/toCompleteSupply")
    public ResponseEntity<String> toCompleteSupply(Long id){
        return supplyService.toCompleteSupply(id);
    }

    @GetMapping("/supplier/toUnsuccessfulSupply")
    public ResponseEntity<String> toUnsuccessfulSupply(Long id){
        return supplyService.toUnsuccessfulSupply(id);
    }
    @GetMapping("/manager/toConfirmedSupply")
    public ResponseEntity<String> toConfirmedSupply(Long id){
        return supplyService.toConfirmedSupply(id);
    }

    @GetMapping("/free/getSupply/open-pending")
    public List<Supply> getSupplyByOpenAndPending(){
        return supplyService.getOpenPendingSupplies();
    }

    @PostMapping("/supplier/updateSupplierAtSupply")
    public ResponseEntity<Map<String, String>> createSupply(Long supplyId, Long supplierId) {
        return supplyService.updateSupplierAtSupply(supplyId,supplierId);
    }

    @GetMapping("/supplier/getSuppliesBySupplierDuration")
    public List<Supply> getSuppliesBySupplierDuration(Long supplierId) {
        return supplyService.getSuppliesBySupplierDuration(supplierId);
    }

    @GetMapping("/supplier/getSuppliesBySupplierComplete")
    public List<Supply> getSuppliesBySupplierComplete(Long supplierId) {
        return supplyService.getSuppliesBySupplierComplete(supplierId);
    }

    @GetMapping("/supplier/getSuppliesBySupplierUnsuccessful")
    public List<Supply> getSuppliesBySupplierUnsuccessful(Long supplierId) {
        return supplyService.getSuppliesBySupplierUnsuccessful(supplierId);
    }

    @GetMapping("/supplier/getSuppliesBySupplierConfirmed")
    public List<Supply> getSuppliesBySupplierConfirmed(Long supplierId) {
        return supplyService.getSuppliesBySupplierConfirmed(supplierId);
    }

    @GetMapping("/manager/removeSupplierFromSupply")
    public ResponseEntity<String> removeSupplierFromSupply(Long supplyId) {
        return supplyService.removeSupplierFromSupply(supplyId);
    }


    @GetMapping("/manager/getSuppliesByCompanyId")
    public List<Supply>  getSuppliesByCompanyId(Long companyId) {
        return supplyService.getSuppliesByCompanyId(companyId);
    }

    @GetMapping("/supplier/getNote")
    public String  getNote(Long supplyId) {
        return supplyService.getNote(supplyId);
    }

}
