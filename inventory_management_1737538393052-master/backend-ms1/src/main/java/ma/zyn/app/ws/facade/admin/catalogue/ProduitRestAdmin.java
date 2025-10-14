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

import ma.zyn.app.bean.core.catalogue.Produit;
import ma.zyn.app.dao.criteria.core.catalogue.ProduitCriteria;
import ma.zyn.app.service.facade.admin.catalogue.ProduitAdminService;
import ma.zyn.app.ws.converter.catalogue.ProduitConverter;
import ma.zyn.app.ws.dto.catalogue.ProduitDto;
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
@RequestMapping("/api/admin/produit/")
public class ProduitRestAdmin {




    @Operation(summary = "Finds a list of all produits")
    @GetMapping("")
    public ResponseEntity<List<ProduitDto>> findAll() throws Exception {
        ResponseEntity<List<ProduitDto>> res = null;
        List<Produit> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ProduitDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all produits")
    @GetMapping("optimized")
    public ResponseEntity<List<ProduitDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ProduitDto>> res = null;
        List<Produit> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ProduitDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a produit by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProduitDto> findById(@PathVariable Long id) {
        Produit t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ProduitDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a produit by label")
    @GetMapping("label/{label}")
    public ResponseEntity<ProduitDto> findByLabel(@PathVariable String label) {
	    Produit t = service.findByReferenceEntity(new Produit(label));
        if (t != null) {
            converter.init(true);
            ProduitDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  produit")
    @PostMapping("")
    public ResponseEntity<ProduitDto> save(@RequestBody ProduitDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Produit myT = converter.toItem(dto);
            Produit t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProduitDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  produit")
    @PutMapping("")
    public ResponseEntity<ProduitDto> update(@RequestBody ProduitDto dto) throws Exception {
        ResponseEntity<ProduitDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Produit t = service.findById(dto.getId());
            converter.copy(dto,t);
            Produit updated = service.update(t);
            ProduitDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of produit")
    @PostMapping("multiple")
    public ResponseEntity<List<ProduitDto>> delete(@RequestBody List<ProduitDto> dtos) throws Exception {
        ResponseEntity<List<ProduitDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Produit> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified produit")
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


    @Operation(summary = "Finds a produit and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProduitDto> findWithAssociatedLists(@PathVariable Long id) {
        Produit loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ProduitDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds produits by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProduitDto>> findByCriteria(@RequestBody ProduitCriteria criteria) throws Exception {
        ResponseEntity<List<ProduitDto>> res = null;
        List<Produit> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ProduitDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated produits by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProduitCriteria criteria) throws Exception {
        List<Produit> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ProduitDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets produit data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProduitCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProduitDto> findDtos(List<Produit> list){
        converter.initObject(true);
        List<ProduitDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProduitDto> getDtoResponseEntity(ProduitDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ProduitRestAdmin(ProduitAdminService service, ProduitConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ProduitAdminService service;
    private final ProduitConverter converter;





}
