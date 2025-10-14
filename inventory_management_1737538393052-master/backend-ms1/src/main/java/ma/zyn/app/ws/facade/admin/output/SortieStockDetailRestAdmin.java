package  ma.zyn.app.ws.facade.admin.output;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ma.zyn.app.bean.core.input.EntreeStockDetail;
import ma.zyn.app.ws.dto.input.EntreeStockDetailDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.output.SortieStockDetail;
import ma.zyn.app.dao.criteria.core.output.SortieStockDetailCriteria;
import ma.zyn.app.service.facade.admin.output.SortieStockDetailAdminService;
import ma.zyn.app.ws.converter.output.SortieStockDetailConverter;
import ma.zyn.app.ws.dto.output.SortieStockDetailDto;
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
@RequestMapping("/api/admin/sortieStockDetail/")
public class SortieStockDetailRestAdmin {




    @Operation(summary = "Finds a list of all sortieStockDetails")
    @GetMapping("")
    public ResponseEntity<List<SortieStockDetailDto>> findAll() throws Exception {
        ResponseEntity<List<SortieStockDetailDto>> res = null;
        List<SortieStockDetail> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<SortieStockDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a sortieStockDetail by id")
    @GetMapping("id/{id}")
    public ResponseEntity<SortieStockDetailDto> findById(@PathVariable Long id) {
        SortieStockDetail t = service.findById(id);
        if (t != null) {
            converter.init(true);
            SortieStockDetailDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  sortieStockDetail")
    @PostMapping("")
    public ResponseEntity<SortieStockDetailDto> save(@RequestBody SortieStockDetailDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            SortieStockDetail myT = converter.toItem(dto);
            SortieStockDetail t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                SortieStockDetailDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  sortieStockDetail")
    @PutMapping("")
    public ResponseEntity<SortieStockDetailDto> update(@RequestBody SortieStockDetailDto dto) throws Exception {
        ResponseEntity<SortieStockDetailDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            SortieStockDetail t = service.findById(dto.getId());
            converter.copy(dto,t);
            SortieStockDetail updated = service.update(t);
            SortieStockDetailDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of sortieStockDetail")
    @PostMapping("multiple")
    public ResponseEntity<List<SortieStockDetailDto>> delete(@RequestBody List<SortieStockDetailDto> dtos) throws Exception {
        ResponseEntity<List<SortieStockDetailDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<SortieStockDetail> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified sortieStockDetail")
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

    @Operation(summary = "Finds sortieStockDetails by magasinId and produitId")
    @GetMapping("findByMagazinIdAndProduitId")
    public ResponseEntity<List<SortieStockDetailDto>> findByMagazinIdAndProduitId(@RequestParam Long magasinId, @RequestParam Long produitId) {
        List<SortieStockDetail> list = service.findByMagazinIdAndProduitId(magasinId, produitId);
        List<SortieStockDetailDto> dtos = findDtos(list);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @Operation(summary = "find by sortieStock id")
    @GetMapping("sortieStock/id/{id}")
    public List<SortieStockDetailDto> findBySortieStockId(@PathVariable Long id){
        return findDtos(service.findBySortieStockId(id));
    }
    @Operation(summary = "delete by sortieStock id")
    @DeleteMapping("sortieStock/id/{id}")
    public int deleteBySortieStockId(@PathVariable Long id){
        return service.deleteBySortieStockId(id);
    }
    @Operation(summary = "find by produit id")
    @GetMapping("produit/id/{id}")
    public List<SortieStockDetailDto> findByProduitId(@PathVariable Long id){
        return findDtos(service.findByProduitId(id));
    }
    @Operation(summary = "delete by produit id")
    @DeleteMapping("produit/id/{id}")
    public int deleteByProduitId(@PathVariable Long id){
        return service.deleteByProduitId(id);
    }

    @Operation(summary = "Finds a sortieStockDetail and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<SortieStockDetailDto> findWithAssociatedLists(@PathVariable Long id) {
        SortieStockDetail loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        SortieStockDetailDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds sortieStockDetails by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<SortieStockDetailDto>> findByCriteria(@RequestBody SortieStockDetailCriteria criteria) throws Exception {
        ResponseEntity<List<SortieStockDetailDto>> res = null;
        List<SortieStockDetail> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<SortieStockDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated sortieStockDetails by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody SortieStockDetailCriteria criteria) throws Exception {
        List<SortieStockDetail> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<SortieStockDetailDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets sortieStockDetail data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody SortieStockDetailCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<SortieStockDetailDto> findDtos(List<SortieStockDetail> list){
        converter.initObject(true);
        List<SortieStockDetailDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<SortieStockDetailDto> getDtoResponseEntity(SortieStockDetailDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public SortieStockDetailRestAdmin(SortieStockDetailAdminService service, SortieStockDetailConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final SortieStockDetailAdminService service;
    private final SortieStockDetailConverter converter;





}
