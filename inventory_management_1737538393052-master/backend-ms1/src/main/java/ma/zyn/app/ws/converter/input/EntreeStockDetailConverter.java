package  ma.zyn.app.ws.converter.input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.store.MagasinConverter;
import ma.zyn.app.bean.core.store.Magasin;
import ma.zyn.app.ws.converter.catalogue.ProduitConverter;
import ma.zyn.app.bean.core.catalogue.Produit;
import ma.zyn.app.ws.converter.input.EntreeStockConverter;
import ma.zyn.app.bean.core.input.EntreeStock;

import ma.zyn.app.bean.core.input.EntreeStock;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.input.EntreeStockDetail;
import ma.zyn.app.ws.dto.input.EntreeStockDetailDto;

@Component
public class EntreeStockDetailConverter {

    @Autowired
    private MagasinConverter magasinConverter ;
    @Autowired
    private ProduitConverter produitConverter ;
    @Autowired
    private EntreeStockConverter entreeStockConverter ;
    private boolean entreeStock;
    private boolean produit;
    private boolean magazin;

    public  EntreeStockDetailConverter() {
        initObject(true);
    }

    public EntreeStockDetail toItem(EntreeStockDetailDto dto) {
        if (dto == null) {
            return null;
        } else {
        EntreeStockDetail item = new EntreeStockDetail();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getPrix()))
                item.setPrix(dto.getPrix());
            if(StringUtil.isNotEmpty(dto.getQuantite()))
                item.setQuantite(dto.getQuantite());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(dto.getEntreeStock() != null && dto.getEntreeStock().getId() != null){
                item.setEntreeStock(new EntreeStock());
                item.getEntreeStock().setId(dto.getEntreeStock().getId());
                item.getEntreeStock().setCode(dto.getEntreeStock().getCode());
            }

            if(this.produit && dto.getProduit()!=null)
                item.setProduit(produitConverter.toItem(dto.getProduit())) ;

            if(this.magazin && dto.getMagazin()!=null)
                item.setMagazin(magasinConverter.toItem(dto.getMagazin())) ;




        return item;
        }
    }


    public EntreeStockDetailDto toDto(EntreeStockDetail item) {
        if (item == null) {
            return null;
        } else {
            EntreeStockDetailDto dto = new EntreeStockDetailDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getPrix()))
                dto.setPrix(item.getPrix());
            if(StringUtil.isNotEmpty(item.getQuantite()))
                dto.setQuantite(item.getQuantite());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.entreeStock && item.getEntreeStock()!=null) {
                dto.setEntreeStock(entreeStockConverter.toDto(item.getEntreeStock())) ;

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
        this.entreeStock = value;
        this.produit = value;
        this.magazin = value;
    }
	
    public List<EntreeStockDetail> toItem(List<EntreeStockDetailDto> dtos) {
        List<EntreeStockDetail> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EntreeStockDetailDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EntreeStockDetailDto> toDto(List<EntreeStockDetail> items) {
        List<EntreeStockDetailDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EntreeStockDetail item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EntreeStockDetailDto dto, EntreeStockDetail t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getEntreeStock() == null  && dto.getEntreeStock() != null){
            t.setEntreeStock(new EntreeStock());
        }else if (t.getEntreeStock() != null  && dto.getEntreeStock() != null){
            t.setEntreeStock(null);
            t.setEntreeStock(new EntreeStock());
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
        if (dto.getEntreeStock() != null)
        entreeStockConverter.copy(dto.getEntreeStock(), t.getEntreeStock());
        if (dto.getProduit() != null)
        produitConverter.copy(dto.getProduit(), t.getProduit());
        if (dto.getMagazin() != null)
        magasinConverter.copy(dto.getMagazin(), t.getMagazin());
    }

    public List<EntreeStockDetail> copy(List<EntreeStockDetailDto> dtos) {
        List<EntreeStockDetail> result = new ArrayList<>();
        if (dtos != null) {
            for (EntreeStockDetailDto dto : dtos) {
                EntreeStockDetail instance = new EntreeStockDetail();
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
    public ProduitConverter getProduitConverter(){
        return this.produitConverter;
    }
    public void setProduitConverter(ProduitConverter produitConverter ){
        this.produitConverter = produitConverter;
    }
    public EntreeStockConverter getEntreeStockConverter(){
        return this.entreeStockConverter;
    }
    public void setEntreeStockConverter(EntreeStockConverter entreeStockConverter ){
        this.entreeStockConverter = entreeStockConverter;
    }
    public boolean  isEntreeStock(){
        return this.entreeStock;
    }
    public void  setEntreeStock(boolean entreeStock){
        this.entreeStock = entreeStock;
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
