package com.example.mbs.domain.service;

import com.example.mbs.api.model.Address;
import com.example.mbs.domain.mapper.AddressMapper;
import com.example.mbs.persistence.repository.AddressRepository;
import java.util.List;
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
  public List<Address> getAddresses(List<Long> ids) {
    return AddressMapper.dtosOf(addressRepository.findAllById(ids));
  }

}
