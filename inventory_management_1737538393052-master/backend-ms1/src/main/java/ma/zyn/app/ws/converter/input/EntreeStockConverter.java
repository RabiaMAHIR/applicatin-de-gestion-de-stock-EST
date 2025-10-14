package  ma.zyn.app.ws.converter.input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.agent.FournisseurConverter;
import ma.zyn.app.bean.core.agent.Fournisseur;
import ma.zyn.app.ws.converter.store.MagasinConverter;
import ma.zyn.app.bean.core.store.Magasin;
import ma.zyn.app.ws.converter.input.EntreeStockDetailConverter;
import ma.zyn.app.bean.core.input.EntreeStockDetail;
import ma.zyn.app.ws.converter.catalogue.ProduitConverter;
import ma.zyn.app.bean.core.catalogue.Produit;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.input.EntreeStock;
import ma.zyn.app.ws.dto.input.EntreeStockDto;

@Component
public class EntreeStockConverter {

    @Autowired
    private FournisseurConverter fournisseurConverter ;
    @Autowired
    private MagasinConverter magasinConverter ;
    @Autowired
    private EntreeStockDetailConverter entreeStockDetailConverter ;
    @Autowired
    private ProduitConverter produitConverter ;
    private boolean fournisseur;
    private boolean entreeStockDetails;

    public  EntreeStockConverter() {
        init(true);
    }

    public EntreeStock toItem(EntreeStockDto dto) {
        if (dto == null) {
            return null;
        } else {
        EntreeStock item = new EntreeStock();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getDateEntree()))
                item.setDateEntree(DateUtil.stringEnToDate(dto.getDateEntree()));
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.fournisseur && dto.getFournisseur()!=null)
                item.setFournisseur(fournisseurConverter.toItem(dto.getFournisseur())) ;


            if(this.entreeStockDetails && ListUtil.isNotEmpty(dto.getEntreeStockDetails()))
                item.setEntreeStockDetails(entreeStockDetailConverter.toItem(dto.getEntreeStockDetails()));


        return item;
        }
    }


    public EntreeStockDto toDto(EntreeStock item) {
        if (item == null) {
            return null;
        } else {
            EntreeStockDto dto = new EntreeStockDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(item.getDateEntree()!=null)
                dto.setDateEntree(DateUtil.dateTimeToString(item.getDateEntree()));
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.fournisseur && item.getFournisseur()!=null) {
                dto.setFournisseur(fournisseurConverter.toDto(item.getFournisseur())) ;

            }
        if(this.entreeStockDetails && ListUtil.isNotEmpty(item.getEntreeStockDetails())){
            entreeStockDetailConverter.init(true);
            entreeStockDetailConverter.setEntreeStock(false);
            dto.setEntreeStockDetails(entreeStockDetailConverter.toDto(item.getEntreeStockDetails()));
            entreeStockDetailConverter.setEntreeStock(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.entreeStockDetails = value;
    }
    public void initObject(boolean value) {
        this.fournisseur = value;
    }
	
    public List<EntreeStock> toItem(List<EntreeStockDto> dtos) {
        List<EntreeStock> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EntreeStockDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EntreeStockDto> toDto(List<EntreeStock> items) {
        List<EntreeStockDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EntreeStock item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EntreeStockDto dto, EntreeStock t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getFournisseur() == null  && dto.getFournisseur() != null){
            t.setFournisseur(new Fournisseur());
        }else if (t.getFournisseur() != null  && dto.getFournisseur() != null){
            t.setFournisseur(null);
            t.setFournisseur(new Fournisseur());
        }
        if (dto.getFournisseur() != null)
        fournisseurConverter.copy(dto.getFournisseur(), t.getFournisseur());
        if (dto.getEntreeStockDetails() != null)
            t.setEntreeStockDetails(entreeStockDetailConverter.copy(dto.getEntreeStockDetails()));
    }

    public List<EntreeStock> copy(List<EntreeStockDto> dtos) {
        List<EntreeStock> result = new ArrayList<>();
        if (dtos != null) {
            for (EntreeStockDto dto : dtos) {
                EntreeStock instance = new EntreeStock();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public FournisseurConverter getFournisseurConverter(){
        return this.fournisseurConverter;
    }
    public void setFournisseurConverter(FournisseurConverter fournisseurConverter ){
        this.fournisseurConverter = fournisseurConverter;
    }
    public MagasinConverter getMagasinConverter(){
        return this.magasinConverter;
    }
    public void setMagasinConverter(MagasinConverter magasinConverter ){
        this.magasinConverter = magasinConverter;
    }
    public EntreeStockDetailConverter getEntreeStockDetailConverter(){
        return this.entreeStockDetailConverter;
    }
    public void setEntreeStockDetailConverter(EntreeStockDetailConverter entreeStockDetailConverter ){
        this.entreeStockDetailConverter = entreeStockDetailConverter;
    }
    public ProduitConverter getProduitConverter(){
        return this.produitConverter;
    }
    public void setProduitConverter(ProduitConverter produitConverter ){
        this.produitConverter = produitConverter;
    }
    public boolean  isFournisseur(){
        return this.fournisseur;
    }
    public void  setFournisseur(boolean fournisseur){
        this.fournisseur = fournisseur;
    }
    public boolean  isEntreeStockDetails(){
        return this.entreeStockDetails ;
    }
    public void  setEntreeStockDetails(boolean entreeStockDetails ){
        this.entreeStockDetails  = entreeStockDetails ;
    }
}
