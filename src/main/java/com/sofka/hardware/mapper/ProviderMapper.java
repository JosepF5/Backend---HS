package com.sofka.hardware.mapper;

import com.sofka.hardware.dto.ProviderDTO;
import com.sofka.hardware.collection.Provider;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class ProviderMapper {
    private final ModelMapper mapper;

    public ProviderMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ProviderDTO toProviderDto(Provider provider){
        return mapper.map(provider, ProviderDTO.class);
    }

    public Provider toProviderEntity(ProviderDTO providerDto){
        return mapper.map(providerDto, Provider.class);
    }
}
