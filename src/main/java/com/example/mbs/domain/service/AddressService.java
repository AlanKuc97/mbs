package com.example.mbs.domain.service;

import com.example.mbs.api.model.AddressDTO;
import com.example.mbs.domain.mapper.AddressDtoMapper;
import com.example.mbs.persistence.repository.AddressRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AddressService {

    AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public Optional<AddressDTO> getAddress(Long id){
        return addressRepository.findById(id)
            .map(AddressDtoMapper::dtoOf);
    }

    @Transactional(readOnly = true)
    public Set<AddressDTO> getAddresses(Set<Long> ids){
        return ids.stream()
            .map(this::getAddress)
            .flatMap(Optional::stream)
            .collect(Collectors.toSet());
    }

}
