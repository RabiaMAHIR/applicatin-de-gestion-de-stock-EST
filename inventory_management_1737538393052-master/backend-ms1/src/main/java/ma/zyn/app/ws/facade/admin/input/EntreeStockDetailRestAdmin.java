package  ma.zyn.app.ws.facade.admin.input;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.input.EntreeStockDetail;
import ma.zyn.app.dao.criteria.core.input.EntreeStockDetailCriteria;
import ma.zyn.app.service.facade.admin.input.EntreeStockDetailAdminService;
import ma.zyn.app.ws.converter.input.EntreeStockDetailConverter;
import ma.zyn.app.ws.dto.input.EntreeStockDetailDto;
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
@RequestMapping("/api/admin/entreeStockDetail/")
public class EntreeStockDetailRestAdmin {




    @Operation(summary = "Finds a list of all entreeStockDetails")
    @GetMapping("")
    public ResponseEntity<List<EntreeStockDetailDto>> findAll() throws Exception {
        ResponseEntity<List<EntreeStockDetailDto>> res = null;
        List<EntreeStockDetail> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<EntreeStockDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a entreeStockDetail by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EntreeStockDetailDto> findById(@PathVariable Long id) {
        EntreeStockDetail t = service.findById(id);
        if (t != null) {
            converter.init(true);
            EntreeStockDetailDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  entreeStockDetail")
    @PostMapping("")
    public ResponseEntity<EntreeStockDetailDto> save(@RequestBody EntreeStockDetailDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            EntreeStockDetail myT = converter.toItem(dto);
            EntreeStockDetail t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EntreeStockDetailDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  entreeStockDetail")
    @PutMapping("")
    public ResponseEntity<EntreeStockDetailDto> update(@RequestBody EntreeStockDetailDto dto) throws Exception {
        ResponseEntity<EntreeStockDetailDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EntreeStockDetail t = service.findById(dto.getId());
            converter.copy(dto,t);
            EntreeStockDetail updated = service.update(t);
            EntreeStockDetailDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of entreeStockDetail")
    @PostMapping("multiple")
    public ResponseEntity<List<EntreeStockDetailDto>> delete(@RequestBody List<EntreeStockDetailDto> dtos) throws Exception {
        ResponseEntity<List<EntreeStockDetailDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<EntreeStockDetail> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified entreeStockDetail")
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
    public List<EntreeStockDetailDto> findByProduitId(@PathVariable Long id){
        return findDtos(service.findByProduitId(id));
    }

    @Operation(summary = "Finds entreeStockDetails by magasinId and produitId")
    @GetMapping("findByMagazinIdAndProduitId")
    public ResponseEntity<List<EntreeStockDetailDto>> findByMagazinIdAndProduitId(@RequestParam Long magasinId, @RequestParam Long produitId) {
        List<EntreeStockDetail> list = service.findByMagazinIdAndProduitId(magasinId, produitId);
        List<EntreeStockDetailDto> dtos = findDtos(list);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Operation(summary = "delete by produit id")
    @DeleteMapping("produit/id/{id}")
    public int deleteByProduitId(@PathVariable Long id){
        return service.deleteByProduitId(id);
    }

    @Operation(summary = "Finds a entreeStockDetail and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EntreeStockDetailDto> findWithAssociatedLists(@PathVariable Long id) {
        EntreeStockDetail loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        EntreeStockDetailDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds entreeStockDetails by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EntreeStockDetailDto>> findByCriteria(@RequestBody EntreeStockDetailCriteria criteria) throws Exception {
        ResponseEntity<List<EntreeStockDetailDto>> res = null;
        List<EntreeStockDetail> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<EntreeStockDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated entreeStockDetails by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EntreeStockDetailCriteria criteria) throws Exception {
        List<EntreeStockDetail> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<EntreeStockDetailDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets entreeStockDetail data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EntreeStockDetailCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EntreeStockDetailDto> findDtos(List<EntreeStockDetail> list){
        converter.initObject(true);
        List<EntreeStockDetailDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EntreeStockDetailDto> getDtoResponseEntity(EntreeStockDetailDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public EntreeStockDetailRestAdmin(EntreeStockDetailAdminService service, EntreeStockDetailConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final EntreeStockDetailAdminService service;
    private final EntreeStockDetailConverter converter;





}
