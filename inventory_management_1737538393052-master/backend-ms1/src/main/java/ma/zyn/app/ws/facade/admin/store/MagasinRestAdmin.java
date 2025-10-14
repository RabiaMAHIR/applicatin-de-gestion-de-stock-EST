package  ma.zyn.app.ws.facade.admin.store;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.store.Magasin;
import ma.zyn.app.dao.criteria.core.store.MagasinCriteria;
import ma.zyn.app.service.facade.admin.store.MagasinAdminService;
import ma.zyn.app.ws.converter.store.MagasinConverter;
import ma.zyn.app.ws.dto.store.MagasinDto;
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
@RequestMapping("/api/admin/magasin/")
public class MagasinRestAdmin {




    @Operation(summary = "Finds a list of all magasins")
    @GetMapping("")
    public ResponseEntity<List<MagasinDto>> findAll() throws Exception {
        ResponseEntity<List<MagasinDto>> res = null;
        List<Magasin> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MagasinDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    // <-------------------------------------------------------------->


    @GetMapping("/count")
    public long countMagasins() {
        return service.countMagasins();
    }

    // <-------------------------------------------------------------->

    @Operation(summary = "Finds an optimized list of all magasins")
    @GetMapping("optimized")
    public ResponseEntity<List<MagasinDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<MagasinDto>> res = null;
        List<Magasin> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MagasinDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a magasin by id")
    @GetMapping("id/{id}")
    public ResponseEntity<MagasinDto> findById(@PathVariable Long id) {
        Magasin t = service.findById(id);
        if (t != null) {
            MagasinDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a magasin by label")
    @GetMapping("label/{label}")
    public ResponseEntity<MagasinDto> findByLabel(@PathVariable String label) {
	    Magasin t = service.findByReferenceEntity(new Magasin(label));
        if (t != null) {
            MagasinDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  magasin")
    @PostMapping("")
    public ResponseEntity<MagasinDto> save(@RequestBody MagasinDto dto) throws Exception {
        if(dto!=null){
            Magasin myT = converter.toItem(dto);
            Magasin t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                MagasinDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  magasin")
    @PutMapping("")
    public ResponseEntity<MagasinDto> update(@RequestBody MagasinDto dto) throws Exception {
        ResponseEntity<MagasinDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Magasin t = service.findById(dto.getId());
            converter.copy(dto,t);
            Magasin updated = service.update(t);
            MagasinDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of magasin")
    @PostMapping("multiple")
    public ResponseEntity<List<MagasinDto>> delete(@RequestBody List<MagasinDto> dtos) throws Exception {
        ResponseEntity<List<MagasinDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Magasin> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified magasin")
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


    @Operation(summary = "Finds a magasin and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<MagasinDto> findWithAssociatedLists(@PathVariable Long id) {
        Magasin loaded =  service.findWithAssociatedLists(id);
        MagasinDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds magasins by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<MagasinDto>> findByCriteria(@RequestBody MagasinCriteria criteria) throws Exception {
        ResponseEntity<List<MagasinDto>> res = null;
        List<Magasin> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MagasinDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated magasins by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody MagasinCriteria criteria) throws Exception {
        List<Magasin> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<MagasinDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets magasin data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody MagasinCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<MagasinDto> findDtos(List<Magasin> list){
        List<MagasinDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<MagasinDto> getDtoResponseEntity(MagasinDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public MagasinRestAdmin(MagasinAdminService service, MagasinConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final MagasinAdminService service;
    private final MagasinConverter converter;





}
