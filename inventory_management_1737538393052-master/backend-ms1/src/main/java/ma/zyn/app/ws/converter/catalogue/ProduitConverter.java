package  ma.zyn.app.ws.converter.catalogue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.catalogue.CategorieProduitConverter;
import ma.zyn.app.bean.core.catalogue.CategorieProduit;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.catalogue.Produit;
import ma.zyn.app.ws.dto.catalogue.ProduitDto;

@Component
public class ProduitConverter {

    @Autowired
    private CategorieProduitConverter categorieProduitConverter ;
    private boolean categorieProduit;

    public  ProduitConverter() {
        initObject(true);
    }

    public Produit toItem(ProduitDto dto) {
        if (dto == null) {
            return null;
        } else {
        Produit item = new Produit();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLabel()))
                item.setLabel(dto.getLabel());
            if(StringUtil.isNotEmpty(dto.getPrixUnitaire()))
                item.setPrixUnitaire(dto.getPrixUnitaire());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.categorieProduit && dto.getCategorieProduit()!=null)
                item.setCategorieProduit(categorieProduitConverter.toItem(dto.getCategorieProduit())) ;




        return item;
        }
    }


    public ProduitDto toDto(Produit item) {
        if (item == null) {
            return null;
        } else {
            ProduitDto dto = new ProduitDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLabel()))
                dto.setLabel(item.getLabel());
            if(StringUtil.isNotEmpty(item.getPrixUnitaire()))
                dto.setPrixUnitaire(item.getPrixUnitaire());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.categorieProduit && item.getCategorieProduit()!=null) {
                dto.setCategorieProduit(categorieProduitConverter.toDto(item.getCategorieProduit())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.categorieProduit = value;
    }
	
    public List<Produit> toItem(List<ProduitDto> dtos) {
        List<Produit> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProduitDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProduitDto> toDto(List<Produit> items) {
        List<ProduitDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Produit item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProduitDto dto, Produit t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCategorieProduit() == null  && dto.getCategorieProduit() != null){
            t.setCategorieProduit(new CategorieProduit());
        }else if (t.getCategorieProduit() != null  && dto.getCategorieProduit() != null){
            t.setCategorieProduit(null);
            t.setCategorieProduit(new CategorieProduit());
        }
        if (dto.getCategorieProduit() != null)
        categorieProduitConverter.copy(dto.getCategorieProduit(), t.getCategorieProduit());
    }

    public List<Produit> copy(List<ProduitDto> dtos) {
        List<Produit> result = new ArrayList<>();
        if (dtos != null) {
            for (ProduitDto dto : dtos) {
                Produit instance = new Produit();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CategorieProduitConverter getCategorieProduitConverter(){
        return this.categorieProduitConverter;
    }
    public void setCategorieProduitConverter(CategorieProduitConverter categorieProduitConverter ){
        this.categorieProduitConverter = categorieProduitConverter;
    }
    public boolean  isCategorieProduit(){
        return this.categorieProduit;
    }
    public void  setCategorieProduit(boolean categorieProduit){
        this.categorieProduit = categorieProduit;
    }
}
