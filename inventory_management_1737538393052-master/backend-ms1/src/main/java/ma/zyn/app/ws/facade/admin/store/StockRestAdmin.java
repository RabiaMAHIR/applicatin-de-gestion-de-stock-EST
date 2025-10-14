package  ma.zyn.app.ws.facade.admin.store;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.store.Stock;
import ma.zyn.app.dao.criteria.core.store.StockCriteria;
import ma.zyn.app.service.facade.admin.store.StockAdminService;
import ma.zyn.app.ws.converter.store.StockConverter;
import ma.zyn.app.ws.dto.store.StockDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/stock/")
public class StockRestAdmin {




    @Operation(summary = "Finds a list of all stocks")
    @GetMapping("")
    public ResponseEntity<List<StockDto>> findAll() throws Exception {
        ResponseEntity<List<StockDto>> res = null;
        List<Stock> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<StockDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a stock by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StockDto> findById(@PathVariable Long id) {
        Stock t = service.findById(id);
        if (t != null) {
            converter.init(true);
            StockDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }






    // <-------------------------------------------------------------->
    @Operation(summary = "Finds stocks by magazin id")
    @GetMapping("/magazin/{labelMagazin}")
    public ResponseEntity<List<StockDto>> getStocksByMagazinId(@PathVariable String labelMagazin) {
        List<Stock> stocks = service.findStocksByMagazinId(labelMagazin);
        List<StockDto> stockDtos = converter.toDto(stocks); // تحويل إلى StockDto
        return ResponseEntity.ok(stockDtos);
    }


    @Operation(summary = "Finds total quantities for all products across all stocks")
    @GetMapping("/produits/total-quantities")
    public ResponseEntity<List<Map<String, BigDecimal>>> getTotalQuantitiesForAllProduits() {
        List<Map<String, BigDecimal>> totalQuantities = service.findTotalQuantitiesForAllProduits();
        return ResponseEntity.ok(totalQuantities);
    }
    // <-------------------------------------------------------------->



    @Operation(summary = "Saves the specified  stock")
    @PostMapping("")
    public ResponseEntity<StockDto> save(@RequestBody StockDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Stock myT = converter.toItem(dto);
            Stock t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                StockDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  stock")
    @PutMapping("")
    public ResponseEntity<StockDto> update(@RequestBody StockDto dto) throws Exception {
        ResponseEntity<StockDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Stock t = service.findById(dto.getId());
            converter.copy(dto,t);
            Stock updated = service.update(t);
            StockDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of stock")
    @PostMapping("multiple")
    public ResponseEntity<List<StockDto>> delete(@RequestBody List<StockDto> dtos) throws Exception {
        ResponseEntity<List<StockDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Stock> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified stock")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }

    @Operation(summary = "find by produit id")
    @GetMapping("produit/id/{id}")
    public List<StockDto> findByProduitId(@PathVariable Long id){
        return findDtos(service.findByProduitId(id));
    }
    @Operation(summary = "delete by produit id")
    @DeleteMapping("produit/id/{id}")
    public int deleteByProduitId(@PathVariable Long id){
        return service.deleteByProduitId(id);
    }

    @Operation(summary = "Finds a stock and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StockDto> findWithAssociatedLists(@PathVariable Long id) {
        Stock loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        StockDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds stocks by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<StockDto>> findByCriteria(@RequestBody StockCriteria criteria) throws Exception {
        ResponseEntity<List<StockDto>> res = null;
        List<Stock> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<StockDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated stocks by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody StockCriteria criteria) throws Exception {
        List<Stock> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<StockDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets stock data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody StockCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<StockDto> findDtos(List<Stock> list){
        converter.initObject(true);
        List<StockDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<StockDto> getDtoResponseEntity(StockDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public StockRestAdmin(StockAdminService service, StockConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final StockAdminService service;
    private final StockConverter converter;





}
