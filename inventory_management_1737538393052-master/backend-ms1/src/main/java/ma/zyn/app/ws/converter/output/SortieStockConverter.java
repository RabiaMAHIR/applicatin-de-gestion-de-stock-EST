package  ma.zyn.app.ws.converter.output;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.store.MagasinConverter;
import ma.zyn.app.bean.core.store.Magasin;
import ma.zyn.app.ws.converter.output.SortieStockDetailConverter;
import ma.zyn.app.bean.core.output.SortieStockDetail;
import ma.zyn.app.ws.converter.catalogue.ProduitConverter;
import ma.zyn.app.bean.core.catalogue.Produit;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.output.SortieStock;
import ma.zyn.app.ws.dto.output.SortieStockDto;

@Component
public class SortieStockConverter {

    @Autowired
    private MagasinConverter magasinConverter ;
    @Autowired
    private SortieStockDetailConverter sortieStockDetailConverter ;
    @Autowired
    private ProduitConverter produitConverter ;
    private boolean sortieStockDetails;

    public  SortieStockConverter() {
        initList(true);
    }

    public SortieStock toItem(SortieStockDto dto) {
        if (dto == null) {
            return null;
        } else {
        SortieStock item = new SortieStock();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDateSortie()))
                item.setDateSortie(DateUtil.stringEnToDate(dto.getDateSortie()));
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());

            if(this.sortieStockDetails && ListUtil.isNotEmpty(dto.getSortieStockDetails()))
                item.setSortieStockDetails(sortieStockDetailConverter.toItem(dto.getSortieStockDetails()));


        return item;
        }
    }


    public SortieStockDto toDto(SortieStock item) {
        if (item == null) {
            return null;
        } else {
            SortieStockDto dto = new SortieStockDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(item.getDateSortie()!=null)
                dto.setDateSortie(DateUtil.dateTimeToString(item.getDateSortie()));
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
        if(this.sortieStockDetails && ListUtil.isNotEmpty(item.getSortieStockDetails())){
            sortieStockDetailConverter.init(true);
            sortieStockDetailConverter.setSortieStock(false);
            dto.setSortieStockDetails(sortieStockDetailConverter.toDto(item.getSortieStockDetails()));
            sortieStockDetailConverter.setSortieStock(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.sortieStockDetails = value;
    }
	
    public List<SortieStock> toItem(List<SortieStockDto> dtos) {
        List<SortieStock> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SortieStockDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SortieStockDto> toDto(List<SortieStock> items) {
        List<SortieStockDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (SortieStock item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SortieStockDto dto, SortieStock t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getSortieStockDetails() != null)
            t.setSortieStockDetails(sortieStockDetailConverter.copy(dto.getSortieStockDetails()));
    }

    public List<SortieStock> copy(List<SortieStockDto> dtos) {
        List<SortieStock> result = new ArrayList<>();
        if (dtos != null) {
            for (SortieStockDto dto : dtos) {
                SortieStock instance = new SortieStock();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public MagasinConverter getMagasinConverter(){
        return this.magasinConverter;
    }
    public void setMagasinConverter(MagasinConverter magasinConverter ){
        this.magasinConverter = magasinConverter;
    }
    public SortieStockDetailConverter getSortieStockDetailConverter(){
        return this.sortieStockDetailConverter;
    }
    public void setSortieStockDetailConverter(SortieStockDetailConverter sortieStockDetailConverter ){
        this.sortieStockDetailConverter = sortieStockDetailConverter;
    }
    public ProduitConverter getProduitConverter(){
        return this.produitConverter;
    }
    public void setProduitConverter(ProduitConverter produitConverter ){
        this.produitConverter = produitConverter;
    }
    public boolean  isSortieStockDetails(){
        return this.sortieStockDetails ;
    }
    public void  setSortieStockDetails(boolean sortieStockDetails ){
        this.sortieStockDetails  = sortieStockDetails ;
    }
}
