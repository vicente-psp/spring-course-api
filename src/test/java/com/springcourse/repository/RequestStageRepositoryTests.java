package com.springcourse.repository;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestStageRepositoryTests {

    @Autowired private RequestStageRepository requestStageRepository;

    @Test
    public void aSaveTest() {
        RequestStage requestStage = new RequestStage(null, new Date(), "Foi comprado novo laptop de marca HD",
//                RequestState.CLOSED, new Request(1L, null, null, null, null, null, null),
                RequestState.CLOSED, new Request(1L, null, null, null, null, null),
                new User(1L, null, null, null, null, null, null));

        RequestStage requestStageCreated = requestStageRepository.save(requestStage);

        assertThat(requestStageCreated.getId()).isEqualTo(1L);
    }

    @Test
    public void getByIdTest() {
        Optional<RequestStage> requestStageOptional = requestStageRepository.findById(1L);
        RequestStage requestStage = requestStageOptional.get();

        assertThat(requestStage.getDescription()).isEqualTo("Foi comprado novo laptop de marca HD");
    }

    @Test
    public void listByRequestIdTest() {
        List<RequestStage> requestStageList = requestStageRepository.findAllByRequestId(1L);
        assertThat(requestStageList.size()).isEqualTo(1);
    }

}
