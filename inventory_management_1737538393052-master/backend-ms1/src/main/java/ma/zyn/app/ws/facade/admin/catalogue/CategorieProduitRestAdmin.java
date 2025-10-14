package  ma.zyn.app.ws.facade.admin.catalogue;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.catalogue.CategorieProduit;
import ma.zyn.app.dao.criteria.core.catalogue.CategorieProduitCriteria;
import ma.zyn.app.service.facade.admin.catalogue.CategorieProduitAdminService;
import ma.zyn.app.ws.converter.catalogue.CategorieProduitConverter;
import ma.zyn.app.ws.dto.catalogue.CategorieProduitDto;
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
@RequestMapping("/api/admin/categorieProduit/")
public class CategorieProduitRestAdmin {




    @Operation(summary = "Finds a list of all categorieProduits")
    @GetMapping("")
    public ResponseEntity<List<CategorieProduitDto>> findAll() throws Exception {
        ResponseEntity<List<CategorieProduitDto>> res = null;
        List<CategorieProduit> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategorieProduitDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all categorieProduits")
    @GetMapping("optimized")
    public ResponseEntity<List<CategorieProduitDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CategorieProduitDto>> res = null;
        List<CategorieProduit> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategorieProduitDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a categorieProduit by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CategorieProduitDto> findById(@PathVariable Long id) {
        CategorieProduit t = service.findById(id);
        if (t != null) {
            CategorieProduitDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a categorieProduit by label")
    @GetMapping("label/{label}")
    public ResponseEntity<CategorieProduitDto> findByLabel(@PathVariable String label) {
	    CategorieProduit t = service.findByReferenceEntity(new CategorieProduit(label));
        if (t != null) {
            CategorieProduitDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  categorieProduit")
    @PostMapping("")
    public ResponseEntity<CategorieProduitDto> save(@RequestBody CategorieProduitDto dto) throws Exception {
        if(dto!=null){
            CategorieProduit myT = converter.toItem(dto);
            CategorieProduit t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CategorieProduitDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  categorieProduit")
    @PutMapping("")
    public ResponseEntity<CategorieProduitDto> update(@RequestBody CategorieProduitDto dto) throws Exception {
        ResponseEntity<CategorieProduitDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CategorieProduit t = service.findById(dto.getId());
            converter.copy(dto,t);
            CategorieProduit updated = service.update(t);
            CategorieProduitDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of categorieProduit")
    @PostMapping("multiple")
    public ResponseEntity<List<CategorieProduitDto>> delete(@RequestBody List<CategorieProduitDto> dtos) throws Exception {
        ResponseEntity<List<CategorieProduitDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<CategorieProduit> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified categorieProduit")
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


    @Operation(summary = "Finds a categorieProduit and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CategorieProduitDto> findWithAssociatedLists(@PathVariable Long id) {
        CategorieProduit loaded =  service.findWithAssociatedLists(id);
        CategorieProduitDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds categorieProduits by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CategorieProduitDto>> findByCriteria(@RequestBody CategorieProduitCriteria criteria) throws Exception {
        ResponseEntity<List<CategorieProduitDto>> res = null;
        List<CategorieProduit> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategorieProduitDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated categorieProduits by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CategorieProduitCriteria criteria) throws Exception {
        List<CategorieProduit> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<CategorieProduitDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets categorieProduit data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CategorieProduitCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CategorieProduitDto> findDtos(List<CategorieProduit> list){
        List<CategorieProduitDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CategorieProduitDto> getDtoResponseEntity(CategorieProduitDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public CategorieProduitRestAdmin(CategorieProduitAdminService service, CategorieProduitConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CategorieProduitAdminService service;
    private final CategorieProduitConverter converter;





}
