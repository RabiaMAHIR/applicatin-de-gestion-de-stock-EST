package  ma.zyn.app.ws.converter.catalogue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.catalogue.CategorieProduit;
import ma.zyn.app.ws.dto.catalogue.CategorieProduitDto;

@Component
public class CategorieProduitConverter {



    public CategorieProduit toItem(CategorieProduitDto dto) {
        if (dto == null) {
            return null;
        } else {
        CategorieProduit item = new CategorieProduit();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLabel()))
                item.setLabel(dto.getLabel());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());



        return item;
        }
    }


    public CategorieProduitDto toDto(CategorieProduit item) {
        if (item == null) {
            return null;
        } else {
            CategorieProduitDto dto = new CategorieProduitDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLabel()))
                dto.setLabel(item.getLabel());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());


        return dto;
        }
    }


	
    public List<CategorieProduit> toItem(List<CategorieProduitDto> dtos) {
        List<CategorieProduit> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CategorieProduitDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CategorieProduitDto> toDto(List<CategorieProduit> items) {
        List<CategorieProduitDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CategorieProduit item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CategorieProduitDto dto, CategorieProduit t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<CategorieProduit> copy(List<CategorieProduitDto> dtos) {
        List<CategorieProduit> result = new ArrayList<>();
        if (dtos != null) {
            for (CategorieProduitDto dto : dtos) {
                CategorieProduit instance = new CategorieProduit();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
