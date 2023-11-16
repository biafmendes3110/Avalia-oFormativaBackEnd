package com.produtoAvaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produtoAvaliacao.entity.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {

}
