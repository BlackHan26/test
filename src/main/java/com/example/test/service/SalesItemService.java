package com.example.test.service;

import com.example.test.dto.ResponseDto;
import com.example.test.repository.SalesItemRepository;
import com.example.test.dto.SalesItemDto;
import com.example.test.entity.SalesItemEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class SalesItemService {
    private final SalesItemRepository salesItemRepository;
    private ResponseDto getResponseMessageDto(String msg) {
        ResponseDto response = new ResponseDto();
        response.setMessage(msg);
        return response;
    }

    public ResponseDto createSalesItem(SalesItemDto dto){
        SalesItemEntity salesItemEntity = new SalesItemEntity();
        salesItemEntity.setTitle(dto.getTitle());
        salesItemEntity.setContent(dto.getContent());
        salesItemEntity.setImgUrl(dto.getImgUrl());
        salesItemEntity.setPassword(dto.getPassword());
        salesItemEntity.setWriter(dto.getWriter());
        salesItemEntity.setMinPrice(dto.getMinPriceWanted());
        salesItemEntity.setStatus("판매중");
        SalesItemDto.fromEntity(salesItemRepository.save(salesItemEntity));
        return getResponseMessageDto("물품을 등록 했습니다.");
    }
    public SalesItemDto readSalesItem(Long id){
        Optional<SalesItemEntity> optionalSale = salesItemRepository.findById(id);
        if(optionalSale.isPresent())
            return SalesItemDto.fromEntity(optionalSale.get());
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    public List<SalesItemDto> readSalesItemALl(){
        List<SalesItemDto> saleList = new ArrayList<>();
        for(SalesItemEntity entity: salesItemRepository.findAll()){
            saleList.add(SalesItemDto.fromEntity(entity));
        }
        return saleList;
    }
    public ResponseDto updateSale(Long id, SalesItemDto dto){
        Optional<SalesItemEntity> optionalSale = salesItemRepository.findById(id);
        if(optionalSale.isPresent()){
            SalesItemEntity sale = optionalSale.get();
            sale.setWriter(dto.getWriter());
            sale.setTitle(dto.getTitle());
            sale.setContent(dto.getContent());
            sale.setMinPrice(dto.getMinPriceWanted());
            SalesItemDto.fromEntity(salesItemRepository.save(new SalesItemEntity()));
            return getResponseMessageDto("물품이 업데이트 되었습니다.");
        }else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    public ResponseDto deleteSale(Long id, String writer, String password){
        Optional<SalesItemEntity> optionalSale = salesItemRepository.findById(id);
        if(optionalSale.isPresent()){
            salesItemRepository.deleteById(id);
            return getResponseMessageDto("물품이 삭제되었습니다.");
        }else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    public Page<SalesItemDto> readSalePaged(){
        Pageable pageable = PageRequest.of(
                0, 10, Sort.by("Id").descending());
        Page<SalesItemEntity> saleEntityPage = salesItemRepository.findAll(pageable);
        Page<SalesItemDto> saleDtoPage = saleEntityPage.map(SalesItemDto::fromEntity);
        return saleDtoPage;
    }
}
