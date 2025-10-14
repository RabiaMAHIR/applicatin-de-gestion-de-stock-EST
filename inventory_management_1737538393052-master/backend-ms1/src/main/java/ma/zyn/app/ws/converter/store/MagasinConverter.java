package  ma.zyn.app.ws.converter.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.store.Magasin;
import ma.zyn.app.ws.dto.store.MagasinDto;

@Component
public class MagasinConverter {



    public Magasin toItem(MagasinDto dto) {
        if (dto == null) {
            return null;
        } else {
        Magasin item = new Magasin();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getAdresse()))
                item.setAdresse(dto.getAdresse());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLabel()))
                item.setLabel(dto.getLabel());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());



        return item;
        }
    }


    public MagasinDto toDto(Magasin item) {
        if (item == null) {
            return null;
        } else {
            MagasinDto dto = new MagasinDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getAdresse()))
                dto.setAdresse(item.getAdresse());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLabel()))
                dto.setLabel(item.getLabel());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());


        return dto;
        }
    }


	
    public List<Magasin> toItem(List<MagasinDto> dtos) {
        List<Magasin> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (MagasinDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<MagasinDto> toDto(List<Magasin> items) {
        List<MagasinDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Magasin item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(MagasinDto dto, Magasin t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Magasin> copy(List<MagasinDto> dtos) {
        List<Magasin> result = new ArrayList<>();
        if (dtos != null) {
            for (MagasinDto dto : dtos) {
                Magasin instance = new Magasin();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
