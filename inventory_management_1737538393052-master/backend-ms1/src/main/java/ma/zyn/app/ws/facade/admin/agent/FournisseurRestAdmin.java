package  ma.zyn.app.ws.facade.admin.agent;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.agent.Fournisseur;
import ma.zyn.app.dao.criteria.core.agent.FournisseurCriteria;
import ma.zyn.app.service.facade.admin.agent.FournisseurAdminService;
import ma.zyn.app.ws.converter.agent.FournisseurConverter;
import ma.zyn.app.ws.dto.agent.FournisseurDto;
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
@RequestMapping("/api/admin/fournisseur/")
public class FournisseurRestAdmin {


    // <-------------------------------------------------------------->


    @GetMapping("/count")
    public long countFournisseurs() {
        return service.countFournisseurs();
    }

    // <-------------------------------------------------------------->

    @Operation(summary = "Finds a list of all fournisseurs")
    @GetMapping("")
    public ResponseEntity<List<FournisseurDto>> findAll() throws Exception {
        ResponseEntity<List<FournisseurDto>> res = null;
        List<Fournisseur> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<FournisseurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all fournisseurs")
    @GetMapping("optimized")
    public ResponseEntity<List<FournisseurDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<FournisseurDto>> res = null;
        List<Fournisseur> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<FournisseurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a fournisseur by id")
    @GetMapping("id/{id}")
    public ResponseEntity<FournisseurDto> findById(@PathVariable Long id) {
        Fournisseur t = service.findById(id);
        if (t != null) {
            FournisseurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a fournisseur by label")
    @GetMapping("label/{label}")
    public ResponseEntity<FournisseurDto> findByLabel(@PathVariable String label) {
	    Fournisseur t = service.findByReferenceEntity(new Fournisseur(label));
        if (t != null) {
            FournisseurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  fournisseur")
    @PostMapping("")
    public ResponseEntity<FournisseurDto> save(@RequestBody FournisseurDto dto) throws Exception {
        if(dto!=null){
            Fournisseur myT = converter.toItem(dto);
            Fournisseur t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                FournisseurDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  fournisseur")
    @PutMapping("")
    public ResponseEntity<FournisseurDto> update(@RequestBody FournisseurDto dto) throws Exception {
        ResponseEntity<FournisseurDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Fournisseur t = service.findById(dto.getId());
            converter.copy(dto,t);
            Fournisseur updated = service.update(t);
            FournisseurDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of fournisseur")
    @PostMapping("multiple")
    public ResponseEntity<List<FournisseurDto>> delete(@RequestBody List<FournisseurDto> dtos) throws Exception {
        ResponseEntity<List<FournisseurDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Fournisseur> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified fournisseur")
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


    @Operation(summary = "Finds a fournisseur and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<FournisseurDto> findWithAssociatedLists(@PathVariable Long id) {
        Fournisseur loaded =  service.findWithAssociatedLists(id);
        FournisseurDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds fournisseurs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<FournisseurDto>> findByCriteria(@RequestBody FournisseurCriteria criteria) throws Exception {
        ResponseEntity<List<FournisseurDto>> res = null;
        List<Fournisseur> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<FournisseurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated fournisseurs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody FournisseurCriteria criteria) throws Exception {
        List<Fournisseur> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<FournisseurDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets fournisseur data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody FournisseurCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<FournisseurDto> findDtos(List<Fournisseur> list){
        List<FournisseurDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<FournisseurDto> getDtoResponseEntity(FournisseurDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public FournisseurRestAdmin(FournisseurAdminService service, FournisseurConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final FournisseurAdminService service;
    private final FournisseurConverter converter;





}
