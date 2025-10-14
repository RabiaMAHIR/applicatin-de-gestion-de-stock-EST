package  ma.zyn.app.ws.facade.admin.input;

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

import ma.zyn.app.bean.core.input.EntreeStock;
import ma.zyn.app.dao.criteria.core.input.EntreeStockCriteria;
import ma.zyn.app.service.facade.admin.input.EntreeStockAdminService;
import ma.zyn.app.ws.converter.input.EntreeStockConverter;
import ma.zyn.app.ws.dto.input.EntreeStockDto;
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
@RequestMapping("/api/admin/entreeStock/")
public class EntreeStockRestAdmin {




    @Operation(summary = "Finds a list of all entreeStocks")
    @GetMapping("")
    public ResponseEntity<List<EntreeStockDto>> findAll() throws Exception {
        ResponseEntity<List<EntreeStockDto>> res = null;
        List<EntreeStock> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<EntreeStockDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    // <-------------------------------------------------------------->

    @Operation(summary = "Gets total Entree Stock amount for a specific year and month")
    @GetMapping("/total/{year}/{month}")
    public ResponseEntity<BigDecimal> getTotalForYearAndMonth(@PathVariable int year, @PathVariable int month) {
        BigDecimal total = service.getTotalForYearAndMonth(year, month);
        return ResponseEntity.ok(total);
    }

    @Operation(summary = "Gets total Entree Stock income for a specific year")
    @GetMapping("/total/year/{year}")
    public ResponseEntity<BigDecimal> getTotalForYear(@PathVariable int year) {
        BigDecimal total = service.getTotalForYear(year);
        return ResponseEntity.ok(total);
    }
    @Operation(summary = "Gets total quantity of Entree Stock for a specific year and month")
    @GetMapping("/quantity/{year}/{month}")
    public ResponseEntity<BigDecimal> calculateQuantityByYearAndMonth(@PathVariable int year, @PathVariable int month) {
        BigDecimal quantity = service.calculateQuantityByYearAndMonth(year, month);
        return ResponseEntity.ok(quantity);
    }

    @Operation(summary = "Gets the change in total Entree Stock income for a specific year")
    @GetMapping("/change/{year}")
    public ResponseEntity<BigDecimal> getChangeEntreeTotalForYear(@PathVariable int year) {
        BigDecimal change = service.getChangeTotalForYear(year);
        return ResponseEntity.ok(change);
    }



    // <-------------------------------------------------------------->

  

    @Operation(summary = "Finds an optimized list of all entreeStocks")
    @GetMapping("optimized")
    public ResponseEntity<List<EntreeStockDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EntreeStockDto>> res = null;
        List<EntreeStock> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<EntreeStockDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a entreeStock by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EntreeStockDto> findById(@PathVariable Long id) {
        EntreeStock t = service.findById(id);
        if (t != null) {
            converter.init(true);
            EntreeStockDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a entreeStock by code")
    @GetMapping("code/{code}")
    public ResponseEntity<EntreeStockDto> findByCode(@PathVariable String code) {
	    EntreeStock t = service.findByReferenceEntity(new EntreeStock(code));
        if (t != null) {
            converter.init(true);
            EntreeStockDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  entreeStock")
    @PostMapping("")
    public ResponseEntity<EntreeStockDto> save(@RequestBody EntreeStockDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            EntreeStock myT = converter.toItem(dto);
            EntreeStock t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EntreeStockDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  entreeStock")
    @PutMapping("")
    public ResponseEntity<EntreeStockDto> update(@RequestBody EntreeStockDto dto) throws Exception {
        ResponseEntity<EntreeStockDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EntreeStock t = service.findById(dto.getId());
            converter.copy(dto,t);
            EntreeStock updated = service.update(t);
            EntreeStockDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of entreeStock")
    @PostMapping("multiple")
    public ResponseEntity<List<EntreeStockDto>> delete(@RequestBody List<EntreeStockDto> dtos) throws Exception {
        ResponseEntity<List<EntreeStockDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<EntreeStock> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified entreeStock")
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


    @Operation(summary = "Finds a entreeStock and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EntreeStockDto> findWithAssociatedLists(@PathVariable Long id) {
        EntreeStock loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        EntreeStockDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds entreeStocks by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EntreeStockDto>> findByCriteria(@RequestBody EntreeStockCriteria criteria) throws Exception {
        ResponseEntity<List<EntreeStockDto>> res = null;
        List<EntreeStock> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<EntreeStockDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated entreeStocks by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EntreeStockCriteria criteria) throws Exception {
        List<EntreeStock> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<EntreeStockDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets entreeStock data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EntreeStockCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EntreeStockDto> findDtos(List<EntreeStock> list){
        converter.initList(false);
        converter.initObject(true);
        List<EntreeStockDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EntreeStockDto> getDtoResponseEntity(EntreeStockDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public EntreeStockRestAdmin(EntreeStockAdminService service, EntreeStockConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final EntreeStockAdminService service;
    private final EntreeStockConverter converter;





}
