package com.study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.querydsl.entity.Hello;
import com.study.querydsl.entity.QHello;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class QuerydslApplicationTests {

    @Autowired
    EntityManager em;

    @Test
    void contextLoads() {

        final Hello hello = new Hello();
        em.persist(hello);

        final JPAQueryFactory query = new JPAQueryFactory(em);
        final QHello qHello = QHello.hello; //Querydsl Q타입 동작 확인

        final Hello result = query
                .selectFrom(qHello)
                .fetchOne();

        assertThat(result).isEqualTo(hello);
        //lombok 동작 확인 (hello.getId())
        assertThat(result.getId()).isEqualTo(hello.getId());
    }

}
