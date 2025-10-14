package  ma.zyn.app.ws.converter.store;

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



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.store.Stock;
import ma.zyn.app.ws.dto.store.StockDto;

@Component
public class StockConverter {

    @Autowired
    private MagasinConverter magasinConverter ;
    @Autowired
    private ProduitConverter produitConverter ;
    private boolean produit;
    private boolean magazin;

    public  StockConverter() {
        initObject(true);
    }

    public Stock toItem(StockDto dto) {
        if (dto == null) {
            return null;
        } else {
        Stock item = new Stock();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getQuantite()))
                item.setQuantite(dto.getQuantite());
            if(StringUtil.isNotEmpty(dto.getQuantiteDeffecteuse()))
                item.setQuantiteDeffecteuse(dto.getQuantiteDeffecteuse());
            if(this.produit && dto.getProduit()!=null)
                item.setProduit(produitConverter.toItem(dto.getProduit())) ;

            if(this.magazin && dto.getMagazin()!=null)
                item.setMagazin(magasinConverter.toItem(dto.getMagazin())) ;




        return item;
        }
    }


    public StockDto toDto(Stock item) {
        if (item == null) {
            return null;
        } else {
            StockDto dto = new StockDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getQuantite()))
                dto.setQuantite(item.getQuantite());
            if(StringUtil.isNotEmpty(item.getQuantiteDeffecteuse()))
                dto.setQuantiteDeffecteuse(item.getQuantiteDeffecteuse());
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
        this.produit = value;
        this.magazin = value;
    }
	
    public List<Stock> toItem(List<StockDto> dtos) {
        List<Stock> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StockDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StockDto> toDto(List<Stock> items) {
        List<StockDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Stock item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StockDto dto, Stock t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
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
        if (dto.getProduit() != null)
        produitConverter.copy(dto.getProduit(), t.getProduit());
        if (dto.getMagazin() != null)
        magasinConverter.copy(dto.getMagazin(), t.getMagazin());
    }

    public List<Stock> copy(List<StockDto> dtos) {
        List<Stock> result = new ArrayList<>();
        if (dtos != null) {
            for (StockDto dto : dtos) {
                Stock instance = new Stock();
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
