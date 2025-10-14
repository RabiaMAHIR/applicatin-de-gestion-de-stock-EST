package  ma.zyn.app.ws.converter.output;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.output.SortieStockConverter;
import ma.zyn.app.bean.core.output.SortieStock;
import ma.zyn.app.ws.converter.store.MagasinConverter;
import ma.zyn.app.bean.core.store.Magasin;
import ma.zyn.app.ws.converter.catalogue.ProduitConverter;
import ma.zyn.app.bean.core.catalogue.Produit;

import ma.zyn.app.bean.core.output.SortieStock;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.output.SortieStockDetail;
import ma.zyn.app.ws.dto.output.SortieStockDetailDto;

@Component
public class SortieStockDetailConverter {

    @Autowired
    private SortieStockConverter sortieStockConverter ;
    @Autowired
    private MagasinConverter magasinConverter ;
    @Autowired
    private ProduitConverter produitConverter ;
    private boolean sortieStock;
    private boolean produit;
    private boolean magazin;

    public  SortieStockDetailConverter() {
        initObject(true);
    }

    public SortieStockDetail toItem(SortieStockDetailDto dto) {
        if (dto == null) {
            return null;
        } else {
        SortieStockDetail item = new SortieStockDetail();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getPrix()))
                item.setPrix(dto.getPrix());
            if(StringUtil.isNotEmpty(dto.getQuantite()))
                item.setQuantite(dto.getQuantite());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(dto.getSortieStock() != null && dto.getSortieStock().getId() != null){
                item.setSortieStock(new SortieStock());
                item.getSortieStock().setId(dto.getSortieStock().getId());
                item.getSortieStock().setCode(dto.getSortieStock().getCode());
            }

            if(this.produit && dto.getProduit()!=null)
                item.setProduit(produitConverter.toItem(dto.getProduit())) ;

            if(this.magazin && dto.getMagazin()!=null)
                item.setMagazin(magasinConverter.toItem(dto.getMagazin())) ;




        return item;
        }
    }


    public SortieStockDetailDto toDto(SortieStockDetail item) {
        if (item == null) {
            return null;
        } else {
            SortieStockDetailDto dto = new SortieStockDetailDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getPrix()))
                dto.setPrix(item.getPrix());
            if(StringUtil.isNotEmpty(item.getQuantite()))
                dto.setQuantite(item.getQuantite());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.sortieStock && item.getSortieStock()!=null) {
                dto.setSortieStock(sortieStockConverter.toDto(item.getSortieStock())) ;

            }
            if(this.produit && item.getProduit()!=null) {
                dto.setProduit(produitConverter.toDto(item.getProduit())) ;

            }
            if(this.magazin && item.getMagazin()!=null) {
                dto.setMagazin(magasinConverter.toDto(item.getMagazin())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.sortieStock = value;
        this.produit = value;
        this.magazin = value;
    }
	
    public List<SortieStockDetail> toItem(List<SortieStockDetailDto> dtos) {
        List<SortieStockDetail> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SortieStockDetailDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SortieStockDetailDto> toDto(List<SortieStockDetail> items) {
        List<SortieStockDetailDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (SortieStockDetail item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SortieStockDetailDto dto, SortieStockDetail t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getSortieStock() == null  && dto.getSortieStock() != null){
            t.setSortieStock(new SortieStock());
        }else if (t.getSortieStock() != null  && dto.getSortieStock() != null){
            t.setSortieStock(null);
            t.setSortieStock(new SortieStock());
        }
        if(t.getProduit() == null  && dto.getProduit() != null){
            t.setProduit(new Produit());
        }else if (t.getProduit() != null  && dto.getProduit() != null){
            t.setProduit(null);
            t.setProduit(new Produit());
        }
        if(t.getMagazin() == null  && dto.getMagazin() != null){
            t.setMagazin(new Magasin());
        }else if (t.getMagazin() != null  && dto.getMagazin() != null){
            t.setMagazin(null);
            t.setMagazin(new Magasin());
        }
        if (dto.getSortieStock() != null)
        sortieStockConverter.copy(dto.getSortieStock(), t.getSortieStock());
        if (dto.getProduit() != null)
        produitConverter.copy(dto.getProduit(), t.getProduit());
        if (dto.getMagazin() != null)
        magasinConverter.copy(dto.getMagazin(), t.getMagazin());
    }

    public List<SortieStockDetail> copy(List<SortieStockDetailDto> dtos) {
        List<SortieStockDetail> result = new ArrayList<>();
        if (dtos != null) {
            for (SortieStockDetailDto dto : dtos) {
                SortieStockDetail instance = new SortieStockDetail();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public SortieStockConverter getSortieStockConverter(){
        return this.sortieStockConverter;
    }
    public void setSortieStockConverter(SortieStockConverter sortieStockConverter ){
        this.sortieStockConverter = sortieStockConverter;
    }
    public MagasinConverter getMagasinConverter(){
        return this.magasinConverter;
    }
    public void setMagasinConverter(MagasinConverter magasinConverter ){
        this.magasinConverter = magasinConverter;
    }
    public ProduitConverter getProduitConverter(){
        return this.produitConverter;
    }
    public void setProduitConverter(ProduitConverter produitConverter ){
        this.produitConverter = produitConverter;
    }
    public boolean  isSortieStock(){
        return this.sortieStock;
    }
    public void  setSortieStock(boolean sortieStock){
        this.sortieStock = sortieStock;
    }
    public boolean  isProduit(){
        return this.produit;
    }
    public void  setProduit(boolean produit){
        this.produit = produit;
    }
    public boolean  isMagazin(){
        return this.magazin;
    }
    public void  setMagazin(boolean magazin){
        this.magazin = magazin;
    }
}
