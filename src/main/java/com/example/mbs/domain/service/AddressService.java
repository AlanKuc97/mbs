package com.example.mbs.domain.service;

import com.example.mbs.api.model.Address;
import com.example.mbs.domain.mapper.AddressMapper;
import com.example.mbs.persistence.repository.AddressRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings({"checkstyle:MissingJavadocMethod", "checkstyle:MissingJavadocType"})
@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AddressService {

  AddressRepository addressRepository;

  @Transactional(readOnly = true)
  public Optional<Address> getAddress(Long id) {
    return addressRepository.findById(id)
      .map(AddressMapper::dtoOf);
  }

  @Transactional(readOnly = true)
  public List<Address> getAddresses(List<Long> ids) {
    return ids.stream()
      .map(this::getAddress)
      .flatMap(Optional::stream)
      .collect(Collectors.toList());
  }

}
