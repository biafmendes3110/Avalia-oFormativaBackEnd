package com.produtoAvaliacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produtoAvaliacao.entity.Produto;
import com.produtoAvaliacao.services.ProdutoServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Produto", description = "API REST DE GERENCIAMENTO SE USUÁRIOS")
@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*")
public class ProdutoController {
	private final ProdutoServices produtoServices;

	@Autowired
	public ProdutoController (ProdutoServices produtoServices) {
		this.produtoServices = produtoServices;
	}
	@GetMapping("/{id}")
	@Operation(summary = "Localiza o produto por ID")
	public ResponseEntity <Produto> buscaProdutoIdControlId(@PathVariable Long id){
		Produto produto = produtoServices.buscarProdutoId(id);
		if(produto!= null) {
			return ResponseEntity.ok(produto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping
	@Operation(summary = "Apresenta todos os produtos")
	public ResponseEntity<List<Produto>> buscaTodosProdutosControl() {
		List<Produto> Produto = produtoServices.buscarTodosProdutos();

		return ResponseEntity.ok(Produto);
	}
	@PostMapping
	@Operation(summary = "Cadastra um produto")
	public ResponseEntity<Produto> salvaCursoControl(@RequestBody @Valid Produto produto){
		Produto salvaProduto = produtoServices.salvaProduto(produto);

		return ResponseEntity.status(HttpStatus.CREATED).body(salvaProduto);

	}
	@PutMapping ("/{id}")
	@Operation(summary = "alterar produto por id ")
	public ResponseEntity<Produto> alterarProduto(@PathVariable Long id, @RequestBody @Valid Produto produto) {
		Produto alterarProduto = produtoServices.alterarProduto(id,produto);
		if (alterarProduto  != null) {
			return ResponseEntity.ok(alterarProduto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation(summary = "Apagar o id selecionado")
	public ResponseEntity<String> apagaProdutoControl(@PathVariable Long id) {
		boolean apagar = produtoServices.apagarProduto(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
