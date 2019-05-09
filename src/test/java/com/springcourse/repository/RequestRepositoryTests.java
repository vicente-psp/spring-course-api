package com.springcourse.repository;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestRepositoryTests {

    @Autowired private RequestRepository requestRepository;

    @Test
    public void aSaveTest() {
        User owner = new User();
        owner.setId(1L);

        Request request = new Request(null, "Novo Laptop HP 106", "Pretendo obter um laptop", new Date(), RequestState.OPEN, owner, null);
        Request createdRequest = requestRepository.save(request);

        assertThat(createdRequest.getId()).isEqualTo(1L);
    }

    @Test
    public void updateTest() {
        Optional<Request> requestOptional = requestRepository.findById(1L);
        Request request = requestOptional.get();

        request.setDescription("Pretendo obter um laptop, de 106 GB");

        Request updatedRequest = requestRepository.save(request);

        assertThat(updatedRequest.getDescription()).isEqualTo("Pretendo obter um laptop, de 106 GB");
    }

    @Test
    public void getByIdTest() {
        Optional<Request> requestOptional = requestRepository.findById(1L);
        Request request = requestOptional.get();

        assertThat(request.getSubject()).isEqualTo("Novo Laptop HP 106");
    }

    @Test
    public void listTest() {
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList.size()).isEqualTo(2);
    }

    @Test
    public void listByOwnerIdTest() {
        List<Request> requestList = requestRepository.findAllByOwnerId(1L);
        assertThat(requestList.size()).isEqualTo(2);
    }

    @Test
    public void updateStatusTest() {
        int affectedRows = requestRepository.updateStatus(1L, RequestState.IN_PROGRESS);
        assertThat(affectedRows).isEqualTo(1L);
    }

}
