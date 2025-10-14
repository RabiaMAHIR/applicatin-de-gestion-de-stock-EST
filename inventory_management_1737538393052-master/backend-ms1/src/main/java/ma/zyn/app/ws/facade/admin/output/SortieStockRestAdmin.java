package  ma.zyn.app.ws.facade.admin.output;

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

import ma.zyn.app.bean.core.output.SortieStock;
import ma.zyn.app.dao.criteria.core.output.SortieStockCriteria;
import ma.zyn.app.service.facade.admin.output.SortieStockAdminService;
import ma.zyn.app.ws.converter.output.SortieStockConverter;
import ma.zyn.app.ws.dto.output.SortieStockDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/sortieStock/")
public class SortieStockRestAdmin {




    @Operation(summary = "Finds a list of all sortieStocks")
    @GetMapping("")
    public ResponseEntity<List<SortieStockDto>> findAll() throws Exception {
        ResponseEntity<List<SortieStockDto>> res = null;
        List<SortieStock> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        List<SortieStockDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    // <-------------------------------------------------------------->
    @Operation(summary = "Gets total Sortie Stock amount for a specific year and month")
    @GetMapping("/total/{year}/{month}")
    public ResponseEntity<BigDecimal> getTotalSortForYearAndMonth(@PathVariable int year, @PathVariable int month) {
        BigDecimal total = service.getTotalSortForYearAndMonth(year, month);
        return ResponseEntity.ok(total);
    }

    @Operation(summary = "Gets total Sortie Stock income for a specific year")
    @GetMapping("/total/year/{year}")
    public ResponseEntity<BigDecimal> getTotalSortForYear(@PathVariable int year) {
        BigDecimal total = service.getTotalSortForYear(year);
        return ResponseEntity.ok(total);
    }

    @Operation(summary = "Gets total quantity of Sortie Stock for a specific year and month")
    @GetMapping("/quantity/{year}/{month}")
    public ResponseEntity<BigDecimal> calculateSortQuantityByYearAndMonth(@PathVariable int year, @PathVariable int month) {
        BigDecimal quantity = service.calculateSortQuantityByYearAndMonth(year, month);
        return ResponseEntity.ok(quantity);
    }
    @Operation(summary = "Gets the change in total Sortie Stock income for a specific year")
    @GetMapping("/change/{year}")
    public ResponseEntity<BigDecimal> getChangeSortieTotalForYear(@PathVariable int year) {
        BigDecimal change = service.getChangeTotalForYear(year);
        return ResponseEntity.ok(change);
    }

    // <-------------------------------------------------------------->


    @Operation(summary = "Finds an optimized list of all sortieStocks")
    @GetMapping("optimized")
    public ResponseEntity<List<SortieStockDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<SortieStockDto>> res = null;
        List<SortieStock> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        List<SortieStockDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a sortieStock by id")
    @GetMapping("id/{id}")
    public ResponseEntity<SortieStockDto> findById(@PathVariable Long id) {
        SortieStock t = service.findById(id);
        if (t != null) {
            converter.init(true);
            SortieStockDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a sortieStock by code")
    @GetMapping("code/{code}")
    public ResponseEntity<SortieStockDto> findByCode(@PathVariable String code) {
	    SortieStock t = service.findByReferenceEntity(new SortieStock(code));
        if (t != null) {
            converter.init(true);
            SortieStockDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  sortieStock")
    @PostMapping("")
    public ResponseEntity<SortieStockDto> save(@RequestBody SortieStockDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            SortieStock myT = converter.toItem(dto);
            SortieStock t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                SortieStockDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  sortieStock")
    @PutMapping("")
    public ResponseEntity<SortieStockDto> update(@RequestBody SortieStockDto dto) throws Exception {
        ResponseEntity<SortieStockDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            SortieStock t = service.findById(dto.getId());
            converter.copy(dto,t);
            SortieStock updated = service.update(t);
            SortieStockDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of sortieStock")
    @PostMapping("multiple")
    public ResponseEntity<List<SortieStockDto>> delete(@RequestBody List<SortieStockDto> dtos) throws Exception {
        ResponseEntity<List<SortieStockDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<SortieStock> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified sortieStock")
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


    @Operation(summary = "Finds a sortieStock and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<SortieStockDto> findWithAssociatedLists(@PathVariable Long id) {
        SortieStock loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        SortieStockDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds sortieStocks by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<SortieStockDto>> findByCriteria(@RequestBody SortieStockCriteria criteria) throws Exception {
        ResponseEntity<List<SortieStockDto>> res = null;
        List<SortieStock> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        List<SortieStockDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated sortieStocks by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody SortieStockCriteria criteria) throws Exception {
        List<SortieStock> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        List<SortieStockDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets sortieStock data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody SortieStockCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<SortieStockDto> findDtos(List<SortieStock> list){
        converter.initList(false);
        List<SortieStockDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<SortieStockDto> getDtoResponseEntity(SortieStockDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public SortieStockRestAdmin(SortieStockAdminService service, SortieStockConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final SortieStockAdminService service;
    private final SortieStockConverter converter;





}
