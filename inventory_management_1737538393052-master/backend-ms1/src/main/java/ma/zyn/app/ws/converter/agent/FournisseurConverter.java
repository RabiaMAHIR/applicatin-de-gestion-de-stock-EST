package  ma.zyn.app.ws.converter.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.agent.Fournisseur;
import ma.zyn.app.ws.dto.agent.FournisseurDto;

@Component
public class FournisseurConverter {



    public Fournisseur toItem(FournisseurDto dto) {
        if (dto == null) {
            return null;
        } else {
        Fournisseur item = new Fournisseur();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLabel()))
                item.setLabel(dto.getLabel());
            if(StringUtil.isNotEmpty(dto.getAdresse()))
                item.setAdresse(dto.getAdresse());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());



        return item;
        }
    }


    public FournisseurDto toDto(Fournisseur item) {
        if (item == null) {
            return null;
        } else {
            FournisseurDto dto = new FournisseurDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLabel()))
                dto.setLabel(item.getLabel());
            if(StringUtil.isNotEmpty(item.getAdresse()))
                dto.setAdresse(item.getAdresse());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());


        return dto;
        }
    }


	
    public List<Fournisseur> toItem(List<FournisseurDto> dtos) {
        List<Fournisseur> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (FournisseurDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<FournisseurDto> toDto(List<Fournisseur> items) {
        List<FournisseurDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Fournisseur item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(FournisseurDto dto, Fournisseur t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Fournisseur> copy(List<FournisseurDto> dtos) {
        List<Fournisseur> result = new ArrayList<>();
        if (dtos != null) {
            for (FournisseurDto dto : dtos) {
                Fournisseur instance = new Fournisseur();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
